package com.qjm.controller;

import com.qjm.model.generate.User;
import com.qjm.service.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author qianjm
 * @date 2018/9/5
 */
@RestController
@RequestMapping("/users")
public class UserController {

  @Autowired
  UserService userService;

  @GetMapping
  public List<User> findAll(){
    return userService.findAll();
  }
}
