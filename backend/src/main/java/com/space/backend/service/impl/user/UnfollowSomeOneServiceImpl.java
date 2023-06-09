package com.space.backend.service.impl.user;

import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.space.backend.mapper.FollowedMapper;
import com.space.backend.mapper.UserMapper;
import com.space.backend.pojo.Followed;
import com.space.backend.pojo.User;
import com.space.backend.service.impl.utils.UserDetailsImpl;
import com.space.backend.service.user.UnfollowSomeOneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UnfollowSomeOneServiceImpl implements UnfollowSomeOneService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private FollowedMapper followedMapper;

    @Override
    public JSONObject unfollowSomeOne(Integer user1Id, Integer user2Id) {

        QueryWrapper<Followed> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", user1Id).eq("focus_user_id", user2Id);
        followedMapper.delete(queryWrapper);

        QueryWrapper<User> queryWrapper2 = new QueryWrapper<>();
        queryWrapper2.eq("id", user2Id);
        User user = userMapper.selectOne(queryWrapper2);
        user.setFollowCount(user.getFollowCount() - 1);
        userMapper.update(user, queryWrapper2);


        JSONObject resp = new JSONObject();
        resp.put("error_message", "success");

        return resp;
    }
}
