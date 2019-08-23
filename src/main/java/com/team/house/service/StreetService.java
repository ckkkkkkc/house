package com.team.house.service;

import com.github.pagehelper.PageInfo;
import com.team.house.entity.Street;
import com.team.house.util.PageBean;

/**
 * Created by Administrator on 2019-8-13.
 */

public interface StreetService {

    //分页查询所有
    PageInfo<Street> pageShow(PageBean page);

    //添加
    Integer addStreet(Street street);

    //查询单条
    Street selectOneStreet(Integer id);

    //修改单条
    Integer updateOneStreet(Street street);

    //删除单条
    Integer deleteOneStreet(Integer id);

    //批量删除
    Integer delMoreStreet(Integer[] ids);

}

