package com.study.shiyanchang.manager.api;

import com.study.shiyanchang.common.entity.po.TUser;
import com.study.shiyanchang.manager.service.TUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private TUserService userService;

    @GetMapping("/{token}")
    public TUser getUser(@PathVariable String token){
        return userService.selectUserByToken(token);
    }
}
