package com.qjm.service.impl;

import com.qjm.mapper.extend.RoleMapperEx;
import com.qjm.model.generate.Roles;
import com.qjm.service.RoleService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author qianjm
 * @date 2018/9/6
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class RoleServiceImpl implements RoleService {

  @Autowired
  RoleMapperEx rolesMapperEx;

  public List<Roles> findByUserId(Long userId) {
    return rolesMapperEx.findByUserId(userId);
  }
}
