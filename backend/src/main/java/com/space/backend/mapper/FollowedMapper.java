package com.space.backend.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.space.backend.pojo.Followed;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FollowedMapper extends BaseMapper<Followed> {
}
