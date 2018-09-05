package com.qjm.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author qianjm
 * @date 2018/9/5
 */
@Configuration
@MapperScan("com.qjm.mapper")
public class MapperConfig {

}
