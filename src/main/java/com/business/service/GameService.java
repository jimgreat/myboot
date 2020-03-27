package com.business.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.business.entity.Game;
import com.business.mapper.GameMapper;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class GameService extends ServiceImpl<GameMapper,Game> {
    public Page<Map<String, Object>> list(String condition) {
        Page page = new Page(1, 20);
        return this.baseMapper.list(page);
    }
}
