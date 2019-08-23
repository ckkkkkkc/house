package com.team.house.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.team.house.entity.Users;
import com.team.house.entity.UsersExample;
import com.team.house.mapper.UsersMapper;
import com.team.house.service.UsersService;
import com.team.house.util.MD5Utils;
import com.team.house.util.UsersCondition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2019-8-15.
 */

@Service
public class UsersServiceImpl implements UsersService {

    @Autowired
    private UsersMapper usersMapper;

    @Override
    public PageInfo<Users> getAllUserInfo(UsersCondition usersCondition) {
        //开启分页
        PageHelper.startPage(usersCondition.getPage(),usersCondition.getRows());
        UsersExample e = new UsersExample();
        UsersExample.Criteria c = e.createCriteria();
        //条件查询
        //根据姓名
        if(usersCondition.getName()!=null){
            c.andNameLike("%"+usersCondition.getName()+"%");
        }
        //根据电话号码
        if(usersCondition.getTelephone()!=null){
            c.andTelephoneLike("%"+usersCondition.getTelephone()+"%");
        }
        //查询全部用户信息
        List<Users> list = usersMapper.selectByExample(e);
        PageInfo<Users> info = new PageInfo<>(list);
        return info;
    }

    @Override
    public int regUser(Users user) {
        //设置注册用户
        user.setIsadmin(new Integer("0"));
        //采用md5对用户输入的密码进行加密
        user.setPassword(MD5Utils.md5Encrypt(user.getPassword()));
        return usersMapper.insertSelective(user);
    }

    @Override
    public int isUserNameExists(String name) {
        return usersMapper.getUserCount(name);
    }

    @Override
    public Users login(String username, String password) {
        UsersExample usersExample=new UsersExample();
        UsersExample.Criteria criteria = usersExample.createCriteria();
        //指定注册用户
        //criteria.andIsadminNotEqualTo(0);
        //指定用户名密码
        criteria.andNameEqualTo(username);
        criteria.andPasswordEqualTo(MD5Utils.md5Encrypt(password));//加密
        //执行
        List<Users> list=usersMapper.selectByExample(usersExample);
        if(list.size()==0)
            return null;
        else
            return list.get(0);
    }
}
