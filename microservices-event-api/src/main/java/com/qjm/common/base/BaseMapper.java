package com.qjm.common.base;

import com.qjm.common.extend.ListMapper;
import tk.mybatis.mapper.common.Mapper;

/**
 * @author qianjm
 * @date 2018/9/5
 */
public interface BaseMapper<T> extends Mapper<T>, ListMapper<T> {

}
