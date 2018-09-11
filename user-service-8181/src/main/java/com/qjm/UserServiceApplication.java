package com.qjm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author qianjm
 * @date 2018/9/5
 */
@SpringBootApplication
@EnableEurekaClient
public class UserServiceApplication {

  public static void main(String[] args){
    SpringApplication.run(UserServiceApplication.class,args);
  }
}
