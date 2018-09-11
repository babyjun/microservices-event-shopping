package com.qjm.mapper.extend;

import com.qjm.model.generate.Roles;
import java.util.List;

/**
 * @author qianjm
 * @date 2018/9/6
 */
public interface RoleMapperEx {

  List<Roles> findByUserId(Long userId);
}
