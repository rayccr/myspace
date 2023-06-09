package com.space.backend.service.user;

import com.alibaba.fastjson2.JSONObject;

public interface HadFollowService {
    JSONObject hadFollow(Integer user1Id, Integer user2Id);
}
