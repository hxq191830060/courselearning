<template>
  <div>
    <v-container class="pl-16 pr-16">
      <v-card width="700" color="#F5F5F5" style="display: flex; align-items: center" elevation="5">
        <div class="mx-auto" style="width: 90% ">
          <v-card-title>{{ this.postingItem.title }}</v-card-title>
          <v-card-subtitle>用户:{{ this.postingItem.userName}} 发帖时间:{{ this.postingItem.createTime }}</v-card-subtitle>
          <v-card-text>{{ this.postingItem.content }}</v-card-text>
          <v-card-actions style=" float: right">
            <v-btn v-if="this.postingItem.isCurrentUser" @click="deletePosting" class="right-arrow"
                   color="deep-purple lighten-2" text  style=" font-size: 17px">
              删帖
            </v-btn>
            <v-btn @click="toReply" class="right-arrow"
                   color="deep-purple lighten-2" text  style=" font-size: 17px">
              回帖
            </v-btn>
          </v-card-actions>
        </div>
      </v-card>
    </v-container>
  </div>
</template>

<script>
import {deletePostById} from "../api/discuss";

export default {
  name: "PostingItem",
  props: {
    postingItem: {
      type: Object,
      // 对象或数组且一定会从一个工厂函数返回默认值
      default: function () {
        return {
        }

      },
      showSuccessDialog: false,
      showFailDialog: false
    },

  },

  methods: {
    toReply() {
      this.$router.push(`/student/discuss/reply/${this.postingItem.postId}`);
    },
    deletePosting(){
      //调用删帖函数
      deletePostById(this.postingItem.postId).then(res=>{
        if(res.code === 1){
          //删除成功
          this.showSuccessDialog = true;
          setTimeout(() => {
            this.showSuccessDialog = false;
            this.$router.go(0);
          }, 1000);
        }else{
          this.showFailDialog = true;
          setTimeout(() => {
            this.showFailDialog = false;
          }, 1000);
        }
      })
    }
  },
  mounted() {
  }
}
</script>

<style scoped>

</style>
