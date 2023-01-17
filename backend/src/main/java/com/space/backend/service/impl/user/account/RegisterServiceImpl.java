package com.space.backend.service.impl.user.account;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.space.backend.mapper.UserMapper;
import com.space.backend.pojo.User;
import com.space.backend.service.user.account.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

@Service
public class RegisterServiceImpl implements RegisterService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private final String[] photos = {
            "https://img-static.mihoyo.com/communityweb/upload/ee221417d8f9a4e6e03b707db09b4df2.png",
            "https://upload-bbs.mihoyo.com/upload/2022/09/09/39ed096de3b1c59e09a9bfffaefcf946_6861543172227748935.png",
            "https://img-static.mihoyo.com/communityweb/upload/76497420de6af78a2bc0a8ac9f65807d.png",
            "https://img-static.mihoyo.com/communityweb/upload/fa9678e6d88d9f7e526c26d418cd9400.png",
            "https://img-static.mihoyo.com/communityweb/upload/2ed79379e9e4f4f5e7cf59c583bcd911.png",
            "https://img-static.mihoyo.com/communityweb/upload/8fd7aedf7756ed17c9625d5c27cde48d.png",
            "https://upload-bbs.mihoyo.com/upload/2022/11/24/78c0c40f7cc89facd10ff38b3385d918_3614720584318019134.png",
            "https://upload-bbs.mihoyo.com/upload/2022/10/19/01ce40f4f3897f287b4c862ccb7653d8_8254683619642583206.png",
    };

    @Override
    public Map<String, String> register(String username, String password, String confirmedPassword) {
        Map<String, String> map = new HashMap<>();
        if(username == null){
            map.put("error_message", "用户名不能为空");
            return map;
        }

        if(password == null || confirmedPassword == null){
            map.put("error_message", "密码不能为空");
            return map;
        }

        username = username.trim();
        if(username.length() == 0){
            map.put("error_message", "用户名不能为空");
            return map;
        }

        if(password.length() == 0 || confirmedPassword.length() == 0){
            map.put("error_message", "密码不能为空");
            return map;
        }

        if(username.length() > 100){
            map.put("error_message", "用户名长度不能大于100");
            return map;
        }

        if(password.length() > 100 || confirmedPassword.length() > 100){
            map.put("error_message", "密码长度不能大于100");
            return map;
        }

        if(!password.equals(confirmedPassword)){
            map.put("error_message", "两次输入的密码不一致");
            return map;
        }

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        List<User> users = userMapper.selectList(queryWrapper);
        if(!users.isEmpty()){
            map.put("error_message", "用户名已存在");
            return map;
        }

        String encodedPassword = passwordEncoder.encode(password);

        int idx = (int)(1 + Math.random() * (photos.length-1-1));
        String photo = photos[idx];
        User user = new User(null, username, password, encodedPassword, "null", 0, "null", photo);
        userMapper.insert(user);

        map.put("error_message", "success");
        return map;
    }
}
