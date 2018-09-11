package com.qjm.controller;

import com.qjm.common.base.BaseController;
import com.qjm.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author qianjm
 * @date 2018/9/7
 */
@RestController
@RequestMapping("/pruducts")
public class ProductController extends BaseController {

  @Autowired
  ProductService productService;

  @GetMapping
  public ResponseEntity<?> findAll() {
    return buildOk(productService.findAll());
  }
}
