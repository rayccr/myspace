package com.space.backend.controller.user;

import com.alibaba.fastjson2.JSONObject;
import com.space.backend.service.user.FollowSomeOneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

import static java.lang.Integer.parseInt;

@RestController
public class FollowSomeOneServiceController {
    @Autowired
    private FollowSomeOneService followSomeOneService;

    @PostMapping("/api/user/followsomeone/")
    public JSONObject followSomeOne(@RequestParam Map<String, String> data){
        Integer user1Id = parseInt(data.get("user1Id"));
        Integer user2Id = parseInt(data.get("user2Id"));
        return followSomeOneService.followSomeOne(user1Id, user2Id);
    }
}
