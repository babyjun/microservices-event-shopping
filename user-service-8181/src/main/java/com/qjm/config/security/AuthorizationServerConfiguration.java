package com.qjm.config.security;

import com.qjm.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

/**
 * .授权服务
 * @author qianjm
 * @date 2018/9/6
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

  /**
   * .注入
   */
  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private RedisConnectionFactory redisConnectionFactory;

  @Autowired
  private UserServiceImpl userService;

  @Override
  public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
    clients.inMemory().withClient("Web")
        .authorizedGrantTypes("password","refresh_token")
        .scopes("read", "write")
        .authorities("ROLE_WEB")
        .secret("123456").resourceIds("Web");
  }

  /**
   * . 配置授权访问端点和令牌服务
   */
  @Override
  public void configure(AuthorizationServerEndpointsConfigurer endpoints){
    endpoints
        .tokenStore(redisTokenStore())
        .authenticationManager(authenticationManager)
        .tokenServices(tokenServices())
        .userDetailsService(userService);
  }

  @Override
  public void configure(AuthorizationServerSecurityConfigurer security) {
    security.checkTokenAccess("isAuthenticated()");
  }

  /**
   * .注入redisTokenStore，使用redis存储token
   */
  @Bean
  @Primary
  public DefaultTokenServices tokenServices() {
    DefaultTokenServices tokenServices = new DefaultTokenServices();
    tokenServices.setSupportRefreshToken(true);
    tokenServices.setTokenStore(redisTokenStore());
    return tokenServices;
  }

  @Bean
  public TokenStore redisTokenStore(){
    return new RedisTokenStore(redisConnectionFactory);
  }
}
