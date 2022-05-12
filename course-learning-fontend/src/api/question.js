import axios from "axios";
import {QUESTION_MODULE} from "./_prefix";


export const createQuestion = payload=>{
    return axios.post(`${QUESTION_MODULE}/create`,payload).then(res=>{
        return res.data;
    })
}

export const getQuestionsByCourseId = courseId=>{
    return axios.get(`${QUESTION_MODULE}/getQuestionByCourseId?courseId=${courseId}`).then(res=>{
        return res.data;
    })
}
export const getQuestionByQuestionId = questionId=>{
    return axios.get(`${QUESTION_MODULE}/getQuestionByQuestionId?questionId=${questionId}`).then(res=>{
        return res.data;
    })
}