package com.funtl.itoken.common.service;

import com.funtl.itoken.common.domain.BaseDomain;
import com.github.pagehelper.PageInfo;

/**
 * 通用业务逻辑层
 * @ClassName BaseService
 * @Description TODO
 * @Author kdnight
 * @Date 2019/7/8 15:27
 * @Version 1.0
 **/
public interface BaseService<T extends BaseDomain> {
    int insert(T t, String createBy);

    int delete(T t);

    int update(T t, String updateBy);

    int count(T t);

    T selectOne(T t);

    PageInfo<T> page(int pageNum, int pageSize, T t);
}
