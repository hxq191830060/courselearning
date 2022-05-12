<template>
  <v-card width="800" height="260" class="ma-4 pa-2">
    <v-card-text>
      <div>题目ID: {{ questionId }}</div>
      <p >
        {{ description }}
      </p>
    </v-card-text>
    <v-card-actions>
      <v-btn
          color="teal accent-4"
          @click="turnShow"
      >
        {{ this.msg }}
      </v-btn>
    </v-card-actions>
    <v-card-text v-show="this.showaa">
      答案:{{answer}}
      <br>
      解析:{{analysis}}
    </v-card-text>
    </v-card>
</template>

<script>
import Vue from "vue";

export default Vue.extend({
  name: "QuestionItem",
  props: {
    questionId:{
      type:Number,
      default:0
    },
    description:{
      type:String,
      default:"1"
    },
    answer:{
      type:String,
      default:"2"
    },
    analysis:{
      type:String,
      default:"3"
    },
  },
  data(){
    return{
      showaa:false,
      msg:"展示答案和解析"
    }
  },
  methods:{
    turnShow(){
      if(this.showaa){
        this.showaa = false;
        this.msg = "展示答案和解析"
      }else{
        this.showaa = true;
        this.msg = "隐藏答案和解析"
      }
    }
  }
})
</script>

<style scoped>

</style>