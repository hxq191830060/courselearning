import axios from "axios";
import {EXAM_MODULE} from "./_prefix";

export const createTest = payload=>{
    return axios.post(`${EXAM_MODULE}/create`,payload).then(res=>{
        return res.data;
    })
}
export const getAllExamsByCourseId = courseId=>{
    return axios.get(`${EXAM_MODULE}/getExamsByCourseId?courseId=${courseId}`).then(res=>{
        return res.data;
    })
}