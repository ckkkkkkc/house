package com.team.house.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.team.house.entity.Street;
import com.team.house.mapper.StreetMapper;
import com.team.house.service.StreetService;
import com.team.house.util.PageBean;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by Administrator on 2019-8-13.
 */
public class StreetServiceImpl implements StreetService {
    @Autowired
    private StreetMapper streetMapper;

    @Override
    public PageInfo<Street> pageShow(PageBean page) {
        //开启分页
        PageHelper.startPage(page.getPage(), page.getRows());
        //查询所有
        List<Street> list = streetMapper.selectByExample(null);
        PageInfo<Street> info = new PageInfo<>(list);
        return info;
    }

    @Override
    public Integer addStreet(Street Street) {
        return streetMapper.insertSelective(Street);
    }

    @Override
    public Street selectOneStreet(Integer id) {
        return streetMapper.selectByPrimaryKey(id);
    }

    @Override
    public Integer updateOneStreet(Street Street) {
        return streetMapper.updateByPrimaryKeySelective(Street);
    }

    //删除区域 有关街道
    @Override
    public Integer deleteOneStreet(Integer id) {
        return streetMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Integer delMoreStreet(Integer[] ids) {
        return streetMapper.delMoreStreet(ids);
    }
}
