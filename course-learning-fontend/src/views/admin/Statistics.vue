<template>
  <div class="body" style="position:relative;padding: 16px; top: 0 ; left: 0 ; height: 100%;width: 100%;">
    <div style="margin-top: 16px">
      <div style="background-color: coral" :style="this.styleObj1">
        <div style="margin-left: 8px;align-self: center">注册学生数:{{ this.statisticData.studentNum  }}</div>
      </div>
    </div>
    <div style="margin-top: 16px">
      <div style="background-color: cornflowerblue" :style="this.styleObj1">
        <div style="margin-left: 8px;align-self: center">注册老师数:{{this.statisticData.teacherNum}}</div>
      </div>
    </div>
    <div style="margin-top: 16px">
      <div style="background-color: darkgray" :style="this.styleObj1">
        <div style="margin-left: 8px;align-self: center">注册管理员数:{{this.statisticData.adminNum}}</div>
      </div>
    </div>
    <div style="margin-top: 16px">
      <div style="background-color: deepskyblue" :style="this.styleObj1">
        <div style="margin-left: 8px;align-self: center">发布的课程数:{{this.statisticData.courseNum}}</div>
      </div>
    </div>
    <div style="margin-top: 16px">
      <div style="background-color: peru" :style="this.styleObj1">
        <div style="margin-left: 8px;align-self: center">每日访问量:{{this.statisticData.logininNum}}</div>
      </div>
    </div>
  </div>


</template>

<script>
import {getStatisticData} from "@/api/statistics";

export default {
  name: "Statistics",

  data() {
    return {
      statisticData: {
        studentNum: 0,
        teacherNum: 0,
        courseNum: 0,
        adminNum: 0,
        logininNum: 0
      },
      styleObj1:{
        borderRadius:" 4px",
        height: "42px" ,
        width: "100%" ,
        display: "flex",
        justifyContent :"center",
        color:"white"

      },
    }
  },
  methods: {
    init() {
      this.getData();
    },
    getData() {
      getStatisticData().then(res => {
        this.statisticData = res;
      })
    }
  },
  mounted() {
    this.init();
  }
}

</script>

<style scoped>

</style>
