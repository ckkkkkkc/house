package com.team.house.service;

import com.github.pagehelper.PageInfo;
import com.team.house.entity.Users;
import com.team.house.util.UsersCondition;

/**
 * Created by Administrator on 2019-8-15.
 */
public interface UsersService {

    //条件查询所有用户  查询所有用户
    //查询条件和 分页条件存储在查询工具类 包含 page rows
    PageInfo<Users> getAllUserInfo(UsersCondition usersCondition);

    //注册用户 添加功能
    int regUser(Users user);

    /**
     * 检查用户名是否存在
     *
     * @param name 用户名
     * @return 总行数  如果返回1说明存在,返回0说明不存在
     */
    public int isUserNameExists(String name);

    /**
     * 登入功能
     *
     * @param username 用户名称
     * @param password 密码
     * @return 用户对象
     */
    public Users login(String username, String password);

}
