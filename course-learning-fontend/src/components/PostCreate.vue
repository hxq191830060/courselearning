<template>
  <div>
    <v-container class="pl-16 pr-16" >
      <v-alert
          class="alert"
          outlined
          type="success"
          text
          v-show="showSuccessDialog"
          transition="scroll-y-transition"
      >
        发帖成功
      </v-alert>
      <v-alert
          class="alert"
          outlined
          type="warning"
          text
          v-show="showFailDialog"
          transition="scroll-y-transition"
      >
        {{ this.msg }}
      </v-alert>
      <v-text-field v-model="postInfo.title" label="帖子标题" class="v-textarea"></v-text-field>
      <v-textarea v-model="postInfo.content"  label="帖子内容"></v-textarea>
      <v-btn @click="submit" color="info">确定</v-btn>
      <v-btn color="error" @click="returnToDiscuss">取消</v-btn>
    </v-container>
  </div>
</template>

<script>
import {createPost} from "@/api/discuss";

export default {
name: "PostCreate",
  data(){
  return{
    postInfo:{
      cid:"",
      uid:"",
      title:"",
      content:""
    },
    showSuccessDialog :false,
    showFailDialog:false,
    msg:""
  }
  },
  methods: {
  submit(){
    this.postInfo.cid = this.$route.params["courseId"];
    this.postInfo.uid = window.localStorage.getItem("userId");
    const cid = this.$route.params["courseId"];
    const uid = window.localStorage.getItem("userId");
    const title = this.postInfo.title;
    const content = this.postInfo.content;
    const payload = {
      courseId:cid,
      userId:uid,
      title: title,
      content: content
    }
    createPost(payload).then(res=>{
      //说明创建成功
      if (res.code === 1) {
        this.showSuccessDialog = true;
        setTimeout(() => {
          this.showSuccessDialog = false;
          this.$router.go(-1);
        }, 1000);
      }else{
        this.showFailDialog = true;
        this.msg = res.msg;
        setTimeout(() => {
          this.showFailDialog = false;
        }, 1000);
      }
    })
  },
    returnToDiscuss(){
    this.$router.go(-1);
    }
  }
}
</script>

<style scoped>

</style>