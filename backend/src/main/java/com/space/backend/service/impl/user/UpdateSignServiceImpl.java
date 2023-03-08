package com.space.backend.service.impl.user;

import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.space.backend.mapper.UserMapper;
import com.space.backend.pojo.User;
import com.space.backend.service.user.UpdateSignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UpdateSignServiceImpl implements UpdateSignService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public JSONObject updateSign(Integer userId, String content) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", userId);

        User user = userMapper.selectOne(queryWrapper);

        user.setSignature(content);
        userMapper.update(user, queryWrapper);

        JSONObject resp = new JSONObject();
        resp.put("user", user);
        return resp;
    }
}
