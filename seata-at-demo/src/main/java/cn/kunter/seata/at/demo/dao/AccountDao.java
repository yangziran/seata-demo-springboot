package cn.kunter.seata.at.demo.dao;

import cn.kunter.seata.at.demo.eo.Account;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * Account Dao
 * 在DAO层添加注解@DS，是为了单元测试时配置，如若业务也采用动态数据源，则只需要Service进行配置（在DAO层配置无效）
 * @author nature
 * @version 1.0 2020/6/17
 */
@DS("pay")
public interface AccountDao extends BaseMapper<Account> {
}
