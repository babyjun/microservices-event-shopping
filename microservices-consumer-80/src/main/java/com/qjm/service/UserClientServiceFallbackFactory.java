package com.qjm.service;

import com.qjm.common.pojo.User;
import feign.hystrix.FallbackFactory;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 * @author qianjm
 * @date 2018/9/10
 */
@Component
public class UserClientServiceFallbackFactory implements FallbackFactory<UserClientService> {

  public UserClientService create(Throwable throwable) {
    return new UserClientService() {
      public List<User> findAll() {
        List<User> list = new ArrayList<User>();
        User user = new User();
        user.setUsername("查询信息为空!");
        list.add(user);
        return list;
      }

      public String helloWord() {
        return null;
      }
    };
  }
}
