package com.team.house.potal.controller;

import com.team.house.entity.Type;
import com.team.house.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by Administrator on 2019-8-16.
 */

@RequestMapping("/page")
@Controller(value = "typeController2")
public class TypeController {

    @Autowired
    private TypeService typeService;

    @RequestMapping("/getAllType")
    @ResponseBody
    public List<Type> getAllType(){
        return typeService.getAllType();
    }
}
