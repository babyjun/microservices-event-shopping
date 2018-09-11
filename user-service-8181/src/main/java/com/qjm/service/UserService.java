package com.qjm.service;

import com.qjm.common.base.BaseService;
import com.qjm.model.generate.User;
import java.util.List;

/**
 * @author qianjm
 * @date 2018/9/5
 */
public interface UserService extends BaseService<User,Long> {

  List<User> findAllUser();

  User findByPhone(String phone);
}
