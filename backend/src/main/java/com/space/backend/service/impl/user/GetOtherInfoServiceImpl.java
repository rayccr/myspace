package com.space.backend.service.impl.user;

import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.space.backend.mapper.UserMapper;
import com.space.backend.pojo.User;
import com.space.backend.service.user.GetOtherInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GetOtherInfoServiceImpl implements GetOtherInfoService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public JSONObject getOtherInfo(Integer userId) {
        QueryWrapper<User> queryWrapper = new QueryWrapper();
        queryWrapper.eq("id", userId);

        User user = userMapper.selectOne(queryWrapper);

        JSONObject resp = new JSONObject();
        resp.put("userInfo", user);
        return resp;
    }
}
