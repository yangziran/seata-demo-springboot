package cn.kunter.seata.at.demo.dao;

import cn.kunter.seata.at.demo.eo.Account;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Account Dao Test
 * @author nature
 * @version 1.0 2020/6/17
 */
@Slf4j
@SpringBootTest
class AccountDaoTests {

    @Autowired
    private AccountDao accountDao;

    @Test
    void testSelectById() {
        Long id = 1L;
        Account account = accountDao.selectById(id);
        assertNotNull(account);
        log.info(JSON.toJSONString(account));
    }
}