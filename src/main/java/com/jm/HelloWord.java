package com.jm;

import com.dubbo.service.DemoService;
import com.jm.business.entity.Game;
import com.jm.business.service.GameService;
import com.dubbo.back.BackService;
import com.jm.ds.DS;
import com.jm.ds.DataSourceContextHolder;
import org.apache.dubbo.config.annotation.DubboReference;
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
    GameService gameService;

    @DubboReference(version = "1.0.0")
    BackService backService;

    @DubboReference(version = "1.0.0")
    DemoService demoService;

//    @RequestMapping("mq")
//    @ResponseBody
//    public Object mq(){
//        try {
//            String name = "VALUE:"+aint.getAndIncrement();
//            Message msg = new Message("TopicTest", "tags1", name.getBytes(RemotingHelper.DEFAULT_CHARSET));
//            defaultMQProducer.send(msg);
//            return "OK";
//        }catch (Exception e){
//            return "ERROR";
//        }
//    }

    @RequestMapping("index")
    @ResponseBody
    public String index(){
        String r = demoService.sayName("from index");
        return r;
    }

    @RequestMapping("")
    @ResponseBody
    @DS(value = "hi")
    public String hello(@RequestParam(name = "msg",defaultValue = "") String msg) {
        logger.info("hi");
        String r = backService.back(" from SpringBoot!");
        List<Game> li = gameService.list();
        int cnt = li.size();
        return "hello,Spring boot!"+DataSourceContextHolder.getDB()+" "+r+" with cnt:"+cnt;
    }

}
