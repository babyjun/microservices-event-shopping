package com.qjm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author qianjm
 * @date 2018/9/5
 * .服务发现
 */
@SpringBootApplication
@EnableEurekaServer
public class DiscoveryServiceApplication {

  public static void main(String[] args){
    SpringApplication.run(DiscoveryServiceApplication.class,args);
  }
}
