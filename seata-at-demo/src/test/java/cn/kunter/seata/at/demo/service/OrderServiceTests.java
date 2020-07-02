package cn.kunter.seata.at.demo.service;

import cn.kunter.seata.at.demo.dto.R;
import cn.kunter.seata.at.demo.vo.PlaceOrderVo;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Order Service Test
 * SQL脚本里面，默认设置了商品价格为5，商品数量为10，用户余额为20
 * @author nature
 * @version 1.0 2020/6/17
 */
@Slf4j
@SpringBootTest
class OrderServiceTests {

    @Autowired
    private OrderService orderService;

    /**
     * 模拟正常下单，事务提交
     */
    @Test
    void testPlaceOrderSuccess() throws Exception {

        PlaceOrderVo placeOrderVo = PlaceOrderVo.builder().userId(1L).productId(1L).number(1).build();
        assertNotNull(placeOrderVo);

        R r = orderService.placeOrder(placeOrderVo);
        assertNotNull(r);
        log.info(JSON.toJSONString(r));
    }

    /**
     * 模拟库存不足，事务回滚
     */
    @Test
    void testPlaceOrderFailOfStock() throws Exception {

        PlaceOrderVo placeOrderVo = PlaceOrderVo.builder().userId(1L).productId(1L).number(11).build();
        assertNotNull(placeOrderVo);

        assertThrows(Exception.class, () -> {
            R r = orderService.placeOrder(placeOrderVo);
            assertNotNull(r);
            log.info(JSON.toJSONString(r));
        });
    }

    /**
     * 模拟余额不足，事务回滚
     */
    @Test
    void testPlaceOrderFailOfBalance() throws Exception {

        PlaceOrderVo placeOrderVo = PlaceOrderVo.builder().userId(1L).productId(1L).number(5).build();
        assertNotNull(placeOrderVo);

        assertThrows(Exception.class, () -> {
            R r = orderService.placeOrder(placeOrderVo);
            assertNotNull(r);
            log.info(JSON.toJSONString(r));
        });
    }
}