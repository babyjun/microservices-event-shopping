package com.qjm.controller;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;

/**
 * @author qianjm
 * @date 2018/9/21
 */
@EnableBinding(Sink.class)
public class MsgSink {

  @StreamListener(Sink.INPUT)
  public void process(String  msg){
      System.out.println("get kafka message:"+msg);
    }
}
