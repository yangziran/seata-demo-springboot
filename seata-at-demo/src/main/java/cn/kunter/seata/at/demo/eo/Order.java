package cn.kunter.seata.at.demo.eo;

import cn.kunter.seata.at.demo.enums.OrderStatus;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * orders
 * @author nature
 * @version 1.0 2020/6/17
 */
@Data
@Builder
@TableName(value = "orders")
public class Order implements Serializable {

    @TableId(type = IdType.AUTO)
    private Integer id;

    private Long userId;

    private Long productId;

    private OrderStatus status;

    private Integer number;

}
