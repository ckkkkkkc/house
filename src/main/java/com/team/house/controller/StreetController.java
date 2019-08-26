package com.team.house.controller;

import com.github.pagehelper.PageInfo;
import com.team.house.entity.Street;
import com.team.house.service.StreetService;
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
public class StreetController {
    
    @Autowired
    private StreetService streetService;

    //分页查询所有 pageHelper插件
    @RequestMapping("/pageShowStreet")
    @ResponseBody
    public Map<String, Object> getStreet(PageBean page) {
        PageInfo<Street> info = streetService.pageShow(page);
        //System.out.println(page);
        HashMap<String, Object> map = new HashMap<>();
        map.put("rows", info.getList());
        map.put("total", info.getTotal());
        return map;
    }

    //添加
    @RequestMapping("/addStreet")
    @ResponseBody
    public String addStreet(Street Street) {
        int temp = -1;
        try {
            temp = streetService.addStreet(Street);
            //System.out.println(temp);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "{\"result\":" + temp + "}";
    }


    //通过id查询信息
    @RequestMapping("/seleteOneStreet")
    @ResponseBody
    public Street seleteOne(Integer id) {
        return streetService.selectOneStreet(id);
    }

    //更新
    @RequestMapping("/updateStreet")
    @ResponseBody
    public String updateStreet(Street Street) {
        int temp = -1;
        try {
            temp = streetService.updateOneStreet(Street);
            //System.out.println(temp);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "{\"result\":" + temp + "}";
    }


    //删除单条
    @RequestMapping("/deleteStreet")
    @ResponseBody
    public String deleteStreet(Integer id) {
        int temp = -1;
        try {
            temp = streetService.deleteOneStreet(id);
            //System.out.println(temp);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "{\"result\":" + temp + "}";
    }

    //批量删除
    //delMoreStreet?id=1&id=2&id=3  = public String delStreet(Integer []id)
    @RequestMapping("/delMoreStreet")  //1,2,3
    @ResponseBody
    public String delStreet(String ids){
        //将字符串转化为数组
        String arys[]=ids.split(",");
        Integer [] is=new Integer[arys.length];
        for (int i=0;i<arys.length;i++){
            is[i]=new Integer(arys[i]);
        }
        //调用业务
        int temp=streetService.delMoreStreet(is);
        return "{\"result\":"+temp+"}";
    }
}
