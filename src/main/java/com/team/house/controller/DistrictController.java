package com.team.house.controller;

import com.github.pagehelper.PageInfo;
import com.team.house.entity.District;
import com.team.house.service.DistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/admin")   //表示后所有的控制器请求都在/admin目录下
public class DistrictController {

    @Autowired
    private DistrictService districtService;

    //查询全部信息
   /* @RequestMapping("/getDistrict")
    @ResponseBody
    public List<District> getDistrict() {
        List<District> list = districtService.getAllDistrict();
        return list;
    }*/

    //分页查询所有 pageHelper插件 加 easyui框架
    @RequestMapping("/pageShow")
    @ResponseBody
    public Map<String, Object> getDistrict(Integer page, Integer rows) {
        PageInfo<District> info = districtService.pageShow(page, rows);
        HashMap<String, Object> map = new HashMap<>();
        //因为用了前端框架easyui 所以返回 rows 和 total
        map.put("rows", info.getList());
        map.put("total", info.getTotal());
        return map;
    }

    //添加
    @RequestMapping("/addDis")
    @ResponseBody
    public String addDistrict(District district) {
        int temp = -1;
        try {
            temp = districtService.addDistrict(district);
        } catch (Exception e) {
            e.printStackTrace();//报错记录日志
        }
        return "{\"result\":" + temp + "}";
    }

    //通过id查询信息
    @RequestMapping("/seleteOne")
    @ResponseBody
    public District seleteOne(Integer id) {
        return districtService.selectOneDistrict(id);
    }

    //更新
    @RequestMapping("/updateDis")
    @ResponseBody
    public String updateDistrict(District district) {
        int temp = -1;
        try {
            temp = districtService.updateOneDistrict(district);
            //System.out.println(temp);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "{\"result\":" + temp + "}";
    }

    //删除单条
    @RequestMapping("/deleteDis")
    @ResponseBody
    public String deleteDistrict(Integer id) {
        int temp = -1;
        try {
            temp = districtService.deleteOneDistrict(id);
            //System.out.println(temp);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "{\"result\":" + temp + "}";
    }

    //批量删除区域
    //delMoreDistrict?id=1&id=2&id=3  = public String delDistrict(Integer []id)
    @RequestMapping("/delMoreDistrict")  //1,2,3
    @ResponseBody
    public String delDistrict(String ids) {
        //将字符串转化为数组
        String arys[] = ids.split(",");
        Integer[] is = new Integer[arys.length];
        for (int i = 0; i < arys.length; i++) {
            is[i]=new Integer(arys[i]);
        }
        //调用业务
        int temp = districtService.delMoreDistrict(is);
        return "{\"result\":" + temp + "}";
    }
}
