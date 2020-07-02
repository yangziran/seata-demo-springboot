package cn.kunter.seata.at.demo;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main
 * @author nature
 * @version 1.0 2020/6/17
 */
@SpringBootApplication(exclude = DruidDataSourceAutoConfigure.class)
@MapperScan("cn.kunter.seata.at.demo.dao")
public class SeataAtDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SeataAtDemoApplication.class, args);
    }

}
