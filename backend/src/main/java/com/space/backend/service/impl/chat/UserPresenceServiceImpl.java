package com.space.backend.service.impl.chat;

import com.alibaba.fastjson2.JSONObject;
import com.space.backend.service.chat.UserPresenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserPresenceServiceImpl implements UserPresenceService {
    private final RedisTemplate<String, String> redisTemplate;
    private static final String ONLINE_USERS_KEY = "onlineUsers";

    @Autowired
    public UserPresenceServiceImpl(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public JSONObject addUser(String username) {
        this.redisTemplate.opsForSet().add(ONLINE_USERS_KEY, username);
        JSONObject resp = new JSONObject();
        resp.put("error_message", "success");
        return resp;
    }

    @Override
    public JSONObject removeUser(String username) {
        this.redisTemplate.opsForSet().remove(ONLINE_USERS_KEY, username);
        JSONObject resp = new JSONObject();
        resp.put("error_message", "success");
        return resp;
    }

    @Override
    public JSONObject getOnlineUsers() {
        Set<String> onlineUsers = this.redisTemplate.opsForSet().members(ONLINE_USERS_KEY);
        JSONObject resp = new JSONObject();
        resp.put("onlineUsers", onlineUsers);
        return resp;
    }
}
