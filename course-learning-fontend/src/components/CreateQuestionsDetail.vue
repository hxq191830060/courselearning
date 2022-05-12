<template>
  <div>
    <!-- alert -->
    <v-alert
        class="alert"
        outlined
        type="success"
        text
        v-show="showSuccessDialog"
        transition="scroll-y-transition"
    >
      题目创建成功！
    </v-alert>
    <!-- alert -->
    <v-alert
        class="alert"
        outlined
        type="warning"
        text
        v-show="showFailDialog"
        transition="scroll-y-transition"
    >
      {{ msg }}
    </v-alert>
    <v-container class="pl-16 pr-16">

        <v-textarea v-model="questionInfo.description" label="题目描述"></v-textarea>
        <v-textarea v-model="questionInfo.answer" label="题目答案"></v-textarea>
        <v-textarea v-model="questionInfo.analysis" label="题目解析"></v-textarea>
        <v-btn class="ml-0 mt-8 info" @click="submit">
          确认
        </v-btn>
    </v-container>
  </div>
</template>

<script>
import {createQuestion} from "../api/question";

export default {
  name: "CreateQuestionsDetail",

  data() {
    return {
      questionInfo:{
        description:"",
        answer:"",
        analysis:""
      },
      showSuccessDialog: false,
      showFailDialog: false,
      msg: ""
    };
  },

  methods: {
    submit() {
      const courseId = this.$route.params.courseId;
      const payload = {
        courseId:courseId,
        description:this.questionInfo.description,
        answer:this.questionInfo.answer,
        analysis: this.questionInfo.analysis
      }
      createQuestion(payload).then(res=>{
        console.log(res);
        if (res.code === 1) {
          this.showSuccessDialog = true;
          setTimeout(() => {
            this.showSuccessDialog = false;
            this.$router.go(-1);
          }, 1000);
        } else {
          this.showFailDialog = true;
          this.msg = res.msg;
          setTimeout(() => {
            this.showFailDialog = false;
          }, 1000);
      }})
    }
  }
};
</script>

<style scoped>
.alert {
  position: fixed;
  left: 50%;
  top: 100px;
  z-index: 999;
}
</style>