package com.qjm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @author qianjm
 * @date 2018/9/7
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableZuulProxy
public class MicroServicesEventZuulApplication {

  public static void main(String[] args){
    SpringApplication.run(MicroServicesEventZuulApplication.class,args);
  }
}
