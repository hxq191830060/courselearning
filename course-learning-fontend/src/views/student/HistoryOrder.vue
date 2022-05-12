<template>
  <div>
    <v-container class="ma-8 pa-4">
      <v-card
        v-for="o in orderList"
        :key="o.id"
        :color="colorList[o.id % colorList.length]"
        class="ma-8 pa-4"
      >
        <v-card-title class="headline">
          {{ o.courseName }}
        </v-card-title>

        <v-card-subtitle class="mt-1">
          购买时间: {{ o.createTime }}
        </v-card-subtitle>
        <!--  如果status==1，则已经支付，可以学习，否则不能学习，跳转到支付订单页面   -->

        <v-card-actions class="pa-0 pl-2">
          <v-btn v-if="o.status === 1" text @click="handleStudy(o.courseId)"
            >学习课程</v-btn
          >
          <v-btn
            v-else
            text
            @click="showDialog(o.courseId, o.courseName, o.cost)"
            >{{ o.cost === 0 ? "免费购买" : "购买课程" }}</v-btn
          >
        </v-card-actions>
      </v-card>
    </v-container>
    <v-dialog v-model="dialog" width="1000">
      <v-card>
        <v-card-title>购买信息确认</v-card-title>
        <v-card-text> 是否购买课程 「{{ currentCourseName }}」？ </v-card-text>

        <v-divider></v-divider>

        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn color="primary" text @click="openSettleDialog">
            选择结算方案
          </v-btn>
          <v-btn color="primary" text @click="dialog = false">
            取消
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>

    <v-dialog v-model="settleDialog" width="1000">
      <v-card>
        <v-card-title v-if="msg">{{ msg }}</v-card-title>
        <div v-else>
          <v-card-title>优惠券选择</v-card-title>
          <v-data-table
            v-model="selectedCoupons"
            :headers="couponHeaders"
            :items="coupons"
            class="elevation-1"
            show-select
            @item-selected="onSelectCoupon"
          >
            <template #header.data-table-select></template>
          </v-data-table>
          <v-row justify="center" class="mt-5"> </v-row>
          <v-card-actions class="justify-space-around d-flex">
            <v-alert type="info">课程原价： {{ currentOrder.origin }}</v-alert>
            <v-alert type="success">实际付款： {{ currentOrder.cost }}</v-alert>
            <v-btn color="primary" @click="confirmPayment">确认支付</v-btn>
            <v-btn color="secondary" @click="leaveSettlement">离开</v-btn>
          </v-card-actions>
        </div>
      </v-card>
    </v-dialog>

    <!-- 购买结果 -->
    <v-dialog v-model="dialog2" width="500">
      <v-card>
        <v-card-title>购买课程</v-card-title>

        <v-card-text>
          {{ msg }}
        </v-card-text>

        <v-divider></v-divider>

        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn color="primary" text @click="dialog2 = false">
            确认
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
    <v-snackbar v-model="showSnackBar" :color="snackBarColor" top>
      {{ snackBarMsg }}
    </v-snackbar>
  </div>
</template>

<script>
import {
  cancelCoupon,
  createOrder,
  getOrderById,
  getOrdersByUser,
  payOrder,
  useCoupon
} from "@/api/order";
import { getAvailableCouponsForOrder } from "@/api/coupon";

export default {
  name: "HistoryOrder",
  data() {
    return {
      colorList: [
        "#FFAB91",
        "#26A69A",
        "#039BE5",
        "#546E7A",
        "#B39DDB",
        "#EF9A9A"
      ],
      orderList: [],
      dialog: false,
      dialog2: false,
      msg: "",
      currentCourseId: 0,
      currentCourseName: "",
      currentCoursePrice: 0,
      coupons: [],
      selectedCoupons: [],
      settleDialog: false,
      currentOrder: {},
      couponHeaders: [
        {
          text: "优惠券名称",
          value: "name"
        },
        {
          text: "优惠券类型",
          value: "type"
        },
        {
          text: "优惠券描述",
          value: "description"
        },
        {
          text: "元数据",
          value: "metadata"
        },
        {
          text: "与其他优惠券同时使用",
          value: "sharable"
        }
      ],
      showSnackBar: false,
      snackBarColor: "success",
      snackBarMsg: ""
    };
  },

  methods: {
    handleStudy(courseId) {
      this.$router.push(`/student/course/${courseId}`);
    },
    buyCourse(courseId, courseName, cost) {
      this.$emit("buy-course", courseId, courseName, cost);
    },
    showDialog(courseId, courseName, coursePrice) {
      this.currentCourseId = courseId;
      this.currentCourseName = courseName;
      this.currentCoursePrice = coursePrice;
      this.dialog = true;
    },
    openSettleDialog() {
      const uid = window.localStorage.getItem("userId");
      createOrder(uid, this.currentCourseId).then(res => {
        if (res.code === 0) {
          this.snackBarColor = "error";
          this.snackBarMsg = res.msg;
          this.showSnackBar = true;
          this.dialog = false;
          return;
        }

        this.dialog = false;
        this.settleDialog = true;
        this.currentOrder = res.data;

        getAvailableCouponsForOrder(this.currentOrder.id).then(res => {
          this.selectedCoupons = this.currentOrder.usedCoupons
            ? this.currentOrder.usedCoupons
            : [];

          this.coupons = [...res.data, ...this.selectedCoupons]; // 将当前可用的 和 已经用的 合并起来展示，若之前创建过订单没有付款，再次进入时需要这样
        });
      });
    },
    confirmPayment() {
      this.settleDialog = false;
      payOrder(this.currentOrder.id).then(res => {
        this.snackBarColor = "success";
        this.snackBarMsg = res.msg;
        this.showSnackBar = true;
      });
    },
    leaveSettlement() {
      this.currentOrder = {};
      this.currentCourseId = 0;
      this.currentCoursePrice = 0;
      this.currentCourseName = "";
      this.coupons = [];
      this.selectedCoupons = [];
      this.settleDialog = false;
    },
    onSelectCoupon({ item, value }) {
      if (value) {
        useCoupon(this.currentOrder, item.id).then(res => {
          this.snackBarColor = "success";
          this.snackBarMsg = res.msg;
          this.showSnackBar = true;
          getOrderById(this.currentOrder.id).then(res => {
            this.currentOrder = res.data;
          });
        });
      } else {
        cancelCoupon(this.currentOrder, item.id).then(res => {
          this.snackBarColor = "success";
          this.snackBarMsg = res.msg;
          this.showSnackBar = true;
          getOrderById(this.currentOrder.id).then(res => {
            this.currentOrder = res.data;
          });
        });
      }
    }
  },

  mounted() {
    const uid = window.localStorage.getItem("userId");
    getOrdersByUser(uid).then(res => {
      console.log(res);
      this.orderList = res || [];
    });
  }
};
</script>
