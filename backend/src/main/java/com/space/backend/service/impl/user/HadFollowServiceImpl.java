package com.space.backend.service.impl.user;

import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.space.backend.mapper.FollowedMapper;
import com.space.backend.pojo.Followed;
import com.space.backend.service.user.HadFollowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HadFollowServiceImpl implements HadFollowService {

    @Autowired
    private FollowedMapper followedMapper;

    @Override
    public JSONObject hadFollow(Integer user1Id, Integer user2Id) {
        QueryWrapper<Followed> queryWrapper = new QueryWrapper<>();

        queryWrapper.eq("user_id", user1Id).eq("focus_user_id", user2Id);

        Followed followed = followedMapper.selectOne(queryWrapper);

        JSONObject resp = new JSONObject();
        if(followed == null){
            resp.put("error_message", "error");
            return resp;
        }
        resp.put("error_message", "success");

        return resp;
    }
}
