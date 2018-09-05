package com.qjm.service.impl;

import com.qjm.common.base.BaseMapper;
import com.qjm.common.base.impl.BaseServiceImpl;
import com.qjm.model.generate.User;
import com.qjm.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @author qianjm
 * @date 2018/9/5
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<User,Long> implements UserService {


  public UserServiceImpl(BaseMapper<User> baseMapper) {
    super(baseMapper);
  }
}
