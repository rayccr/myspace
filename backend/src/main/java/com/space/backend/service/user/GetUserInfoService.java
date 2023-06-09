package com.space.backend.service.user;

import com.alibaba.fastjson2.JSONObject;

public interface GetUserInfoService {
    JSONObject getUserInfo(Integer userId);
    JSONObject getUserInfoByName(String username);
}
