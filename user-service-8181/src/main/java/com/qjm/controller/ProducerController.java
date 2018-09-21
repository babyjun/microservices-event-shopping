package com.qjm.controller;

import com.qjm.service.SendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author qianjm
 * @date 2018/9/21
 */
@RestController
public class ProducerController {

  @Autowired
  SendService service;

  @RequestMapping(value = "/send/{msg}",method = RequestMethod.GET)
  public void send(@PathVariable("msg") String msg){
    service.sendMessage(msg);
  }
}
