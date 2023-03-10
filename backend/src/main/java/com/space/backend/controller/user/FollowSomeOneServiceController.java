package com.space.backend.controller.user;

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
    public Map<String, String> followSomeOne(@RequestParam Map<String, String> data){
        Integer userId = parseInt(data.get("userId"));
        return followSomeOneService.followSomeOne(userId);
    }
}
