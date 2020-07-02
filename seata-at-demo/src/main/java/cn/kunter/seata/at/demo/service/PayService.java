package cn.kunter.seata.at.demo.service;

/**
 * Pay Service
 * @author nature
 * @version 1.0 2020/6/17
 */
public interface PayService {

    /**
     * 扣减余额
     * @param userId 用户ID
     * @param amount 待扣减金额
     * @return boolean 操作结果
     * @throws Exception 失败时抛出异常
     */
    boolean reduceBalance(Long userId, Double amount) throws Exception;

}
