package com.space.backend.service.chat;

import com.alibaba.fastjson2.JSONObject;

import java.util.Map;

public interface AddMessageService {
    Map<String, String> addMessage(String username, String content);
}
