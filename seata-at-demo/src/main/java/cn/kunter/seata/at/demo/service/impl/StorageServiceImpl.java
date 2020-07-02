package cn.kunter.seata.at.demo.service.impl;

import cn.kunter.seata.at.demo.dao.ProductDao;
import cn.kunter.seata.at.demo.eo.Product;
import cn.kunter.seata.at.demo.service.StorageService;
import com.baomidou.dynamic.datasource.annotation.DS;
import io.seata.core.context.RootContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Storage Service Impl
 * @author nature
 * @version 1.0 2020/6/17
 */
@DS("storage")
@Slf4j
@Service
public class StorageServiceImpl implements StorageService {

    @Autowired
    private ProductDao productDao;

    /**
     * 扣减库存
     * 重点：事务传播特性设置为 REQUIRES_NEW 开启新的事务
     * @param productId 商品ID
     * @param number 待扣减数量
     * @return boolean 操作结果
     * @throws Exception 失败时抛出异常
     */
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    @Override
    public Double reduceStock(Long productId, Integer number) throws Exception {

        log.info("==========STORAGE START==========");
        log.info("当前XID: {}", RootContext.getXID());

        // 检查库存
        checkStock(productId, number);

        // 扣减库存
        log.info("开始扣减产品 {} 库存", productId);
        Product product = productDao.selectById(productId);

        product.setStock(product.getStock() - number);
        productDao.updateById(product);
        Double totalPrice = product.getPrice() * number;
        log.info("扣减产品 {} 库存成功, 订单总价为: {}", productId, totalPrice);
        log.info("==========STORAGE END==========");
        return totalPrice;
    }

    /**
     * 检查库存
     * @param productId 商品ID
     * @param number 待扣减数量
     * @throws Exception 库存不足时抛出异常
     */
    private void checkStock(Long productId, Integer number) throws Exception {

        log.info("检查商品 {} 库存", productId);
        Product product = productDao.selectById(productId);

        Integer stock = product.getStock();
        if (stock < number) {
            log.warn("商品 {} 库存不足, 当前库存: {}, 订单数量: {}", productId, stock, number);
            throw new Exception("库存不足");
        }
    }

}
