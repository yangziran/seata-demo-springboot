package cn.kunter.seata.saga.demo.service;

import java.math.BigDecimal;

/**
 * Pay Service
 * @author nature
 * @version 1.0 2020/6/17
 */
public interface PayService {

    /**
     * 扣减
     */
    Boolean reduce(String businessKey, BigDecimal amount);

    /**
     * 补偿
     */
    Boolean compensateReduce(String businessKey);

}
