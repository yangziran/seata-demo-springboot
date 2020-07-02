package cn.kunter.seata.saga.demo.service.impl;

import cn.kunter.seata.saga.demo.service.StorageService;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Storage Service Impl
 * @author nature
 * @version 1.0 2020/6/17
 */
@Slf4j
@Service("storageService")
public class StorageServiceImpl implements StorageService {


    @Override
    public Boolean reduce(String businessKey, int count, Map<String, Object> params) {

        if (params != null && "true".equals(params.get("throwException"))) {
            throw new RuntimeException("reduce storage failed");
        }
        log.info("reduce storage succeed, businessKey: {}, count: {}, params: {}", businessKey, count,
                JSON.toJSONString(params));
        return true;
    }

    @Override
    public Boolean compensateReduce(String businessKey, Map<String, Object> params) {

//        if (params != null && "true".equals(params.get("throwException"))) {
//            throw new RuntimeException("compensate reduce storage failed");
//        }
        log.info("compensate reduce storage succeed, businessKey: {}, params: {}", businessKey,
                JSON.toJSONString(params));
        return true;
    }

}
