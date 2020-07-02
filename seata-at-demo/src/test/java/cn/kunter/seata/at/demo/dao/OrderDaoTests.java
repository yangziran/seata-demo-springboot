package cn.kunter.seata.at.demo.dao;

import cn.kunter.seata.at.demo.eo.Order;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Order Dao Test
 * @author nature
 * @version 1.0 2020/6/17
 */
@Slf4j
@SpringBootTest
class OrderDaoTests {

    @Autowired
    private OrderDao orderDao;

    @Test
    void testSelectById() {
        Long id = 1L;
        Order order = orderDao.selectById(id);
        assertNotNull(order);
        log.info(JSON.toJSONString(order));
    }

}