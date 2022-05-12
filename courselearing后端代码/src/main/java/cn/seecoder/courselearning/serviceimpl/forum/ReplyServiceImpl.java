package cn.seecoder.courselearning.serviceimpl.forum;

import cn.seecoder.courselearning.mapperservice.forum.ReplyMapper;
import cn.seecoder.courselearning.mapperservice.user.UserMapper;
import cn.seecoder.courselearning.po.forum.Reply;
import cn.seecoder.courselearning.service.forum.ReplyService;
import cn.seecoder.courselearning.service.user.UserService;
import cn.seecoder.courselearning.util.Constant;
import cn.seecoder.courselearning.vo.ResultVO;
import cn.seecoder.courselearning.vo.forum.ReplyVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

@Service
public class ReplyServiceImpl implements ReplyService {
    @Resource
    ReplyMapper replyMapper;

    @Resource
    UserService userService;
    /**
     * 根据帖子编号返回所有的评论，按时间排序
     * @param postId 帖子编号
     */
    @Override
    public List<ReplyVO> getReplyListByPostId(int postId) {
        List<Reply> replyList=replyMapper.seleceReplyListByPostId(postId);

        List<ReplyVO> list=new ArrayList<>();

        for (Reply reply : replyList) {
            list.add(new ReplyVO(reply, userService.getUser(reply.getUserId()).getUname()));
        }

        list.sort(new Comparator<ReplyVO>() {
            @Override
            public int compare(ReplyVO o1, ReplyVO o2) {
                return o1.getCreateTime().compareTo(o2.getCreateTime());
            }
        });
        return list;
    }

    /**
     * 创建评论
     * @param replyVO 帖子编号
     * 参数内含有postId，userId，content
     */
    @Override
    public ResultVO<ReplyVO> createReply(ReplyVO replyVO) {
        Reply reply=new Reply();
        reply.setPostId(replyVO.getPostId());
        reply.setCreateTime(new Date());
        reply.setUserId(replyVO.getUserId());
        reply.setContent(replyVO.getContent());
        int insertNum=replyMapper.insert(reply);
        reply.setReplyId(replyMapper.getLastedReplyId());
        String username=userService.getUser(reply.getUserId()).getUname();
        ReplyVO res=new ReplyVO(reply,username);
        if(insertNum!=1){
            return new ResultVO<>(Constant.REQUEST_FAIL, "创建评论失败", res);
        }else{
            return new ResultVO<>(Constant.REQUEST_SUCCESS, "创建评论成功", res);
        }

    }

    /**
     * 删除评论
     * @param replyId 评论编号
     *
     */
    @Override
    public ResultVO<ReplyVO> deleteReply(int replyId) {
        int deleteNum=replyMapper.delete(replyId);
        if(deleteNum!=1){
            return new ResultVO<ReplyVO>(Constant.REQUEST_FAIL,"删除评论失败");
        }else{
            return new ResultVO<ReplyVO>(Constant.REQUEST_SUCCESS,"删除评论成功");
        }
    }
}
