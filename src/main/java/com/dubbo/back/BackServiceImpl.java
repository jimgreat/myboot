package com.dubbo.back;

import com.business.entity.Game;
import com.business.service.GameService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service(version = "1.0.0")
public class BackServiceImpl implements BackService {

    @Autowired
    GameService gameService;

    @Override
    public String back(String msg) {

        List<Game> li = gameService.list();
        int cnt = li.size();

        return "I'm Back 2 ["+msg+"] with cnt:"+cnt;
    }
}
