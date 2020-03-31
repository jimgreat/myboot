package com.jm;

import com.jm.business.entity.Game;
import com.jm.business.entity.User;
import com.jm.business.service.GameService;
import com.dubbo.back.BackService;
import com.jm.core.DefaultConsumerMQ;
import com.jm.ds.DS;
import com.jm.ds.DataSourceContextHolder;
import com.jm.service.ICityService;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
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

    @Autowired
    DefaultMQProducer defaultMQProducer;

    @RequestMapping("mq")
    @ResponseBody
    public Object mq(){
        try {
            String name = "VALUE";
            Message msg = new Message("TopicTest", "tags1", name.getBytes(RemotingHelper.DEFAULT_CHARSET));
            defaultMQProducer.send(msg);
            return "OK";
        }catch (Exception e){
            return "ERROR";
        }
    }

    @RequestMapping("index")
    @ResponseBody
    public List<User> index(){
        return backService.getUsers();
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

        List<Game> li = gameService.list();
        int cnt = li.size();

        return "hello,Spring boot!"+DataSourceContextHolder.getDB()+" "+r+" with cnt:"+cnt;
    }


}
