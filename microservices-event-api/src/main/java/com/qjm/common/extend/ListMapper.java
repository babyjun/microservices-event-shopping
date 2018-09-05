package com.qjm.common.extend;

import java.util.List;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.UpdateProvider;


/**
 * . BaseMapper扩展 自定义批量插入，批量逻辑删除，物理删除
 *
 * @author qjm
 * @date 2018/09/05
 */
public interface ListMapper<T> {

  /**
   * . mybatis 通用mapper 批量添加扩展
   *
   * @param recordList 实体类型集合
   */
  @Options(useGeneratedKeys = true, keyProperty = "id")
  @InsertProvider(type = ListMapperProvider.class, method = "dynamicSQL")
  int insertList(List<T> recordList);

  /**
   * . mybatis 通用mapper 批量添加扩展
   *
   * @param ids array ids集合
   */
  @Options(useGeneratedKeys = true, keyProperty = "id")
  @DeleteProvider(type = ListMapperProvider.class, method = "dynamicSQL")
  int deleteList(long[] ids);

  /**
   * . mybatis 通用mapper 批量逻辑删除扩展
   *
   * @param ids array ids集合
   */
  @Options(useGeneratedKeys = true, keyProperty = "id")
  @DeleteProvider(type = ListMapperProvider.class, method = "dynamicSQL")
  int logicDeleteList(long[] ids);

  /**
   * . mybatis 通用mapper 逻辑删除扩展
   */
  @Options(useGeneratedKeys = true, keyProperty = "id")
  @UpdateProvider(type = ListMapperProvider.class, method = "dynamicSQL")
  int logicDelete(long id);

  /**
   * . mybatis 通用mapper 删除所有
   */
  @Options(useGeneratedKeys = true, keyProperty = "id")
  @UpdateProvider(type = ListMapperProvider.class, method = "dynamicSQL")
  int deleteAll();
}
