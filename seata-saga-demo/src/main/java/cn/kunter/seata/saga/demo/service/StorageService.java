package cn.kunter.seata.saga.demo.service;

import java.util.Map;

/**
 * Storage Service
 * @author nature
 * @version 1.0 2020/6/17
 */
public interface StorageService {

    /**
     * 扣减
     */
    Boolean reduce(String businessKey, int count, Map<String, Object> params);

    /**
     * 补偿
     */
    Boolean compensateReduce(String businessKey, Map<String, Object> params);

}
