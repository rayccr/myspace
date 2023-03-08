package com.space.backend.service.user;

import com.alibaba.fastjson2.JSONObject;

import java.util.Map;

public interface UpdateSignService {
    JSONObject updateSign(Integer userId, String content);
}
