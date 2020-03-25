package com.jm;

import com.jm.service.IMyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/login")
public class LoginController implements ApplicationContextAware {

    private ApplicationContext ac;

    @Value("${server.port}")
    private Integer port;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ac=applicationContext;

        Environment env = ac.getEnvironment();
        String p = env.resolvePlaceholders("${server.port}");
        System.out.println(p);
    }

    private static Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    IMyService myService;

//    @Autowired
//    MyMonitor myMonitor;

//    @RequestMapping("/succ")
//    @ResponseBody
//    public String succ(){
//        return "succ"+myMonitor.getName();
//    }


    @RequestMapping("/log")
    @ResponseBody
    public String log(){
        myService.t();
        return "log";
    }

}
