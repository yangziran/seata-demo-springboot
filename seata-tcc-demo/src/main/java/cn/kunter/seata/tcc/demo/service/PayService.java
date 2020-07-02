package cn.kunter.seata.tcc.demo.service;

import io.seata.rm.tcc.api.BusinessActionContext;
import io.seata.rm.tcc.api.BusinessActionContextParameter;
import io.seata.rm.tcc.api.LocalTCC;
import io.seata.rm.tcc.api.TwoPhaseBusinessAction;

/**
 * Pay Service
 * @author nature
 * @version 1.0 2020/6/17
 */
@LocalTCC
public interface PayService {

    /**
     * Prepare
     */
    @TwoPhaseBusinessAction(name = "PayService", commitMethod = "commit", rollbackMethod = "rollback")
    Boolean prepare(@BusinessActionContextParameter(paramName = "b") String b);

    /**
     * Commit
     */
    Boolean commit(BusinessActionContext actionContext);

    /**
     * Rollback
     */
    Boolean rollback(BusinessActionContext actionContext);

}
