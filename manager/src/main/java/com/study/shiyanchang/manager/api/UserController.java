package com.study.shiyanchang.manager.api;

import com.study.shiyanchang.common.entity.dto.UserLoginDTO;
import com.study.shiyanchang.common.entity.po.TUser;
import com.study.shiyanchang.common.util.MD5;
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

    @GetMapping("/login")
    public TUser login(UserLoginDTO userLoginDTO){
        // 将密码进行md5加盐加密
        userLoginDTO.setPassword(MD5.encodeByMd5AndSalt(userLoginDTO.getPassword()));
        return userService.getByNameAndPwd(userLoginDTO);
    }

    // todo: 2020-11-09，还没做完，只是校验
    @GetMapping("/info/{loginUserId}/{id}")
    public TUser getUserById(@PathVariable("loginUserId") long loginUserId,
                             @PathVariable("id") Long id){
        // todo: 需要根据loginUserId获取登录用户信息
        // todo: 需要根据id获取要获取的用户信息
        // todo: 需要根据数据权限决定是否可以看到这个用户

        return userService.getById(id);
    }
}
