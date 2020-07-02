package cn.kunter.seata.tcc.demo.controller;

import cn.kunter.seata.tcc.demo.service.OrderService;
import cn.kunter.seata.tcc.demo.service.PayService;
import cn.kunter.seata.tcc.demo.service.StorageService;
import io.seata.core.context.RootContext;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private StorageService storageService;
    @Autowired
    private PayService payService;

    /**
     * 发起分布式事务，模拟成功
     */
    @RequestMapping("/commit")
    @GlobalTransactional
    public String orderCommit() {

        // 第一个TCC事务参与者
        boolean orderResult = orderService.prepare(1);
        if (!orderResult) {
            throw new RuntimeException("OrderService failed");
        }

        // 第二个TCC事务参与者
        boolean storageResult = storageService.prepare("STORAGE");
        if (!storageResult) {
            throw new RuntimeException("StorageService failed");
        }

        // 第三个TCC事务参与者
        boolean payResult = payService.prepare("PAY");
        if (!payResult) {
            throw new RuntimeException("PayService failed");
        }

        String xid = RootContext.getXID();
        log.info("XID: {}", xid);
        return xid;
    }

    /**
     * 发起分布式事务，模拟失败
     */
    @RequestMapping("/rollback")
    @GlobalTransactional
    public String orderRollback() {

        // 第一个TCC事务参与者
        boolean orderResult = orderService.prepare(1);
        if (!orderResult) {
            throw new RuntimeException("OrderService failed");
        }

        // 第二个TCC事务参与者
        boolean storageResult = storageService.prepare("STORAGE");
        if (!storageResult) {
            throw new RuntimeException("StorageService failed");
        }

        // 第三个TCC事务参与者
        boolean payResult = payService.prepare("PAY");
        if (!payResult) {
            throw new RuntimeException("PayService failed");
        }

        String xid = RootContext.getXID();
        log.info("XID: {}", xid);
        throw new RuntimeException("Transactional Rollback");
    }

}
