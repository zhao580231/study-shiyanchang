package com.study.shiyanchang.manager.api;

import com.study.shiyanchang.common.entity.po.TUser;
import com.study.shiyanchang.manager.service.TUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {
    @Autowired
    private TUserService userService;

    @GetMapping("/demo")
    public TUser demo(){
        TUser user = userService.getById(1);
        return user;
    }
}
