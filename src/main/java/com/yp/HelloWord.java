package com.yp;

import com.yp.ds.DS;
import com.yp.ds.DataSourceContextHolder;
import com.yp.service.ICityService;
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
        for(int i=0;i<1;i++) {
            City c = new City();
            c.setName("City-"+i);
        }
        cityService.findCity("CN");
        return "hello,Spring boot!"+DataSourceContextHolder.getDB();
    }


}
