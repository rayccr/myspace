package com.space.backend.service.impl.userlist;

import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.space.backend.mapper.UserMapper;
import com.space.backend.pojo.User;
import com.space.backend.service.userlist.UserListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserListServiceImpl implements UserListService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public JSONObject getUserList() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();

        queryWrapper.orderByDesc("id");
        List<User> users = userMapper.selectList(queryWrapper);

        JSONObject resp = new JSONObject();

        resp.put("users", users);
        resp.put("users_count", userMapper.selectCount(null));

        return resp;
    }
}
