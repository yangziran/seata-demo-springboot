package cn.kunter.seata.at.demo.service;

import cn.kunter.seata.at.demo.dto.R;
import cn.kunter.seata.at.demo.vo.PlaceOrderVo;

/**
 * Order Service
 * @author nature
 * @version 1.0 2020/6/17
 */
public interface OrderService {

    /**
     * 下单
     * @param placeOrderVo 请求参数
     * @return R 下单结果
     * @throws Exception 失败时抛出异常
     */
    R placeOrder(PlaceOrderVo placeOrderVo) throws Exception;

}
