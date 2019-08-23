package com.team.house.service;

import com.github.pagehelper.PageInfo;
import com.team.house.entity.District;

import java.util.List;

public interface DistrictService {

    /**
     * 查询所有区域
     *
     * @return 区域实体集合
     */
    List<District> getAllDistrict();

    //分页查询所有
    PageInfo<District> pageShow(Integer page, Integer rows);

    //添加
    Integer addDistrict(District district);

    //查询单条
    District selectOneDistrict(Integer id);

    //修改单条
    Integer updateOneDistrict(District district);

    //删除单条
    Integer deleteOneDistrict(Integer id);

    //批量删除
    Integer delMoreDistrict(Integer[] ids);

}
