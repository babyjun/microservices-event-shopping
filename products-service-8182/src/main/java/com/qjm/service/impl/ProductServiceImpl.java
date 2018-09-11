package com.qjm.service.impl;

import com.qjm.common.base.BaseMapper;
import com.qjm.common.base.impl.BaseServiceImpl;
import com.qjm.model.generate.Products;
import com.qjm.service.ProductService;
import org.springframework.stereotype.Service;

/**
 * @author qianjm
 * @date 2018/9/7
 */
@Service
public class ProductServiceImpl extends BaseServiceImpl<Products, Long> implements ProductService {

  public ProductServiceImpl(BaseMapper<Products> baseMapper) {
    super(baseMapper);
  }
}
