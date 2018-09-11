package com.qjm.service.impl;

import com.qjm.common.base.BaseMapper;
import com.qjm.common.base.impl.BaseServiceImpl;
import com.qjm.model.generate.Roles;
import com.qjm.model.generate.User;
import com.qjm.service.RoleService;
import com.qjm.service.UserService;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

/**
 * @author qianjm
 * @date 2018/9/5
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl extends BaseServiceImpl<User, Long> implements UserService,
    UserDetailsService {

  public Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

  @Autowired
  RoleService roleService;

  public UserServiceImpl(BaseMapper<User> baseMapper) {
    super(baseMapper);
  }

  @Cacheable(value = "userAll", keyGenerator = "keyGenerator")
  public List<User> findAllUser() {
    return this.findAll();
  }


  @Cacheable(value = "currentUser", key = "'UserServiceImpl.findByPhone_'+#phone")
  public User findByPhone(String phone) {
    Example example = new Example(User.class);
    example.createCriteria().andEqualTo("phone", phone);
    List<User> userList = this.findByExample(example);
    User user = null;
    if (userList != null && userList.size() > 0) {
      user = userList.get(0);
    }
    return user;
  }

  public UserDetails loadUserByUsername(String phone) throws UsernameNotFoundException {
    User user = findByPhone(phone);
    List<GrantedAuthority> authorities;
    if (user == null) {
      logger.info("用户名或密码错误");
      throw new UsernameNotFoundException("用户或密码错误");
    }
    List<Roles> roles = roleService.findByUserId(user.getId());
    authorities = new ArrayList<GrantedAuthority>();
    if (roles != null && roles.size() > 0) {
      for (Roles role : roles) {
        authorities.add(new SimpleGrantedAuthority(role.getCode()));
      }
    }
    return new org.springframework.security.core.userdetails.User(user.getPhone(),
        user.getPassword(), authorities);
  }


}
