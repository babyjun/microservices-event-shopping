package com.qjm.service;

import com.qjm.common.config.feign.FeignConfig;
import com.qjm.common.pojo.User;
import java.util.List;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author qianjm
 * @date 2018/9/10
 */
@FeignClient(value = "MICROSERVICES-EVENT-SHOPPING-USER")
public interface UserClientService {

  @GetMapping(value = "/users")
  public List<User> findAll();

  @GetMapping("/users/hello")
  public String helloWord();
}
