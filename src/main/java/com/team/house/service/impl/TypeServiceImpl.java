package com.team.house.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.team.house.entity.Type;
import com.team.house.entity.TypeExample;
import com.team.house.mapper.TypeMapper;
import com.team.house.mapper.StreetMapper;
import com.team.house.service.TypeService;
import com.team.house.util.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Administrator on 2019-8-13.
 */

@Service
public class TypeServiceImpl implements TypeService {
    @Autowired
    private TypeMapper typeMapper;

    @Override
    public PageInfo<Type> pageShow(PageBean page) {
        //开启分页
        PageHelper.startPage(page.getPage(), page.getRows());
        //查询所有
        List<Type> list = typeMapper.selectByExample(null);
        PageInfo<Type> info = new PageInfo<>(list);
        return info;
    }

    @Override
    public Integer addType(Type Type) {
        return typeMapper.insertSelective(Type);
    }

    @Override
    public Type selectOneType(Integer id) {
        return typeMapper.selectByPrimaryKey(id);
    }

    @Override
    public Integer updateOneType(Type type) {
        return typeMapper.updateByPrimaryKeySelective(type);
    }

    //删除区域 有关街道
    @Override
    public Integer deleteOneType(Integer id) {
     return  typeMapper.deleteByPrimaryKey(id);

    }
    
    @Override
    public Integer delMoreType(Integer[] ids) {
        return typeMapper.delMoreType(ids);
    }

    @Override
    public List<Type> getAllType() {
        return typeMapper.selectByExample(null);
    }
}
