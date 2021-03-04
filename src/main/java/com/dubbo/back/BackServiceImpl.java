package com.dubbo.back;

import com.jm.business.entity.Game;
import com.jm.business.entity.User;
import com.jm.business.service.GameService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;


@Service(version = "1.0.0")
public class BackServiceImpl implements BackService {

    @Autowired
    GameService gameService;

    @Override
    public List<User> getUsers()
    {
        User u = User.builder().userName("Hi").build();
        List<User> li = Collections.singletonList(u);
        return li;
    }

    @Override
    public String back(String msg)
    {
        List<Game> li = gameService.list();
        int cnt = li.size();
        return "I'm Back 2 ["+msg+" with cnt:"+cnt+"]";
    }
}
