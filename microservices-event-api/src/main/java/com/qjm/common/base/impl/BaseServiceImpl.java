package com.qjm.common.base.impl;

import com.qjm.common.base.BaseMapper;
import com.qjm.common.base.BaseService;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ClassUtils;
import tk.mybatis.mapper.entity.Example;

/**
 * @author qianjm
 * @date 2018/9/5
 */
public abstract class BaseServiceImpl<T, Pk extends Serializable> implements BaseService<T, Pk> {

  protected final Logger logger = LoggerFactory.getLogger(this.getClass());

  @Autowired
  BaseMapper<T> baseMapper;

  /**
   * . 泛型类型
   */
  private Class<T> modelClass;

  public BaseServiceImpl(BaseMapper<T> baseMapper) {
    //返回当前类的父类Type
    // eg:UserServiceImpl extends BaseServiceImpl(User,Long)，获取的是BaseServiceImpl<User,Long>
    Type type = this.getClass().getGenericSuperclass();
    //返回表示此类型实际类型参数的 Type 对象的数组,以上例子返回User
    this.modelClass = (Class<T>) ((ParameterizedType) type).getActualTypeArguments()[0];
    this.baseMapper = baseMapper;
  }

  @Override
  public int insert(T t) {
    return baseMapper.insertSelective(t);
  }

  @Override
  public int update(T t) {
    return baseMapper.updateByPrimaryKeySelective(t);
  }

  @Override
  public int saveOrUpdate(T t) {
    int result = -1;
    if (isSaveNew(t)) {
      result = baseMapper.insert(t);
    } else {
      result = baseMapper.updateByPrimaryKey(t);
    }
    return result;
  }

  @Override
  public int updateByExample(@Param("record") T t, @Param("example") Object example) {
    return baseMapper.updateByExampleSelective(t, example);
  }

  @Override
  public int delete(T t) {
    return baseMapper.delete(t);
  }

  @Override
  public int deleteById(Pk id) {
    return baseMapper.deleteByPrimaryKey(id);
  }

  @Override
  public int logicDeleteById(Pk id) {
    return baseMapper.logicDelete((Long) id);
  }

  @Override
  public int deleteByExample(Object example) {
    return baseMapper.deleteByExample(example);
  }

  @Override
  public T findById(Pk id) {
    return baseMapper.selectByPrimaryKey(id);
  }

  @Override
  public List<T> findAll() {
    return baseMapper.selectAll();
  }

  @Override
  public List<T> findByExample(Object example) {
    return baseMapper.selectByExample(example);
  }

  @Override
  public int countByExample(Object example) {
    return baseMapper.selectCountByExample(example);
  }

  @Override
  public int insertList(List<T> ts) {
    return baseMapper.insertList(ts);
  }

  @Override
  public int deleteList(long[] ids) {
    return baseMapper.deleteList(ids);
  }

  @Override
  public int logicDeleteList(long[] ids) {
    return baseMapper.logicDeleteList(ids);
  }

  @Override
  public int deleteAll() {
    return baseMapper.deleteAll();
  }

  @Override
  public Example createExample() {
    return new Example(modelClass);
  }

  /**
   * . 根据t判定是否需要保存为新的记录。 <br/> 通常情况下不用重写，如有需要可在子服务实现里重写该方法。
   *
   * @param t 数据对象
   * @return t.getId()== null ? true:false
   */
  protected boolean isSaveNew(final T t) {
    Method method = ClassUtils.getMethod(t.getClass(), "getId", new Class[]{});
    Object o = null;
    try {
      o = method.invoke(t, new Object[0]);
    } catch (IllegalAccessException e) {
      e.printStackTrace();
    } catch (InvocationTargetException e) {
      e.printStackTrace();
    }
    boolean r = false;
    if (o == null || o.toString().trim().length() == 0) {
      r = true;
    }
    return r;
  }
}
