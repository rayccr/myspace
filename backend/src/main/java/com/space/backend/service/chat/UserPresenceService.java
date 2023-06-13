package com.space.backend.service.chat;

import com.alibaba.fastjson2.JSONObject;

public interface UserPresenceService {
    public JSONObject addUser(String username);

    public JSONObject removeUser(String username);

    public JSONObject getOnlineUsers();
}
