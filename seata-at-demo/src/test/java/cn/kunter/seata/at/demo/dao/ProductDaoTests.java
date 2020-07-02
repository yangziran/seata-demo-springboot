package cn.kunter.seata.at.demo.dao;

import cn.kunter.seata.at.demo.eo.Product;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Product Dao Test
 * @author nature
 * @version 1.0 2020/6/17
 */
@Slf4j
@SpringBootTest
class ProductDaoTests {

    @Autowired
    private ProductDao productDao;

    @Test
    void testSelectById() {
        Long id = 1L;
        Product product = productDao.selectById(id);
        assertNotNull(product);
        log.info(JSON.toJSONString(product));
    }

}