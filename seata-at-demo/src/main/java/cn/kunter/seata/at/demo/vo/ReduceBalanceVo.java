package cn.kunter.seata.at.demo.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * 扣减余额VO
 * @author nature
 * @version 1.0 2020/6/17
 */
@Data
public class ReduceBalanceVo implements Serializable {

    private Long userId;

    private Integer price;

}
