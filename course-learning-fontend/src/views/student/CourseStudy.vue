<template>
  <div>
    <v-container class="pl-16 pr-16">
      <!-- alert-->
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

      <!-- dialog-->
      <template>
        <v-row justify="center">
          <v-dialog v-model="dialog" persistent max-width="600px">
            <v-card>
              <v-card-title>
                <span class="headline">课件详情</span>
              </v-card-title>
              <v-card-text>
                <v-container>
                  <v-row>
                    <v-col cols="12" sm="6" md="4">
                      <v-text-field
                        label="文件类型"
                        v-model="currentFile.fileType"
                        readonly
                      ></v-text-field>
                    </v-col>
                    <v-col cols="12" sm="6" md="4">
                      <v-text-field
                        label="文件大小"
                        v-model="currentFile.fileSize"
                        readonly
                      ></v-text-field>
                    </v-col>
                    <v-col cols="12">
                      <v-text-field
                        label="文件名"
                        v-model="currentFile.fileName"
                        readonly
                      ></v-text-field>
                    </v-col>
                    <v-col cols="12">
                      <v-text-field
                        label="课件标题"
                        v-model="currentFile.title"
                        readonly
                      ></v-text-field>
                    </v-col>
                    <v-col cols="12" sm="6">
                      <v-select
                        :items="[true, false]"
                        label="是否免费"
                        v-model="currentFile.freeFlag"
                        readonly
                      ></v-select>
                    </v-col>
                    <v-col cols="12" sm="6">
                      <v-select
                        :items="[true, false]"
                        label="是否可下载"
                        v-model="currentFile.downloadFlag"
                        readonly
                      ></v-select>
                    </v-col>
                    <v-col cols="12" sm="6">
                      <v-select
                        :items="[true, false]"
                        label="是否有权查看"
                        v-model="currentFile.availableFlag"
                        readonly
                      ></v-select>
                    </v-col>
                  </v-row>
                </v-container>
              </v-card-text>
              <v-card-actions>
                <v-spacer></v-spacer>
                <v-btn color="blue darken-1" text @click="dialog = false">
                  Close
                </v-btn>
                <v-btn
                  v-show="currentFile.downloadFlag"
                  color="blue darken-1"
                  text
                  @click="handleDownloadFile"
                >
                  下载课件
                </v-btn>
              </v-card-actions>
            </v-card>
          </v-dialog>
        </v-row>
      </template>

      <!--测试详情弹窗-->
      <template>
        <v-row justify="center">
          <v-dialog v-model="examDialog" persistent max-width="600px">
            <v-card>
              <v-card-title>
                <span class="headline">测试详情</span>
              </v-card-title>
              <v-card-text>
                <v-container>
                  <v-row>
                    <v-col cols="12" sm="6" md="4">
                      <v-text-field
                          label="测试名称"
                          v-model="currentExam.name"
                          disabled
                      ></v-text-field>
                    </v-col>
                    <v-col cols="12" sm="6" md="4">
                      <v-text-field
                          label="测试信息"
                          v-model="currentExam.description"
                          disabled
                      ></v-text-field>
                    </v-col>
                    <v-col cols="12">
                      <v-text-field
                          label="开始时间"
                          v-model="currentExam.startTime"
                          disabled
                      ></v-text-field>
                    </v-col>
                    <v-col cols="12">
                      <v-text-field
                          label="结束时间"
                          v-model="currentExam.endTime"
                          disabled
                      ></v-text-field>
                    </v-col>
                  </v-row>
                  <v-carousel>
                    <v-carousel-item v-for="i in currentExam.questions" :key="i">
                      <v-sheet height="100%" tile color="info" max-width="100%">
                        <v-row
                            class="fill-height"
                            align="center"
                            justify="center"
                        >
                          <v-card color="correct">
                            <v-card-text>
                              题号: {{i.questionId}}
                            </v-card-text>
                            <v-card-text  >
                              题目描述: {{i.description}}
                            </v-card-text>
                          </v-card>
                        </v-row>
                      </v-sheet>
                    </v-carousel-item>
                  </v-carousel>
                  <v-carousel-item >
                    asd
                  </v-carousel-item>
                </v-container>
              </v-card-text>
              <v-card-actions>
                <v-spacer></v-spacer>
                <v-btn color="info" text >
                  进入考试
                </v-btn>
                <v-btn color="error" text @click="examDialog = false">
                  关闭
                </v-btn>
              </v-card-actions>
            </v-card>
          </v-dialog>
        </v-row>
      </template>

      <v-row>
        <!-- 课件 -->
        <v-row style="flex-direction: column">
          <v-row>
            <v-card width="500" class="mx-auto mt-8">
              <v-toolbar color="indigo lighten-2" dark>
                <v-app-bar-nav-icon></v-app-bar-nav-icon>
                <v-toolbar-title>下载课件</v-toolbar-title>
                <v-spacer></v-spacer>
              </v-toolbar>

              <v-list subheader two-line>
                <v-list-item v-for="file in files" :key="file.title">
                  <v-list-item-avatar>
                    <v-icon class="grey lighten-1" dark>
                      mdi-file
                    </v-icon>
                  </v-list-item-avatar>

                  <v-list-item-content>
                    <v-list-item-title v-text="file.title"></v-list-item-title>

                    <v-list-item-subtitle
                        v-text="file.fileSize"
                    ></v-list-item-subtitle>
                  </v-list-item-content>

                  <v-list-item-action>
                    <v-btn icon>
                      <v-icon
                          color="grey lighten-1"
                          @click="showFileDialog(file.id)"
                      >mdi-more</v-icon
                      >
                    </v-btn>
                  </v-list-item-action>
                </v-list-item>
              </v-list>
            </v-card>
          </v-row>
          <v-row >
            <!--测试列表-->
            <v-card width="500" class="mx-auto mt-8">
              <v-toolbar color="primary" dark>
                <v-app-bar-nav-icon></v-app-bar-nav-icon>
                <v-toolbar-title>课程测试</v-toolbar-title>
                <v-spacer></v-spacer>
              </v-toolbar>

              <v-list subheader three-line>
                <v-list-item v-for="exam in exams" :key="exam.name">
                  <v-list-item-avatar>
                    <v-icon class="grey lighten-1" dark>
                      mdi-school
                    </v-icon>
                  </v-list-item-avatar>

                  <v-list-item-content>
                    <v-list-item-title v-text="exam.name"></v-list-item-title>

                    <v-list-item-subtitle
                        开始时间 v-text="exam.startTime"
                    ></v-list-item-subtitle>
                    <v-list-item-subtitle
                        结束时间 v-text="exam.endTime"
                    ></v-list-item-subtitle>
                  </v-list-item-content>

                  <v-list-item-action>
                    <v-btn icon>
                      <v-icon
                          color="grey lighten-1"
                          @click="showExamDialog(exam.examId)"
                      >mdi-more</v-icon
                      >
                    </v-btn>
                  </v-list-item-action>
                </v-list-item>
              </v-list>
            </v-card>
          </v-row>
        </v-row>
        <!-- 课程信息 -->
        <v-col>
          <form
            class="pa-12 grey lighten-5 mt-8"
            style="position: sticky;top: 0"
          >
            <v-text-field
              v-model="courseInfo.name"
              label="课程名称"
              readonly
            ></v-text-field>
            <v-text-field
              v-model="courseInfo.id"
              label="课程ID"
              readonly
            ></v-text-field>
            <v-select
              :items="types"
              v-model="courseInfo.type"
              label="课程分类"
              readonly
            ></v-select>
            <v-textarea
              v-model="courseInfo.intro"
              label="课程简介"
              readonly
            ></v-textarea>
            <v-text-field
              v-model="courseInfo.school"
              label="所属学校"
              readonly
            ></v-text-field>
            <v-text-field
              v-model="courseInfo.createTime"
              label="创建时间"
              readonly
            ></v-text-field>
            <v-text-field
              v-model="courseInfo.teacherName"
              label="创建教师"
              readonly
            ></v-text-field>
            <v-switch
              v-model="isCharge"
              :label="`是否为付费课程: ${isCharge.toString()}`"
              readonly
            ></v-switch>
            <v-text-field
              v-show="isCharge"
              v-model="courseInfo.cost"
              label="课程价格(元)"
              readonly
            ></v-text-field>
          </form>
        </v-col>

      </v-row>

    </v-container>
  </div>
