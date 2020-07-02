package cn.kunter.seata.tcc.demo.service;

import io.seata.rm.tcc.api.BusinessActionContext;
import io.seata.rm.tcc.api.BusinessActionContextParameter;
import io.seata.rm.tcc.api.LocalTCC;
import io.seata.rm.tcc.api.TwoPhaseBusinessAction;

/**
 * Storage Service
 * @author nature
 * @version 1.0 2020/6/17
 */
@LocalTCC
public interface StorageService {

    /**
     * Prepare
     */
    @TwoPhaseBusinessAction(name = "StorageService", commitMethod = "commit", rollbackMethod = "rollback")
    Boolean prepare(@BusinessActionContextParameter(paramName = "c") String c);

    /**
     * Commit
     */
    Boolean commit(BusinessActionContext actionContext);

    /**
     * Rollback
     */
    Boolean rollback(BusinessActionContext actionContext);

}
