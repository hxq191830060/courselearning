package cn.seecoder.courselearning.controller.coupon;

import cn.seecoder.courselearning.dto.coupon.CouponDTO;
import cn.seecoder.courselearning.dto.coupon.DeliverCouponDTO;
import cn.seecoder.courselearning.service.coupon.CouponService;
import cn.seecoder.courselearning.vo.ResultVO;
import cn.seecoder.courselearning.vo.coupon.CouponVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Api(tags = "优惠卷API接口")
@RestController
@RequestMapping("/coupon")
public class CouponController {
    @Resource
    CouponService couponService;

    // 创建优惠券

    @ApiOperation(value = "创建优惠卷",notes = "管理员通过该接口完成创建优惠卷的操作")
    @PostMapping("/create")
    ResultVO<CouponVO> createCoupon(@RequestBody CouponDTO universalCouponDTO) {
        return couponService.createCoupon(universalCouponDTO);
    }

    // 查看所有的优惠券

    @ApiOperation(value = "查看所有未过期优惠卷",notes = "用户通过该接口获取所有的未过期的优惠卷信息(包含未开始的优惠卷)")
    @GetMapping("/getAll")
    ResultVO<List<CouponVO>> getAll() {
        return couponService.getAllAvailableCoupons();
    }


    // 获取订单可用的优惠券

    @GetMapping("/getForOrder")
    ResultVO<List<CouponVO>> getForOrder(@RequestParam Integer orderId) {
        return couponService.getAllAvailableCouponsForOrder(orderId);
    }

    // 发放优惠券
    @PostMapping("/deliver")
    ResultVO<Boolean> deliver(@RequestBody DeliverCouponDTO deliverCouponDTO) {
        return couponService.deliverCoupon(deliverCouponDTO);
    }
}
