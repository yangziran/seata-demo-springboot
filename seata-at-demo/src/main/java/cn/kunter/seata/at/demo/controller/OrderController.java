package cn.kunter.seata.at.demo.controller;

import cn.kunter.seata.at.demo.dto.R;
import cn.kunter.seata.at.demo.service.OrderService;
import cn.kunter.seata.at.demo.vo.PlaceOrderVo;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Order Controller
 * @author nature
 * @version 1.0 2020/6/17
 */
@Slf4j
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;


    @RequestMapping("/place")
    public R placeOrder(@RequestBody PlaceOrderVo placeOrderVo) throws Exception {

        log.info("收到下单请求, 用户: {}, 商品: {}", placeOrderVo.getUserId(), placeOrderVo.getProductId());

        R r = orderService.placeOrder(placeOrderVo);
        log.info("下单完成, 响应结果: {}", JSON.toJSONString(r));
        return r;
    }

}
