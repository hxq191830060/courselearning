<template>
  <div>
    <!--    提交提示功能，样式没调-->
    <v-alert
        class="alert"
        outlined
        type="success"
        text
        v-show="showAlert"
        transition="scroll-y-transition"
    >
      {{ alertMsg }}
    </v-alert>

    <v-card width="650" color="#F5F5F5" style="display: flex; flex-direction: column" elevation="5">
      <div style="width: 90% ;align-self: center">
        <v-card-title style="font-size: 7px">#{{this.replyItem.index}}</v-card-title>
        <v-card-subtitle style="transform: translateY(-10px)">用户：{{ this.replyItem.userName }} 发表于：{{ this.replyItem.createTime }}</v-card-subtitle>
        <v-card-text style="transform: translateY(-20px)">{{ this.replyItem.content }}</v-card-text>
        <v-card-actions style="float: right ">
          <v-btn
              color="deep-purple lighten-2"
              text
              @click="toReply"
          >
            回复
          </v-btn>
        </v-card-actions>
      </div>
    </v-card>

    <v-textarea label="回复" v-show="replyInput" v-model="replyContent" type="textarea"></v-textarea>
    <v-btn color="info" v-show="replyInput" @click="submit">确定</v-btn>
    <v-btn color="error" v-show="replyInput" @click="cancle">取消</v-btn>
  </div>
</template>

<script>
import {createReply} from "@/api/discuss";

export default {
  name: "ReplyItem",
  props: {
    replyItem: {
      type: Object,
      default: function () {
        return {
          replyId: 0,
          userId: 0,
          userName: "",
          createTime: "",
          content: "",
          index: 0
        }
      }
    },
  },
  data(){
    return{
      showAlert: false,
      posid:"",
      uid:"",
      replyInput:false,
      replyContent:"",
      alertMsg:"",
      length: 0
    };
  },
  methods:{
    toReply(){
      //点击应该出现一个输入框
      this.replyInput = true;
    },
    submit(){
      const postId = this.$route.params.postId;
      const uid = window.localStorage.getItem("userId");
      const content = "@"+this.replyItem.userName+"(回复"+this.replyItem.index+"楼）"+":"+this.replyContent;
      const payload = {
        postId:postId,
        userId:uid,
        content:content
      }
      //将处理部分写到then里面，是确定后端数据更新成功后再进行操作，防止异步顺序出错
      createReply(payload).then(res=>{
        this.alertMsg = res.msg;
        console.log(res.msg);
        this.showAlert = true;
        setTimeout(() => {
          this.showAlert = false;
        }, 1000);
        location.reload();
      });
    },
    cancle(){
      this.replyInput = false;
    }
  },

}
</script>

<style scoped>

</style>
