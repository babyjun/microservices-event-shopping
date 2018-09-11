package com.qjm.service;

import com.qjm.model.generate.Roles;
import java.util.List;

/**
 * @author qianjm
 * @date 2018/9/6
 */
public interface RoleService {

  List<Roles> findByUserId(Long userId);
}
