package cn.kunter.seata.saga.demo.controller;

import io.seata.saga.engine.StateMachineEngine;
import io.seata.saga.statelang.domain.StateMachineInstance;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

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
    private StateMachineEngine stateMachineEngine;

    @RequestMapping("/commit")
    public void committed() {

        String businessKey = String.valueOf(System.currentTimeMillis());
        Map<String, Object> startParams = new HashMap<>();
        startParams.put("businessKey", businessKey);
        startParams.put("count", 10);
        startParams.put("amount", new BigDecimal("100"));

        StateMachineInstance instance = stateMachineEngine.startWithBusinessKey("reduceInventoryAndBalance"
                , null, businessKey, startParams);
        log.info("saga transaction commit succeed. XID: {}, Status: {}", instance.getId(), instance.getStatus());

    }

    @RequestMapping("/rollback")
    public void compensated() {

        String businessKey = String.valueOf(System.currentTimeMillis());
        Map<String, Object> startParams = new HashMap<>();
        startParams.put("businessKey", businessKey);
        startParams.put("count", 10);
        startParams.put("amount", new BigDecimal("100"));
        startParams.put("mockReduceBalanceFail", "true");

        StateMachineInstance instance = stateMachineEngine.startWithBusinessKey("reduceInventoryAndBalance"
                , null, businessKey, startParams);
        log.info("saga transaction compensate succeed. XID: {}, Status: {}", instance.getId(), instance.getStatus());

    }

}
