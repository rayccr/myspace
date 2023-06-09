package com.space.backend.service.impl.user;

import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.space.backend.mapper.FollowedMapper;
import com.space.backend.mapper.UserMapper;
import com.space.backend.pojo.Followed;
import com.space.backend.pojo.User;
import com.space.backend.service.impl.utils.UserDetailsImpl;
import com.space.backend.service.user.FollowSomeOneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class FollowSomeOneServiceImpl implements FollowSomeOneService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private FollowedMapper followedMapper;

    @Override
    public JSONObject followSomeOne(Integer user1Id, Integer user2Id) {
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        String datetmp = formatter.format(date);

        Followed followed = new Followed(user1Id, user2Id, datetmp);
        followedMapper.insert(followed);

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", user2Id);
        User user = userMapper.selectOne(queryWrapper);
        user.setFollowCount(user.getFollowCount() + 1);
        userMapper.update(user, queryWrapper);

        JSONObject resp = new JSONObject();
        resp.put("error_message", "success");

        return resp;
    }
}
