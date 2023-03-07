package com.space.backend.service.impl.chat;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.space.backend.mapper.ChatMapper;
import com.space.backend.mapper.UserMapper;
import com.space.backend.pojo.Chat;
import com.space.backend.pojo.User;
import com.space.backend.service.chat.AddMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class AddMessageServiceImpl implements AddMessageService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ChatMapper chatMapper;

    @Override
    public Map<String, String> addMessage(String username, String content) {
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        String datetmp = formatter.format(date);

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        User user = userMapper.selectOne(queryWrapper);

        Chat chat = new Chat(
                user.getId(),
                username,
                content,
                datetmp);

        chatMapper.insert(chat);

        Map<String, String> map = new HashMap<>();
        map.put("error_message", "success");
        return map;
    }
}
