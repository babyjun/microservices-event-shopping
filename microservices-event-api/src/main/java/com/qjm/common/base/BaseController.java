package com.qjm.common.base;

import java.net.URI;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 * @author qianjm
 * @date 2018/9/7
 */
public class BaseController {

  protected ResponseEntity<?> buildCreate(String name){
    return ResponseEntity.created(createLocation()).body(name);
  }

  protected <T>ResponseEntity<?> buildCreate(T t){
    return ResponseEntity.status(201).body(t);
  }

  protected ResponseEntity<?> buildOk(){
    return ResponseEntity.ok().build();
  }

  protected <T>ResponseEntity<?> buildOk(T t){
    if(t==null){
      return null;
    }
    return ResponseEntity.ok(t);
  }
  /**
   * . create location
   *
   * @return URI location
   */
  URI createLocation() {
    return ServletUriComponentsBuilder
        .fromCurrentRequest()
        .build()
        .toUri();
  }
}
