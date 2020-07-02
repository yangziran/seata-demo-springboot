package cn.kunter.seata.tcc.demo.service.impl;

import cn.kunter.seata.tcc.demo.service.PayService;
import cn.kunter.seata.tcc.demo.service.ResultHolder;
import io.seata.core.context.RootContext;
import io.seata.rm.tcc.api.BusinessActionContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Pay Service Impl
 * @author nature
 * @version 1.0 2020/6/17
 */
@Slf4j
@Service
public class PayServiceImpl implements PayService {

    @Override
    public Boolean prepare(String b) {

        String xid = RootContext.getXID();
        log.info("PayService prepare, XID: {}, b: {}", xid, b);
        return true;
    }

    @Override
    public Boolean commit(BusinessActionContext actionContext) {

        String xid = actionContext.getXid();
        log.info("PayService commit, XID: {}, b: {}", xid, actionContext.getActionContext("b"));
        ResultHolder.setPayResult(xid, "T");
        return true;
    }

    @Override
    public Boolean rollback(BusinessActionContext actionContext) {

        String xid = actionContext.getXid();
        log.info("PayService commit, XID: {}, b: {}", xid, actionContext.getActionContext("b"));
        ResultHolder.setPayResult(xid, "R");
        return true;
    }

}
