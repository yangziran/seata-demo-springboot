package cn.kunter.seata.at.demo.service.impl;

import cn.kunter.seata.at.demo.dao.AccountDao;
import cn.kunter.seata.at.demo.eo.Account;
import cn.kunter.seata.at.demo.service.PayService;
import com.baomidou.dynamic.datasource.annotation.DS;
import io.seata.core.context.RootContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Pay Service Impl
 * @author nature
 * @version 1.0 2020/6/17
 */
@DS("pay")
@Slf4j
@Service
public class PayServiceImpl implements PayService {

    @Autowired
    private AccountDao accountDao;

    /**
     * 扣减余额
     * 重点：事务传播特性设置为 REQUIRES_NEW 开启新的事务
     * @param userId 用户ID
     * @param amount 待扣减金额
     * @return boolean 操作结果
     * @throws Exception 失败时抛出异常
     */
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    @Override
    public boolean reduceBalance(Long userId, Double amount) throws Exception {

        log.info("==========PAY START==========");
        log.info("当前XID: {}", RootContext.getXID());

        // 检查余额
        checkBalance(userId, amount);

        // 扣减余额
        log.info("开始扣减用户 {} 余额", userId);
        Account account = accountDao.selectById(userId);

        account.setBalance(account.getBalance() - amount);
        Integer record = accountDao.updateById(account);
        log.info("扣减用户 {} 余额结果: {}", userId, record > 0 ? "操作成功" : "扣减余额失败");
        log.info("==========PAY END==========");

        return record > 0;
    }

    /**
     * 检查余额
     * @param userId 用户ID
     * @param amount 待扣减金额
     * @throws Exception 余额不足时抛出异常
     */
    private void checkBalance(Long userId, Double amount) throws Exception {

        log.info("检查用户 {} 余额", userId);
        Account account = accountDao.selectById(userId);

        Double balance = account.getBalance();
        if (balance < amount) {
            log.warn("用户 {} 余额不足, 当前余额: {}", userId, balance);
            throw new Exception("余额不足");
        }
    }

}
