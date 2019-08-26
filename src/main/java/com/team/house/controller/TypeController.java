package com.team.house.controller;

import com.github.pagehelper.PageInfo;
import com.team.house.entity.Type;
import com.team.house.service.TypeService;
import com.team.house.util.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2019-8-13.
 */
@Controller
@RequestMapping("/admin")
public class TypeController {
    
    @Autowired
    private TypeService typeService;

    //分页查询所有 pageHelper插件
    @RequestMapping("/pageShowType")
    @ResponseBody
    public Map<String, Object> getType(PageBean page) {
        PageInfo<Type> info = typeService.pageShow(page);
        //System.out.println(page);
        HashMap<String, Object> map = new HashMap<>();
        map.put("rows", info.getList());
        map.put("total", info.getTotal());
        return map;
    }

    //添加
    @RequestMapping("/addType")
    @ResponseBody
    public String addType(Type type) {
        int temp = -1;
        try {
            temp = typeService.addType(type);
            //System.out.println(temp);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "{\"result\":" + temp + "}";
    }


    //通过id查询信息
    @RequestMapping("/seleteOneType")
    @ResponseBody
    public Type seleteOne(Integer id) {
        return typeService.selectOneType(id);
    }

    //更新
    @RequestMapping("/updateType")
    @ResponseBody
    public String updateType(Type Type) {
        int temp = -1;
        try {
            temp = typeService.updateOneType(Type);
            //System.out.println(temp);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "{\"result\":" + temp + "}";
    }


    //删除单条
    @RequestMapping("/deleteType")
    @ResponseBody
    public String deleteType(Integer id) {
        int temp = -1;
        try {
            temp = typeService.deleteOneType(id);
            //System.out.println(temp);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "{\"result\":" + temp + "}";
    }

    //批量删除
    //delMoreType?id=1&id=2&id=3  = public String delType(Integer []id)
    @RequestMapping("/delMoreType")  //1,2,3
    @ResponseBody
    public String delType(String ids){
        //将字符串转化为数组
        String arys[]=ids.split(",");
        Integer [] is=new Integer[arys.length];
        for (int i=0;i<arys.length;i++){
            is[i]=new Integer(arys[i]);
        }
        //调用业务
        int temp=typeService.delMoreType(is);
        return "{\"result\":"+temp+"}";
    }
}
