package cn.kunter.seata.at.demo.eo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * product
 * @author nature
 * @version 1.0 2020/6/17
 */
@Data
@TableName(value = "product")
public class Product implements Serializable {

    private Integer id;

    private Double price;

    private Integer stock;

    private Date lastUpdateTime;

}
