<template>
  <v-container class="ma-8 pa-4" fluid >
    <v-row style="display: flex;flex-direction: column">
      <router-link append="append" :to="`detail`">
        <v-btn class="ma-4 mb-8" fab dark color="indigo" fixed>
          添加题目
        </v-btn>
      </router-link>
      <h1 style="color: white"> a</h1>
      <h2 style="color: white"> a</h2>
    </v-row>
    <v-row style="display: flex;flex-direction: column">
      <router-link append="append" :to="`createTest`">
        <v-btn class="ma-4 mb-8" fab dark color="error" fixed>
          创建测试
        </v-btn>
      </router-link>
      <h1> </h1>
    </v-row>

    <v-layout align-center justify-center fill-height>
      <v-row style="display: flex ;flex-direction: column">
        <question-item style="align-self: center"
                       v-for="i in questionList"
                       :key="i.questionId"
                       :question-id="i.questionId"
                       :description="i.description"
                       :answer="i.answer"
                       :analysis="i.analysis"
        >
        </question-item>
      </v-row>
    </v-layout>
  </v-container>
</template>

<script>
import QuestionItem from "./QuestionItem";
import {getQuestionsByCourseId} from "@/api/question";

export default {
  name: "CreateQuestions",
  components:{
    QuestionItem
  },
  data(){
    return{
      questionList:[],
      courseId:this.$route.params["courseId"]
    }
  },
  methods:{
    fetchData(){
      getQuestionsByCourseId(this.courseId).then(res=>{
        this.questionList=res;
        console.log(this.questionList);
      })
    }
  },
  mounted() {
    this.fetchData();
  }

}
</script>

<style scoped>

</style>