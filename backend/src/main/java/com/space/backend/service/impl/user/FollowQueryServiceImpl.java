package com.space.backend.service.impl.user;

import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.space.backend.mapper.FollowedMapper;
import com.space.backend.mapper.UserMapper;
import com.space.backend.pojo.Followed;
import com.space.backend.pojo.User;
import com.space.backend.service.user.FollowQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FollowQueryServiceImpl implements FollowQueryService {

    @Autowired
    private FollowedMapper followedMapper;

    @Autowired
    private UserMapper userMapper;

    @Override
    public JSONObject whoIFollowed(Integer userId) {
        QueryWrapper<Followed> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);

        List<Followed> iFollows = followedMapper.selectList(queryWrapper);
        List<String> usernames = new ArrayList<>();
        List<String> photos = new ArrayList<>();

        JSONObject resp = new JSONObject();

        for (Followed iFollow : iFollows) {
            QueryWrapper<User> queryWrapper1 = new QueryWrapper<>();
            queryWrapper1.eq("id", iFollow.getFocusUserId());
            User user = userMapper.selectOne(queryWrapper1);
            photos.add(user.getPhoto());
            usernames.add(user.getUsername());
        }

        resp.put("followsUsername", usernames);
        resp.put("followsPhoto", photos);
        return resp;
    }

    @Override
    public JSONObject whoFollowedMe(Integer userId) {
        QueryWrapper<Followed> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("focus_user_id", userId);
        List<Followed> followsMe = followedMapper.selectList(queryWrapper);

        JSONObject resp = new JSONObject();
        List<String> usernames = new ArrayList<>();
        List<String> photos = new ArrayList<>();

        for (Followed followMe : followsMe) {
            QueryWrapper<User> queryWrapper1 = new QueryWrapper<>();
            queryWrapper1.eq("id", followMe.getUserId());
            User user = userMapper.selectOne(queryWrapper1);
            photos.add(user.getPhoto());
            usernames.add(user.getUsername());
        }

        resp.put("followedUsername", usernames);
        resp.put("followedPhoto", photos);
        return resp;
    }
}
