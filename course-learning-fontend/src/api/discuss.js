import axios from "axios";
import { POST_MODULE } from "./_prefix";
import { REPLY_MODULE } from "./_prefix";

/**
 * 获取某课程的帖子列表 GET
 * @param {*} courseId
 * @returns
 */
export const getPostByCourseId = courseId => {
   return axios.get(`${POST_MODULE}/getAll/${courseId}`).then(res=>{
       return res.data;
   })
};


/**
 * 获取用户发的所有帖子 GET
 * @param {*} userId
 * @returns
 */
export const getOneUserAllPosts = userId => {
    return axios.get(`${POST_MODULE}/getOneUserAllPosts/${userId}`).then(res=>{
        return res.data;
    })
};

/**
 * 创建一个帖子 POST
 * @param {*} payload
 * @returns
 */
export const createPost = payload => {
    //const {courseId,userId,title,content}=payload;
    return axios.post(`${POST_MODULE}/create`,payload).then(res=>{
        return res.data;
    })
};

/**
 * 根据postId获取post GET
 * @param {*} postId
 * @returns
 */
export const getPostByPostId = postId => {
    return axios.get(`${POST_MODULE}/getPostByPostId/${postId}`).then(res=>{
        return res.data;
    })
};

/**
 * 获取某帖子的所有评论 GET
 * @param {*} postId
 * @returns
 */
export const getReplyListByPostId = postId => {
    return axios.get(`${REPLY_MODULE}/getReplyList/${postId}`).then(res=>{
        return res.data;
    })
};

/**
 * 创建一个评论 POST
 * @param {*} payload
 * @returns
 */
export const createReply=payload=>{
    const {postId,userId,content}=payload
    return axios.post(`${REPLY_MODULE}/create`,{postId,userId,content}).then(res=>{
        return res.data;
    })
}
/**
 * 删除一个POST
 * @param postId
 */
export const deletePostById=postId=>{
    return axios.get(`${POST_MODULE}/delete/${postId}`).then(res=>{
        return res.data;
    })
}