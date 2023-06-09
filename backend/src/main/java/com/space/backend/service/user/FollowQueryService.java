package com.space.backend.service.user;

import com.alibaba.fastjson2.JSONObject;

public interface FollowQueryService {
    JSONObject whoIFollowed(Integer userId);
    JSONObject whoFollowedMe(Integer userId);
}
