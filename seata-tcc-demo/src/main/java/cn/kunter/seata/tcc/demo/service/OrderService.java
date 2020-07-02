package cn.kunter.seata.tcc.demo.service;

import io.seata.rm.tcc.api.BusinessActionContext;
import io.seata.rm.tcc.api.BusinessActionContextParameter;
import io.seata.rm.tcc.api.LocalTCC;
import io.seata.rm.tcc.api.TwoPhaseBusinessAction;

/**
 * Order Service
 * @author nature
 * @version 1.0 2020/6/17
 */
@LocalTCC
public interface OrderService {

    /**
     * Prepare
     */
    @TwoPhaseBusinessAction(name = "OrderService", commitMethod = "commit", rollbackMethod = "rollback")
    Boolean prepare(@BusinessActionContextParameter(paramName = "a") int a);

    /**
     * Commit
     */
    Boolean commit(BusinessActionContext actionContext);

    /**
     * Rollback
     */
    Boolean rollback(BusinessActionContext actionContext);

}
