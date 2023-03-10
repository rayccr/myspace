package com.space.backend.controller.userlist;

import com.alibaba.fastjson2.JSONObject;
import com.space.backend.service.userlist.UserListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class UserListController {

    @Autowired
    private UserListService userListService;

    @GetMapping("/api/user/userlist/")
    public JSONObject getList(@RequestParam Map<String, String> data) {
        return userListService.getUserList();
    }
}
