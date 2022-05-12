<template>
  <div>
    <v-container style="align-self: center">
      <v-alert
          class="alert"
          outlined
          type="success"
          text
          v-show="showSuccessDialog"
          transition="scroll-y-transition"
      >
        创建测试成功
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
      <v-text-field label="测试名称" v-model="testName"></v-text-field>
      <v-text-field label="测试描述" v-model="testDescription" ></v-text-field>
      <v-text-field label="测试开始时间：YYYY-MM-DD HH:MM:SS" v-model="startDate" ></v-text-field>
      <v-text-field label="测试结束时间：YYYY-MM-DD HH:MM:SS" v-model="endDate" ></v-text-field>
      <v-row style="align-self: center"
          v-for="item in questionList"
             :key="item"
      >
        <v-checkbox v-model="selected" :value="item.questionId" :label="item.description"></v-checkbox>
      </v-row>
      <v-btn color="info" @click="submit">确认</v-btn>
      <v-btn color="error" @click="returnPrevious">取消</v-btn>
    </v-container>
  </div>
</template>

<script>
import { getQuestionsByCourseId} from "../../api/question";
import {createTest} from "../../api/exam";

export default {
name: "TestCreate",
  data(){
  return{
    selected:[],
    questionList:[],
    courseId:this.$route.params["courseId"],
    showSuccessDialog :false,
    showFailDialog:false,
    msg:"",
    testName:"",
    testDescription:"",
    startDate:"",
    endDate:""
  }
  },
  methods:{
    fetchData(){
      getQuestionsByCourseId(this.courseId).then(res=>{
        this.questionList=res;
        console.log(this.questionList);
      })
    },
    submit(){
      const payload = {
        courseId:this.courseId,
        name:this.testName,
        description:this.testDescription,
        startTime:this.startDate,
        endTime:this.endDate,
        questionIds:this.selected,

      };
      createTest(payload).then(res=>{
        if (res.code === 1) {
          this.showSuccessDialog = true;
          setTimeout(() => {
            this.showSuccessDialog = false;
            this.$router.go(-1);
          }, 1000);
        }else {
          this.showFailDialog = true;
          this.msg = res.msg;
          setTimeout(() => {
            this.showFailDialog = false;
          }, 1000);
        }})
    },
    returnPrevious(){
      this.$router.go(-1);
    }
  },
  mounted() {
    this.fetchData();
  }
}
</script>

<style scoped>

</style>