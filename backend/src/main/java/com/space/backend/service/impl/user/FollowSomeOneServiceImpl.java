package com.space.backend.service.impl.user;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.space.backend.mapper.FollowedMapper;
import com.space.backend.mapper.UserMapper;
import com.space.backend.pojo.Followed;
import com.space.backend.pojo.User;
import com.space.backend.service.impl.utils.UserDetailsImpl;
import com.space.backend.service.user.FollowSomeOneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class FollowSomeOneServiceImpl implements FollowSomeOneService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private FollowedMapper followedMapper;

    @Override
    public Map<String, String> followSomeOne(Integer UserId) {

        UsernamePasswordAuthenticationToken authenticationToken =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();

        UserDetailsImpl loginUser = (UserDetailsImpl) authenticationToken.getPrincipal();
        User user1 = loginUser.getUser();

        QueryWrapper<Followed> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.eq("user_id", user1.getId()).eq("focus_user_id", UserId);
        Map<String ,String> map = new HashMap<>();

        List<Followed> followeds = followedMapper.selectList(queryWrapper1);

        if(followeds.isEmpty()) {
            QueryWrapper<User> queryWrapper = new QueryWrapper<>(); // other
            queryWrapper.eq("id", UserId);
            User user = userMapper.selectOne(queryWrapper);
            Integer count = user.getFollowCount();
            user.setFollowCount(count + 1);
            userMapper.update(user, queryWrapper);

            SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = new Date(System.currentTimeMillis());
            String datetmp = formatter.format(date);

            Followed followed = new Followed(user1.getId(), user.getId(), datetmp);
            followedMapper.insert(followed);
            map.put("error_message", "success");
        } else {
            map.put("error_message", "error");
        }

        return map;
    }
}
