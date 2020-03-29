package com.jm.business.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jm.business.entity.Game;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

public interface GameMapper extends BaseMapper<Game> {
    Page<Map<String, Object>> list(@Param("page") Page page);

}
