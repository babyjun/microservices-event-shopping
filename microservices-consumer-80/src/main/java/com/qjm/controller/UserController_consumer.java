package com.qjm.controller;

import com.qjm.common.base.BaseController;
import com.qjm.service.UserClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author qianjm
 * @date 2018/9/10
 */
@RestController
@RequestMapping("/consumer")
public class UserController_consumer extends BaseController {

  @Autowired
  UserClientService userClientService;

  @GetMapping("/users")
  public ResponseEntity<?> findAll(){
    return buildOk(userClientService.findAll());
  }

  @GetMapping("/users/hello")
  public String helloWord(){
    return userClientService.helloWord();
  }
}
