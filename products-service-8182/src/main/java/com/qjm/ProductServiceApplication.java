package com.qjm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author qianjm
 * @date 2018/9/7
 */
@SpringBootApplication
@EnableEurekaClient
public class ProductServiceApplication {

  public static void main(String[] args){
    SpringApplication.run(ProductServiceApplication.class,args);
  }
}
