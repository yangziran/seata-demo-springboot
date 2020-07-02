package cn.kunter.seata.saga.demo.service.impl;

import cn.kunter.seata.saga.demo.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Order Service Impl
 * @author nature
 * @version 1.0 2020/6/17
 */
@Slf4j
@Service("orderService")
public class OrderServiceImpl implements OrderService {

    @Override
    public Boolean reduce(String businessKey, int count, Map<String, Object> params) {

        if (params != null && "true".equals(params.get("throwException"))) {
            throw new RuntimeException("reduce order failed");
        }
        log.info("reduce order succeed, businessKey: {}, count: {}", businessKey, count);
        return true;
    }

    @Override
    public Boolean compensateReduce(String businessKey, Map<String, Object> params) {

        if (params != null && "true".equals(params.get("throwException"))) {
            throw new RuntimeException("compensate reduce order failed");
        }
        log.info("compensate reduce order succeed, businessKey: {}", businessKey);
        return true;
    }

}
