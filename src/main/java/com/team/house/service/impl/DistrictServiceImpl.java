package com.team.house.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.team.house.entity.District;
import com.team.house.entity.DistrictExample;
import com.team.house.mapper.DistrictMapper;
import com.team.house.mapper.StreetMapper;
import com.team.house.service.DistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author 王建兵
 * @Classname DistrictServiceImpl
 * @Description TODO
 * @Date 2019/8/9 11:15
 * @Created by Administrator
 */

@Service
public class DistrictServiceImpl implements DistrictService {

    @Autowired
    private DistrictMapper districtMapper;
    @Autowired
    private StreetMapper streetMapper;

   /* @Override
    public List<District> getAllDistrict() {
        //封装条件实体类
        DistrictExample districtExample = new DistrictExample();
        List<District> list = districtMapper.selectByExample(districtExample);
        return list;
    }*/

    @Override
    public PageInfo<District> pageShow(Integer page, Integer rows) {
        //开启分页
        PageHelper.startPage(page, rows);
        //查询所有
        List<District> list = districtMapper.selectByExample(null);
        PageInfo<District> info = new PageInfo<>(list);
        return info;
    }

    @Override
    public Integer addDistrict(District district) {
        return districtMapper.insertSelective(district);
    }

    @Override
    public District selectOneDistrict(Integer id) {
        return districtMapper.selectByPrimaryKey(id);
    }

    @Override
    public Integer updateOneDistrict(District district) {
        return districtMapper.updateByPrimaryKeySelective(district);
    }

    //删除区域 有关街道
    @Override
    @Transactional
    public Integer deleteOneDistrict(Integer id) {
        //通过区域编号删除街道 事务不能加try..catch.. 否则无法生效
        streetMapper.deleteStreetByDistrictId(id);
       //模拟异常 事务要么成功 要么失败
        /* int a=1;
        int b=0;
        int c=a/b;
        System.out.println(c);*/
        districtMapper.deleteByPrimaryKey(id);
        return 1;
    }

    @Override
    public Integer delMoreDistrict(Integer[] ids) {
        return districtMapper.delMoreDistrict(ids);
    }

}
