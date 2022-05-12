<template>
  <div style="flex-direction: column;display: flex ; margin-top: 40px">
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
    <v-row>
      <v-btn class="ma-4 mb-8" fab dark color="indigo" fixed @click="rTDiscuss">
        返回
      </v-btn>
    </v-row>
    <posting-info style="align-self: center" v-bind:posting-item="this.postingItem"></posting-info>
    <el-input style="width: 80% ; margin-top: 40px ; align-self: center"
              type="textarea"
              :autosize="{ minRows: 3, maxRows: 10}"
              placeholder="在此发表你对该贴的回复"
              v-model="textarea">
    </el-input>
    <v-btn @click="sendReply" style="width: 15%;align-self: center;margin-top: 20px" v-show="showButton">提交</v-btn>
    <div class="replyArea" style="align-self: center ; margin-top: 40px ; transition: margin-top ">
      <el-radio-group v-model="reverse" >
        <el-radio :label="true" @click.native="toT">正序</el-radio>
        <el-radio :label="false" @click.native="toF">倒序</el-radio>
      </el-radio-group>
      <reply-item v-for="(replyItem,index) in this.replyList" v-bind:reply-item="replyItem" :key="index"  style="margin-bottom: 25px ; margin-top: 16px"></reply-item>
    </div>
  </div>
</template>


<script>
import PostingInfo from "@/components/PostingInfo";
import ReplyItem from "@/components/ReplyItem";
import {createReply, getPostByPostId, getReplyListByPostId} from "@/api/discuss";
export default {
  name: "ReplyInfo",
  components: {
    PostingInfo,
    ReplyItem
  },

  data() {
    return {
      showAlert: false,
      reverse: true,
      alertMsg: "",
      postId: "",
      textarea: "",
      showButton: false,
      postingItem: {
        userName: "",
        title: "",
        createTime: "",
        commentNum: 0,
        content: "",
        userId: 0,
        postId: 0,
        isCurrentUser: false
      },
      replyList: [],
      //replyId、userId、createTime、content
    }
  },
  methods: {
    //定义两个方法：getPostVoByPostId(),getReplyListByReplyId()
    init() {
      //由于异步问题，只能在方法里对参数进行赋值
      this.getPostVoByPostId();
      this.getReplyListByReplyId()
    },
    //以下两个方法均通过ajax请求实现，下面的数据仅用于测试
    getPostVoByPostId() {
      //获取到当前postId
      this.postId = this.$route.params.postId
      getPostByPostId(this.postId).then(res=>{
        this.postingItem=res;
      })
    },
    rTDiscuss() {
      this.$router.go(-1);
    },
    toT() {
      if (!this.reverse) {
        this.reverse = true;
        this.replyList = this.replyList.reverse();

      }
    },
    toF() {
      if (this.reverse) {
        this.reverse = false;
        this.replyList = this.replyList.reverse();
      }
    },
    getReplyListByReplyId() {
      /*let replyList = [
        {
          replyId: 1,
          userId: 2,
          userName:"王子悦",
          createTime: "2000.5.30",
          content: "这是对帖子的回复1帖子的部分内容这是帖子的部分内容这是帖子的部分内容这是帖子的部分内容这是帖子的部分内容这是帖子的部分内容这是帖子的部分内容这是帖子的部分内容这是帖子的部分内容这是帖子的部分内容这是帖子的部分内容这是帖子的部分内容这是"
        },
        {
          replyId: 2,
          userId: 3,
          userName:"张刘洋",
          createTime: "2010.5.30",
          content: "这是对帖子的回复2"
        },
        {
          replyId: 4,
          userId: 9,
          userName: "周辰熙",
          createTime: "2010.9.30",
          content: "这是对帖子的回复3"
        }
      ];
      return replyList;*/

      getReplyListByPostId(this.postId).then(res=>{
        this.replyList=res;
        for (let i = 0; i < this.replyList.length; i++) {
          this.replyList[i].index = i + 1;
        }
      })
    },
    sendReply(){
      //点击按钮，向后台提交新信息，该信息包含了当前user的id，createTime，content
      //userId通过localStorage.getItem("userId")获取
      //然后强制刷新页面，使得这次的提交显示在页面上
      let userId=localStorage.getItem("userId");
      let content=this.textarea;
      const payload={
        postId:this.postId,
        userId:userId,
        content: content
      }
      createReply(payload).then(res=>{
        this.alertMsg = res.msg;
        console.log(res.msg);
        this.showAlert = true;
        setTimeout(() => {
          this.showAlert = false;
        }, 1000);
        location.reload();
      })
    }
  },
  mounted() {
    this.init();
  },
  watch:{
    textarea:function (){
      if (this.textarea.length === 0) {
        this.showButton = false;
      } else {
        this.showButton = true;
      }
    }

  }
}
</script>

<style scoped>
.alert {
  position: fixed;
  left: 50%;
  top: 100px;
  z-index: 999;
}
</style>
