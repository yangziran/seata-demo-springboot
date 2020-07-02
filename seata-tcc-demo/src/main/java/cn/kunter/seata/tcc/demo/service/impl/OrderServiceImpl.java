package cn.kunter.seata.tcc.demo.service.impl;

import cn.kunter.seata.tcc.demo.service.OrderService;
import cn.kunter.seata.tcc.demo.service.ResultHolder;
import io.seata.core.context.RootContext;
import io.seata.rm.tcc.api.BusinessActionContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Order Service Impl
 * @author nature
 * @version 1.0 2020/6/17
 */
@Slf4j
@Service
public class OrderServiceImpl implements OrderService {

    @Override
    public Boolean prepare(int a) {

        String xid = RootContext.getXID();
        log.info("OrderService prepare, XID: {}, a: {}", xid, a);
        return true;
    }

    @Override
    public Boolean commit(BusinessActionContext actionContext) {

        String xid = actionContext.getXid();
        log.info("OrderService commit, XID: {}, a: {}", xid, actionContext.getActionContext("a"));
        ResultHolder.setOrderResult(xid, "T");
        return true;
    }

    @Override
    public Boolean rollback(BusinessActionContext actionContext) {

        String xid = actionContext.getXid();
        log.info("OrderService rollback, XID: {}, a: {}", xid, actionContext.getActionContext("a"));
        ResultHolder.setOrderResult(xid, "R");
        return true;
    }

}
