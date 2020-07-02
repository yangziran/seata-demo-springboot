package cn.kunter.seata.at.demo.service.impl;

import cn.kunter.seata.at.demo.dao.OrderDao;
import cn.kunter.seata.at.demo.dto.R;
import cn.kunter.seata.at.demo.enums.OrderStatus;
import cn.kunter.seata.at.demo.eo.Order;
import cn.kunter.seata.at.demo.service.OrderService;
import cn.kunter.seata.at.demo.service.PayService;
import cn.kunter.seata.at.demo.service.StorageService;
import cn.kunter.seata.at.demo.vo.PlaceOrderVo;
import com.baomidou.dynamic.datasource.annotation.DS;
import io.seata.core.context.RootContext;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Order Service Impl
 * @author nature
 * @version 1.0 2020/6/17
 */
@DS("order")
@Slf4j
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private PayService payService;
    @Autowired
    private StorageService storageService;

    /**
     * 下单
     * 业务入口需要添加Seata全局事务注解：@GlobalTransactional
     * @param placeOrderVo 请求参数
     * @return R 下单结果
     * @throws Exception 失败时抛出异常
     */
    @GlobalTransactional
    @Override
    public R placeOrder(PlaceOrderVo placeOrderVo) throws Exception {

        log.info("==========ORDER START==========");
        log.info("当前XID: {}", RootContext.getXID());

        Order order = Order.builder().userId(placeOrderVo.getUserId()).productId(placeOrderVo.getProductId())
                           .number(placeOrderVo.getNumber()).status(OrderStatus.INIT).build();
        Integer saveOrderRecord = orderDao.insert(order);
        log.info("保存订单结果: {}", saveOrderRecord > 0 ? "成功" : "失败");

        // 扣减库存并计算总价
        Double totalPrice = storageService.reduceStock(placeOrderVo.getProductId(), placeOrderVo.getNumber());
        // 扣减余额
        boolean balanceResult = payService.reduceBalance(placeOrderVo.getUserId(), totalPrice);

        order.setStatus(OrderStatus.SUCCESS);
        Integer updateOrderRecord = orderDao.updateById(order);
        log.info("更新订单 {} 结果: {}", order.getId(), updateOrderRecord > 0 ? "成功" : "失败");
        log.info("==========ORDER END==========");

        return R.builder().success(balanceResult).build();
    }

}
