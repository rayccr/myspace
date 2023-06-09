package com.space.backend.controller.user;


import com.alibaba.fastjson2.JSONObject;
import com.space.backend.service.user.UnfollowSomeOneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

import static java.lang.Integer.parseInt;

@RestController
public class UnfollowSomeOneServiceController {
    @Autowired
    private UnfollowSomeOneService unfollowSomeOneService;

    @PostMapping("/api/user/unfollowsomeone/")
    public JSONObject unfollowSomeOne(@RequestParam Map<String, String> data){
        Integer user1Id = parseInt(data.get("user1Id"));
        Integer user2Id = parseInt(data.get("user2Id"));
        return unfollowSomeOneService.unfollowSomeOne(user1Id, user2Id);
    }
}
