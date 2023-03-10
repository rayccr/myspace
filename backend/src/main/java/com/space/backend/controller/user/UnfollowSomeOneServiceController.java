package com.space.backend.controller.user;


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
    public Map<String, String> unfollowSomeOne(@RequestParam Map<String, String> data){
        Integer userId = parseInt(data.get("userId"));
        return unfollowSomeOneService.unfollowSomeOne(userId);
    }
}
