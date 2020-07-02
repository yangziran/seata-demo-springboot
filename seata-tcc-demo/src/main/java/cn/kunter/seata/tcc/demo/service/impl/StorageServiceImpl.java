package cn.kunter.seata.tcc.demo.service.impl;

import cn.kunter.seata.tcc.demo.service.ResultHolder;
import cn.kunter.seata.tcc.demo.service.StorageService;
import io.seata.core.context.RootContext;
import io.seata.rm.tcc.api.BusinessActionContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Storage Service Impl
 * @author nature
 * @version 1.0 2020/6/17
 */
@Slf4j
@Service
public class StorageServiceImpl implements StorageService {

    @Override
    public Boolean prepare(String c) {

        String xid = RootContext.getXID();
        log.info("StorageService prepare, XID: {}, c: {}", xid, c);
        return true;
    }

    @Override
    public Boolean commit(BusinessActionContext actionContext) {

        String xid = actionContext.getXid();
        log.info("StorageService commit, XID: {}, c: {}", xid, actionContext.getActionContext("c"));
        ResultHolder.setStorageResult(xid, "T");
        return true;
    }

    @Override
    public Boolean rollback(BusinessActionContext actionContext) {

        String xid = actionContext.getXid();
        log.info("StorageService rollback, XID: {}, c: {}", xid, actionContext.getActionContext("c"));
        ResultHolder.setStorageResult(xid, "R");
        return true;
    }

}
