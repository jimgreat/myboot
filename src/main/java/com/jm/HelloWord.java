package com.jm;

import com.business.entity.Game;
import com.business.service.GameService;
import com.dubbo.back.BackService;
import com.jm.ds.DS;
import com.jm.ds.DataSourceContextHolder;
import com.jm.service.ICityService;
import org.apache.dubbo.config.annotation.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/")
public class HelloWord {

    private static Logger logger = LoggerFactory.getLogger(HelloWord.class);

    @Autowired
    ICityService cityService;

    @Autowired
    GameService gameService;

    @Reference(version = "1.0.0")
    private BackService backService;

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

        String r = backService.back(" from SpringBoot!");

        List<Game> li = gameService.list();
        int cnt = li.size();

        return "hello,Spring boot!"+DataSourceContextHolder.getDB()+" "+r+" cnt:"+cnt;
    }


}
