package com.qjm.controller;

import com.qjm.common.base.BaseController;
import com.qjm.model.generate.User;
import com.qjm.service.UserService;
import java.security.Principal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sun.security.util.Password;

/**
 * @author qianjm
 * @date 2018/9/5
 */
@RestController
@RefreshScope
@RequestMapping("/users")
public class UserController extends BaseController {

  @Autowired
  UserService userService;

  @Autowired
  PasswordEncoder passwordEncoder;

  @Value("${name}")
  private String name;

  @GetMapping("/hello")
  public String helloWord(){
    return "HelloWorld"+name;
  }

  @GetMapping
  public List<User> findAll(){
    return userService.findAllUser();
  }

  @PostMapping
  public ResponseEntity<?> save(@RequestBody User user){
    user.setPassword(passwordEncoder.encode("123456"));
    return buildCreate(userService.insert(user));
  }

  @GetMapping("/user")
  public Principal user(Principal user){
    return user;
  }
}
