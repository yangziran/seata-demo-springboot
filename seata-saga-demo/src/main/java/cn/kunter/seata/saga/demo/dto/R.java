package cn.kunter.seata.saga.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 操作响应对象
 * @author nature
 * @version 1.0 2020/6/17
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class R implements Serializable {

    private boolean success;

    private String message;

    private Object data;

}
