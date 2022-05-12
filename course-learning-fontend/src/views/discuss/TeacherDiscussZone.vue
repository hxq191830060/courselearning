<template>
  <div style="display: flex  ; flex-direction: column" class="pb-10">
    <v-card color="#B0C4DE" dark width="380" height="80"
            class="mt-4" style="display: flex;align-self: center">
      <div style="font-size: 35px; align-self: center" class="font-weight-medium white--text mx-auto" >
        讨论区
      </div>
    </v-card>
    <div style="  display: flex ; justify-content: flex-end ;width: 80%">
      <el-radio-group v-model="reverse" >
        <el-radio :label="true" @click.native="toT">正序</el-radio>
        <el-radio :label="false" @click.native="toF">倒序</el-radio>
      </el-radio-group>
    </div>
    <v-row>
      <router-link append="append" :to="`create`">
        <v-btn class="ma-4 mb-8" fab dark color="indigo" fixed>
          发帖
        </v-btn>
      </router-link>
    </v-row>
    <div class="mx-auto">
      <teacher-posting-item v-for="(postingItem,index) in this.postingList" :key="index" :posting-item="postingItem" class="mt-8" >></teacher-posting-item>
    </div>
  </div>
</template>

<script>
import TeacherPostingItem from "../../components/TeacherPostingItem";
import {getPostByCourseId} from "@/api/discuss";

export default {
  components: {
    TeacherPostingItem,
  },
  name: "TeacherDiscussZone",
  data() {
    return {
      //postingList[ postingItem{header, content,time,commentNum}]
      //userList[userItem{name,headPic,userID}]
      postingList: [],
      courseId: 0,
      reverse: true
    }
  },
  methods: {
    init() {
      this.courseId = this.$route.params.courseId;  //获取courseId
      this.getPostList(this.courseId);//得到postItem对象列表
      this.judgeCurrentUser();
    },
    getPostList() {
      // 测试用
      // let postingList = [//通过getPostListByCourseId得到每一个postItem的postId，userId，time，title，content，
      //在通过getReplyListByPostId得到commentNum
      //   {
      //     header: "王子悦标题",
      //     content: "这是帖子的部分内容这是帖子的部分内容这是帖子的部分内容这是帖子的部分内容这是帖子的部分内容这是帖子的部分内容这是帖子的部分内容这是帖子的部分内容这是帖子的部分内容这是帖子的部分内容",
      //     time: "2021.5.30",
      //     commentNum: 10,
      //     name: "王子悦",
      //     userId:1,
      //     postingId:1

      //    }// {
      //     header: "张刘洋莹莹",
      //     content: "这是帖子的部分内容这是帖子的部分内容这是帖子的部分内容这是帖子的部分内容这是帖子的部分内容这是帖子的部分内容这是帖子的部分内容这是帖子的部分内容这是帖子的部分内容这是帖子的部分内容",
      //     time: "2021.6.30",
      //     commentNum: 16,
      //     name:"张刘洋",
      //     userId:2,
      //     postingId:2
      //   },
      //   {
      //     header: "王子悦标题",
      //     content: "这是帖子的部分内容这是帖子的部分内容这是帖子的部分内容这是帖子的部分内容这是帖子的部分内容这是帖子的部分内容这是帖子的部分内容这是帖子的部分内容这是帖子的部分内容这是帖子的部分内容",
      //     time: "2021.5.30",
      //     commentNum: 10,
      //     name: "王子悦",
      //     userId: 3,
      //     postingId:3
      //   }
      // ];
      // this.judgeCurrentUser();
      // return postingList;

      getPostByCourseId(this.courseId).then(res=>{
        this.postingList=res;
        //存在一个异步的问题，必须在posting更新后才能继续进行操作
        this.judgeCurrentUser();
      })

    },
    judgeCurrentUser(){

      let currentUser = parseInt(window.localStorage.getItem("userId"));
      for (let i=0;i<this.postingList.length;i++)
      {
        if (currentUser === this.postingList[i].userId) {
          this.postingList[i].isCurrentUser = true;
        }
      }
    },
    toT() {
      if (!this.reverse) {
        this.reverse = true;
        this.postingList = this.postingList.reverse();
      }
    },
    toF() {
      if (this.reverse) {
        this.reverse = false;
        this.postingList = this.postingList.reverse();
      }
    },
  },
  mounted() {
    this.init();
  }
}

</script>
