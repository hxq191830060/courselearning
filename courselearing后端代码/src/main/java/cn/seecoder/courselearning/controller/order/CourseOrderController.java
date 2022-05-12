package cn.seecoder.courselearning.controller.order;

import cn.seecoder.courselearning.service.order.CourseOrderService;
import cn.seecoder.courselearning.vo.ResultVO;
import cn.seecoder.courselearning.vo.order.CourseOrderVO;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Api(tags = "购买课程订单API接口")
@RestController
@RequestMapping("/course_order")
public class CourseOrderController {
    @Resource
    private CourseOrderService orderService;



    @PostMapping("/create")
    public ResultVO<CourseOrderVO> createCourseOrder(@RequestParam Integer uid, @RequestParam Integer courseId){
        return orderService.createCourseOrder(courseId,uid);
    }

    /**
     * 根据uid查询用户所有的订单
     */
    @GetMapping("/uid/{uid}")
    public List<CourseOrderVO> getCourseOrders(@PathVariable Integer uid){
        return orderService.getCourseOrders(uid);
    }

    @GetMapping("/orderId/{orderId}")
    public ResultVO<CourseOrderVO> getCourseOrderById(@PathVariable Integer orderId){
        return orderService.getCourseOrderById(orderId);
    }


    @PostMapping("/update")
    public ResultVO<CourseOrderVO> updateCourseOrderStatus(@RequestParam Integer orderId, @RequestParam Integer orderStatus){
        return orderService.updateCourseOrder(orderId, orderStatus);
    }

    @PostMapping("/use_coupon")
    public ResultVO<Boolean> useCoupon(@RequestBody CourseOrderVO orderVO, @RequestParam Integer couponId){
        return orderService.useCoupon(orderVO, couponId);
    }

    @PostMapping("/cancel_coupon")
    public ResultVO<Boolean> cancelCoupon(@RequestBody CourseOrderVO orderVO, @RequestParam Integer couponId){
        return orderService.cancelCoupon(orderVO, couponId);
    }


    @PostMapping("/pay/{orderId}")
    public ResultVO<Boolean> payOrder(@PathVariable Integer orderId){
        return orderService.payOrder(orderId);
    }
}
