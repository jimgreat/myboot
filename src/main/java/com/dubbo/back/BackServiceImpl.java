package com.dubbo.back;

import com.jm.business.entity.Game;
import com.jm.business.entity.User;
import com.jm.business.service.GameService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.List;


@Service(version = "1.0.0")
public class BackServiceImpl implements BackService {

    @Autowired
    GameService gameService;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public List<User> getUsers()
    {
        User u = User.builder().userName("Hi").build();
        mongoTemplate.insert(u);
        List<User> li = mongoTemplate.findAll(User.class);
        return li;
    }

    @Override
    public String back(String msg)
    {
        String key = "KEY";
        stringRedisTemplate.opsForValue().set(key,"vvv");

        List<Game> li = gameService.list();
        int cnt = li.size();

        String v = stringRedisTemplate.opsForValue().get(key);

        return "I'm Back 2 ["+msg+" with cnt:"+cnt+" redis v:"+v+"]";
    }
}
