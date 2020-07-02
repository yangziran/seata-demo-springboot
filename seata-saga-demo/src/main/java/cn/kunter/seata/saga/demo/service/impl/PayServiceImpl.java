package cn.kunter.seata.saga.demo.service.impl;

import cn.kunter.seata.saga.demo.service.PayService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * Pay Service Impl
 * @author nature
 * @version 1.0 2020/6/17
 */
@Slf4j
@Service("payService")
public class PayServiceImpl implements PayService {

    @Override
    public Boolean reduce(String businessKey, BigDecimal amount) {
        log.info("reduce pay succeed, businessKey: {}, amount: {}", businessKey, amount);
        return true;
    }

    @Override
    public Boolean compensateReduce(String businessKey) {
        log.info("compensate reduce pay succeed, businessKey: {}", businessKey);
        return true;
    }

}
