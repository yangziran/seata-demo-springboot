package cn.kunter.seata.at.demo.vo;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * 下单VO
 * @author nature
 * @version 1.0 2020/6/17
 */
@Data
@Builder
public class PlaceOrderVo implements Serializable {

    private Long userId;

    private Long productId;

    private Integer number;

}
