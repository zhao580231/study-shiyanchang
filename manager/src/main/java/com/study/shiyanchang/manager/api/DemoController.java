package com.study.shiyanchang.manager.api;

import com.study.shiyanchang.common.entity.po.TUser;
import com.study.shiyanchang.manager.service.TUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class DemoController {
    @Autowired
    private TUserService userService;

    @GetMapping("/demo")
    public TUser demo(@RequestParam("str") String str){
        System.out.println("前端传参："+str);
        return userService.getById(1);
    }
}
