package cn.kunter.seata.at.demo.eo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * account
 * @author nature
 * @version 1.0 2020/6/17
 */
@Data
@TableName(value = "account")
public class Account implements Serializable {

    private Long id;

    private Double balance;

    private Date lastUpdateTime;

}
