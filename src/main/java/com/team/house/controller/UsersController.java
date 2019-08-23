package com.team.house.controller;

import com.github.pagehelper.PageInfo;
import com.team.house.entity.Users;
import com.team.house.service.UsersService;
import com.team.house.util.UsersCondition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2019-8-15.
 */

@Controller
@RequestMapping("/admin")
public class UsersController {

    @Autowired
    private UsersService service;

    @RequestMapping("/getUsers")
    @ResponseBody
    public Map<String,Object> getAllUsersInfo(UsersCondition usersCondition){
        //调用service层
        PageInfo<Users> info = service.getAllUserInfo(usersCondition);
        //返回 分页两个参数 total rows
        HashMap<String, Object> map = new HashMap<>();
        map.put("total",info.getTotal());
        map.put("rows",info.getList());
        return map;
    }
}
