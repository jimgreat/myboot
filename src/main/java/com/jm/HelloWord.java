package com.jm;

import com.dubbo.back.BackService;
import com.dubbo.service.DemoService;
import com.jm.ds.DS;
import com.jm.ds.DataSourceContextHolder;
import com.jm.service.ICityService;
import org.apache.dubbo.config.annotation.Method;
import org.apache.dubbo.config.annotation.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/")
public class HelloWord {

    private static Logger logger = LoggerFactory.getLogger(HelloWord.class);

    @Autowired
    ICityService cityService;

    @Reference(version = "1.0.0")
    private BackService backService;

    @Reference(version = "${demo.service.version}")
    private DemoService demoService;

    @RequestMapping("index")
    @ResponseBody
    public String index(){
        return "Index";
    }

    @RequestMapping("")
    @ResponseBody
    @DS(value = "hi")
    public String hello(@RequestParam(name = "msg",defaultValue = "") String msg) {
        logger.info("hi");
//        for(int i=0;i<1;i++) {
//            City c = new City();
//            c.setName("City-"+i);
//        }
//        cityService.findCity("CN");

        String r = backService.back(" from SpringBoot!");

        String str = demoService.sayName("jimgreat");

        return "hello,Spring boot!"+DataSourceContextHolder.getDB()+" "+r+" "+str;
    }


}
