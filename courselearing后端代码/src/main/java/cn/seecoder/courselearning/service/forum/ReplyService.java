package cn.seecoder.courselearning.service.forum;

import cn.seecoder.courselearning.vo.ResultVO;
import cn.seecoder.courselearning.vo.forum.ReplyVO;

import java.util.List;

public interface ReplyService {

    List<ReplyVO> getReplyListByPostId(int postId);

    ResultVO<ReplyVO> createReply(ReplyVO replyVO);

    ResultVO<ReplyVO> deleteReply(int replyId);
}
