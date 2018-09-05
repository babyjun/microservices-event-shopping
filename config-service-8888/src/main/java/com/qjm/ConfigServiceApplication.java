package com.qjm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * @author qianjm
 * @date 2018/9/5
 */
@SpringBootApplication
@EnableConfigServer
public class ConfigServiceApplication {
  public static void main(String[] args){
    SpringApplication.run(ConfigServiceApplication.class,args);
  }
}
