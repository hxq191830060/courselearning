package cn.seecoder.courselearning.mapperservice.forum;

import cn.seecoder.courselearning.po.forum.Reply;
import cn.seecoder.courselearning.vo.ResultVO;
import cn.seecoder.courselearning.vo.forum.ReplyVO;

import java.util.List;

public interface ReplyMapper {
    int delete(Integer replyId);

    int insert(Reply reply);

    List<Reply>seleceReplyListByPostId(int postId);

    int getLastedReplyId();
}
