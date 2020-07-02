package cn.kunter.seata.tcc.demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@Slf4j
@SpringBootTest
class OrderControllerTests {

    @Autowired
    private OrderController orderController;

    @Test
    void orderCommit() {
        String xid = orderController.orderCommit();
        assertNotNull(xid);
        log.info("XID: {}", xid);
    }

    @Test
    void orderRollback() {
        assertThrows(Exception.class, () -> {
            String xid = orderController.orderRollback();
            assertNotNull(xid);
            log.info("XID: {}", xid);
        });
    }

}