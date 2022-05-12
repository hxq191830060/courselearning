package cn.seecoder.courselearning.controller.forum;

import cn.seecoder.courselearning.service.forum.ReplyService;
import cn.seecoder.courselearning.vo.ResultVO;
import cn.seecoder.courselearning.vo.forum.ReplyVO;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/reply")
public class ReplyController {

    @Resource
    ReplyService replyService;

    /**
     * 根据帖子编号返回所有的评论，按时间排序
     * @param postId 帖子编号
     */
    @GetMapping("/getReplyList/{postId}")
    List<ReplyVO> getReplyListByPostId(@PathVariable int postId) {
        return replyService.getReplyListByPostId(postId);
    }



    /**
     * 创建评论
     * @param replyVO 帖子编号
     * 参数内含有postId，userId，content
     */
    @PostMapping("/create")
    ResultVO<ReplyVO> createReply(@RequestBody ReplyVO replyVO){
        return replyService.createReply(replyVO);
    }



    /**
     * 删除评论
     * @param replyId 评论编号
     *
     */
    @PostMapping("/delete")
    ResultVO<ReplyVO> deleteReply(@RequestParam int replyId){
        return replyService.deleteReply(replyId);
    }


}
