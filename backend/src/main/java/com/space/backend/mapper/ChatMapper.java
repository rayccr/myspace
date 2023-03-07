package com.space.backend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.space.backend.pojo.Chat;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ChatMapper extends BaseMapper<Chat> {
}
