package com.space.backend.controller.chat;

import com.alibaba.fastjson2.JSONObject;
import com.space.backend.service.chat.UserPresenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class UserPresenceController {
    @Autowired
    private UserPresenceService userPresenceService;


    @PostMapping("/api/chat/adduser/")
    public JSONObject addUser(@RequestParam Map<String, String> data){
        String username = data.get("username");
        return userPresenceService.addUser(username);
    }

    @PostMapping("/api/chat/removeuser/")
    public JSONObject removeUser(@RequestParam Map<String, String> data){
        String username = data.get("username");
        return userPresenceService.removeUser(username);
    }

    @PostMapping("/api/chat/getonlineusers/")
    public JSONObject getOnlineUsers(@RequestParam Map<String, String> data){
        return userPresenceService.getOnlineUsers();
    }

}
