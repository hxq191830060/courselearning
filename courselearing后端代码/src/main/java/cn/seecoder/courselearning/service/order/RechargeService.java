package cn.seecoder.courselearning.service.order;

import cn.seecoder.courselearning.vo.ResultVO;
import cn.seecoder.courselearning.vo.order.RechargeOrderVO;

public interface RechargeService {
    // 账号充值
    ResultVO<RechargeOrderVO> rechargeAccount(RechargeOrderVO rechargeOrderVO);
}
