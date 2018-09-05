package com.qjm.common.base;

import java.io.Serializable;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.entity.Example;

/**
 * @author qianjm
 * @date 2018/9/5
 */
public interface BaseService<T,Pk extends Serializable>{

  /**
   * .新增
   *
   * @param t 实体
   * @return 新增成功纪录数
   */
  int insert(T t);

  /**
   * .根据id更新
   *
   * @param t 实体
   * @return 更新成功纪录数
   */
  int update(T t);

  /**
   * . 根据传入参数，若存在id则更新实体；若id不存在则新增记录
   *
   * @param t 实体 更新成功纪录数
   */
  int saveOrUpdate(T t);

  /**
   * . 根据条件更新
   *
   * @param t 实体
   * @param example 条件
   * @return 更新成功纪录数
   */
  int updateByExample(@Param("record") T t, @Param("example") Object example);

  /**
   * . 删除
   *
   * @param t 实体
   * @return 删除成功纪录数
   */
  int delete(T t);

  /**
   * . 根据id删除 T
   *
   * @param id id
   * @return 删除成功纪录数
   */
  int deleteById(Pk id);

  /**
   * . 根据id逻辑删除 T
   *
   * @param id id
   * @return 删除成功纪录数
   */
  int logicDeleteById(Pk id);

  /**
   * . 根据条件删除
   *
   * @param example 条件
   * @return 更新删除纪录数
   */
  int deleteByExample(Object example);

  /**
   * . 根据id查询
   *
   * @param id id
   * @return 实体
   */
  T findById(Pk id);

  /**
   * . 查询所有
   *
   * @return 实体集合
   */
  List<T> findAll();

  /**
   * . 根据条件查询
   *
   * @param example 条件
   * @return 实体集合
   */
  List<T> findByExample(Object example);

  /**
   * . 根据条件查询纪录数
   *
   * @param example 条件
   * @return 纪录数
   */
  int countByExample(Object example);

  /**
   * . 批量插入实体集合
   *
   * @param ts 实体集合
   * @return 新增成功纪录数
   */
  int insertList(List<T> ts);

  /**
   * . 根据ids删除
   *
   * @param ids ids
   * @return 删除成功纪录数
   */
  int deleteList(long[] ids);

  /**
   * . 根据ids逻辑删除
   *
   * @param ids ids
   * @return 删除成功纪录数
   */
  int logicDeleteList(long[] ids);

  /**
   * . 无力删除
   */
  int deleteAll();

  /**
   * . 创建查询条件实例
   *
   * @return 查询条件实例
   */
  Example createExample();

}
