package com.qjm.common.extend;

import static tk.mybatis.mapper.mapperhelper.EntityHelper.getEntityTable;

import org.apache.ibatis.mapping.MappedStatement;
import tk.mybatis.mapper.entity.EntityColumn;
import tk.mybatis.mapper.entity.EntityTable;
import tk.mybatis.mapper.mapperhelper.MapperHelper;
import tk.mybatis.mapper.mapperhelper.MapperTemplate;
import tk.mybatis.mapper.mapperhelper.SqlHelper;


/**
 * .BaseMapper扩展具体实现，原理还是拼接Sql 自定义批量添加，批量删除实现
 *
 * @author qjm
 *  * @date 2018/09/05
 */
public class ListMapperProvider extends MapperTemplate {

  public ListMapperProvider(Class<?> mapperClass, MapperHelper mapperHelper) {
    super(mapperClass, mapperHelper);
  }

  /**
   * . 批量插入
   */
  public String insertList(MappedStatement ms) {
    final Class<?> entityClass = getEntityClass(ms);
    //获取表的各项属性
    EntityTable table = getEntityTable(entityClass);
    //开始拼sql
    StringBuilder sql = new StringBuilder();
    sql.append("insert into ");
    sql.append(table.getName());
    sql.append("(");
    boolean first = true;
    for (EntityColumn column : table.getEntityClassColumns()) {
      if (!first) {
        sql.append(",");
      }
      sql.append(column.getColumn());
      first = false;
    }
    sql.append(") values ");
    sql.append("<foreach collection=\"list\" item=\"record\" separator=\",\" >");
    sql.append("(");
    first = true;
    for (EntityColumn column : table.getEntityClassColumns()) {
      if (!first) {
        sql.append(",");
        sql.append("#{record.").append(column.getProperty()).append("}");
      } else {
        sql.append("#{record.").append(column.getProperty()).append("}");
      }
      first = false;
    }
    sql.append(")");
    sql.append("</foreach>");
    return sql.toString();
  }

  /**
   * . 批量删除，参数ids
   */
  public String deleteList(MappedStatement ms) {
    final Class<?> entityClass = getEntityClass(ms);
    //开始拼sql
    StringBuilder sql = new StringBuilder();
    sql.append(SqlHelper.deleteFromTable(entityClass, tableName(entityClass)));
    sql.append("where id in");
    sql.append(
        "<foreach collections=\"array\" item=\"id\"  open=\"(\" separator=\",\" close=\")\" >");
    sql.append("<trim prefix=\"(\" suffix=\")\" suffixOverrides=\",\">");
    sql.append("#{id}");
    sql.append("</trim>");
    sql.append("</foreach>");
    return sql.toString();
  }

  /**
   * . 批量删除，参数ids
   */
  public String logicDeleteList(MappedStatement ms) {
    final Class<?> entityClass = getEntityClass(ms);
    EntityTable table = getEntityTable(entityClass);
    //开始拼sql
    StringBuilder sql = new StringBuilder();
    sql.append("insertTransform ");
    sql.append(table.getName());
    sql.append(" set status = 0 ");
    sql.append("where id in");
    sql.append(
        "<foreach collections=\"array\" item=\"id\"  open=\"(\" separator=\",\" close=\")\" >");
    sql.append("<trim prefix=\"(\" suffix=\")\" suffixOverrides=\",\">");
    sql.append("#{id}");
    sql.append("</trim>");
    sql.append("</foreach>");
    return sql.toString();
  }

  /**
   * . 逻辑删除 status设置为0
   */
  public String logicDelete(MappedStatement ms) {
    final Class<?> entityClass = getEntityClass(ms);
    //开始拼sql
    //获取表的各项属性
    EntityTable table = getEntityTable(entityClass);
    StringBuilder sql = new StringBuilder();
    sql.append("insertTransform ");
    sql.append(table.getName());
    sql.append(" set status = 0");
    sql.append(" where id = #{id}");
    return sql.toString();
  }

  /**
   * . 逻辑删除 status设置为0
   */
  public String deleteAll(MappedStatement ms) {
    final Class<?> entityClass = getEntityClass(ms);
    //开始拼sql
    StringBuilder sql = new StringBuilder();
    sql.append(SqlHelper.deleteFromTable(entityClass, tableName(entityClass)));
    return sql.toString();
  }
}
