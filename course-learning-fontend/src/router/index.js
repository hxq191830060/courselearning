import Vue from "vue";
import VueRouter from "vue-router";
// admin
import AdminLayout from "@/layouts/adminLayout.vue";
import CouponList from "@/views/admin/CouponList.vue";
import CouponCreate from "@/views/admin/CouponCreate.vue";
import CouponDeliver from "@/views/admin/CouponDeliver.vue";
// teacher
import TeacherLayout from "@/layouts/teacherLayout.vue";
import CourseList from "@/views/teacher/CourseList.vue";
import CourseCreate from "@/views/teacher/CourseCreate.vue";
import CourseEdit from "@/views/teacher/CourseEdit.vue";
import TeacherCenter from "@/views/teacher/UserCenter.vue";
// student
import StudentLayout from "@/layouts/studentLayout.vue";
import StudentCourseList from "@/views/student/CourseList.vue";
import UserCenter from "@/views/student/UserCenter.vue";
import HistoryOrder from "@/views/student/HistoryOrder.vue";
import CourseStudy from "@/views/student/CourseStudy.vue";
import CoursePeek from "@/views/student/CoursePeek.vue";
// default
import DefaultLayout from "@/layouts/defaultLayout.vue";
import Login from "@/views/Login.vue";
import Register from "@/views/Register.vue";
import Home from "@/views/Home.vue";
import DiscussZone from "@/views/discuss/DiscussZone.vue";
// authentication
import { judgeTeacher, judgeStudent } from "@/util/auth";
import ReplyInfo from "@/components/ReplyInfo";
import PostCreate from "../components/PostCreate";
import TeacherDiscussZone from "../views/discuss/TeacherDiscussZone";

import CreateQuestions from "../components/CreateQuestions";
import CreateQuestionsDetail from "../components/CreateQuestionsDetail";

import Statistics from "@/views/admin/Statistics";
import TestCreate from "../views/teacher/TestCreate";


Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "Home",
    component: DefaultLayout,
    children: [
      {
        path: "/",
        name: "Home",
        component: Home
      },
      {
        path: "login",
        name: "Login",
        component: Login
      },
      {
        path: "register",
        name: "Register",
        component: Register
      }
    ]
  },
  {
    path: "/admin",
    name: "Admin",
    component: AdminLayout,
    children: [
      {
        path: "/",
        name: "CouponList",
        redirect: "coupon"
      },
      {
        path: "coupon",
        name: "CouponList",
        exact: true,
        component: CouponList
      },
      {
        path: "coupon/create",
        name: "CouponCreate",
        component: CouponCreate
      },
      {
        path: "coupon/deliver",
        name: "CouponDeliver",
        component: CouponDeliver
      },
      {
        path: "statistics",
        name:"Statistics",
        component: Statistics
      }
    ]
  },
  {
    path: "/teacher",
    name: "Teacher",
    component: TeacherLayout,
    children: [
      {
        path: "/",
        name: "TeacherCourseList",
        component: CourseList
      },
      {
        path: "discuss/:courseId",
        name: "TeacherDiscussZone",
        component: TeacherDiscussZone
      },
      {
        path:"discuss/:courseId/create",/*跳转到帖子创建界面*/
        name:"PostCreate",
        component: PostCreate
      },
      {
        path:"createQuestions/:courseId",/*跳转到题库界面*/
        name:"CreateQuestion",
        component: CreateQuestions
      },
      {
        path:"createQuestions/:courseId/detail",/*跳转到题目创建界面*/
        name:"CreateQuestionDetail",
        component: CreateQuestionsDetail
      },
      {
        path:"createQuestions/:courseId/createTest",/*跳转到测试创建界面*/
        name:"TestCreate",
        component: TestCreate
      },

      {
        path: "user/:userId",
        name: "TeacherCenter",
        component: TeacherCenter
      },
      {
        path: "create",
        name: "TeacherCourseCreate",
        component: CourseCreate
      },
      {
        path: "edit/:courseId",
        name: "TeacherCourseEdit",
        component: CourseEdit
      },
      {
        path: "discuss/reply/:postId",/*某courseId讨论区下某userId下发的帖的所有回复*/
        name: "ReplyInfo",
        component: ReplyInfo
      }
      ]
  },
  {
    path: "/student",
    name: "Student",
    component: StudentLayout,
    children: [
      {
        path: "/",
        name: "StudentCourseList",
        component: StudentCourseList
      },
      {
        path: "user/:userId",
        name: "StudentUserCenter",
        component: UserCenter
      },
      {
        path: "history",
        name: "HistoryOrder",
        component: HistoryOrder
      },
      {
        path: "course/:courseId",
        name: "CourseStudy",
        component: CourseStudy
      },
      {
        path: "peek/:courseId",
        name: "CoursePeek",
        component: CoursePeek
      },
      {
        path: "discuss/:courseId",
        name: "DiscussZone",
        component: DiscussZone
      },
      {
        path: "discuss/reply/:postId",/*某courseId讨论区下某userId下发的帖的所有回复*/
        name: "ReplyInfo",
        component: ReplyInfo
      },
      {
        path:"discuss/:courseId/create",/*跳转到帖子创建界面*/
        name:"PostCreate",
        component: PostCreate
      }
    ]
  },
];

const router = new VueRouter({
  mode: "hash",
  base: process.env.BASE_URL,
  routes
});

router.beforeEach((to, from, next) => {
  let legal = false;
  if (!to.path.startsWith("/student") && !to.path.startsWith("/teacher"))
    legal = true;
  if (to.path.startsWith("/student") && judgeStudent()) legal = true;
  if (to.path.startsWith("/teacher") && judgeTeacher()) legal = true;
  if (to.name === "CoursePeek") legal = true;
  if (legal) {
    next();
  } else {
    next({ name: "Login" });
  }
});

export default router;