</template>

<script>
import { downloadFile, getCourseWareByCourse } from "@/api/courseWare";
import { getCourseById } from "@/api/course";
import {getAllExamsByCourseId} from "../../api/exam";
//import { FILE_BASE_PATH } from "@/api/file";

export default {
  name: "CourseEdit",

  data() {
    return {
      courseInfo: {},
      types: ["初级", "中级", "高级"],
      msg: "",
      files: [],
      exams:[],
      showAlert: false,
      alertMsg: "",
      dialog: false,
      examDialog:false,
      currentFile: {
        id: 0,
        courseId: 0,
        number: 0,
        title: "",
        fileName: "",
        fileType: "",
        fileSize: "",
        freeFlag: false,
        downloadFlag: true,
        availableFlag: false,
        uploadTime: ""
      },
      currentExam:
          {
            examId:0,
            name:"",
            description:"",
            startTime:"",
            endTime:"",
            questions:[]
          }
    };
  },

  computed: {
    isCharge: function() {
      return this.courseInfo.cost !== 0;
    }
  },

  methods: {
    refreshFile() {
      const { courseId } = this.$route.params;
      const uid = window.localStorage.getItem("userId");
      getCourseWareByCourse({ courseId, uid }).then(res => {
        this.files = res || [];
      });
    },
    refreshExam(){
      const {courseId} = this.$route.params;
      getAllExamsByCourseId(courseId).then(res=>{
        this.exams = res||[];
      })
    },

    loadCourse() {
      const { courseId } = this.$route.params;
      const uid = window.localStorage.getItem("userId");
      getCourseById({ uid, courseId }).then(res => {
        this.courseInfo = res;
      });
    },

    showFileDialog(courseWareId) {
      const currentFile = this.files.find(f => f.id === courseWareId);
      console.log(currentFile);
      this.currentFile = currentFile;
      this.dialog = true;
    },
    showExamDialog(examId){
      const currentExam = this.exams.find(f=>f.examId === examId);
      console.log(currentExam);
      this.currentExam = currentExam;
      this.examDialog = true;
    },

    handleDownloadFile() {
      downloadFile(this.currentFile.fileName).then(res => {
        let url = window.URL.createObjectURL(new Blob([res.data]));
        // 生成一个a标签
        let link = document.createElement("a");
        link.style.display = "none";
        link.href = url;
        // 获取文件名
        link.download = res.config["url"].substring(19);
        document.body.appendChild(link);
        link.click();

        this.alertMsg = "下载成功";
        this.showAlert = true;
        this.dialog = false;
        setTimeout(() => {
          this.showAlert = false;
        }, 1000);
      });
    }
  },

  mounted() {
    this.refreshFile();
    this.refreshExam();
    this.loadCourse();
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
