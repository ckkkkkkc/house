package com.team.house;

import com.team.house.service.DistrictService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Administrator on 2019-8-20.
 */

//事务的一致性  测试类 --  删除区域关联街道
public class Test {
    public static void main(String[] args){
        ApplicationContext ctx=new ClassPathXmlApplicationContext("applicationContext.xml");
        DistrictService districtService=(DistrictService) ctx.getBean("districtServiceImpl");
        districtService.deleteOneDistrict(1029);
    }
}
