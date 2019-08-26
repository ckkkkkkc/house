package com.team.house.service;

import com.github.pagehelper.PageInfo;
import com.team.house.entity.Type;
import com.team.house.util.PageBean;

import java.util.List;

/**
 * 房屋类型模块
 */
public interface TypeService {

    //分页查询所有
    PageInfo<Type> pageShow(PageBean page);

    //添加
    Integer addType(Type type);

    //查询单条
    Type selectOneType(Integer id);

    //修改单条
    Integer updateOneType(Type type);

    //删除单条
    Integer deleteOneType(Integer id);

    //批量删除
    Integer delMoreType(Integer[] ids);

    //查询所有
    List<Type> getAllType();

}

