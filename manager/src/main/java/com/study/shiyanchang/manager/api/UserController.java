package com.study.shiyanchang.manager.api;

import com.study.shiyanchang.common.base.BaseResponse;
import com.study.shiyanchang.common.entity.dto.UserLoginDTO;
import com.study.shiyanchang.common.entity.dto.UserUpdateDTO;
import com.study.shiyanchang.common.entity.po.TUser;
import com.study.shiyanchang.common.util.MD5;
import com.study.shiyanchang.manager.service.TUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
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

    // todo: 2020-11-10，还没做完，只是校验
    @PostMapping("/state/{loginUserId}/{id}/{state}")
    public ResponseEntity<Object> setUserState(@PathVariable("loginUserId") long loginUserId,
                                              @PathVariable("state") Integer state,
                                              @PathVariable("id") Long id){
        // todo: 需要根据loginUserId获取登录用户信息
        // todo: 需要根据id获取要获取的用户信息
        // todo: 需要根据数据权限决定是否可以操作这个用户

        // 修改用户状态
        userService.setUserState(id, state);
        return BaseResponse.operateSuccessMessage();
    }

    // todo: 2020-11-10，还没做完，只是校验
    @PostMapping("/update/{loginUserId}")
    public ResponseEntity<Object> updateUser(@PathVariable("loginUserId") long loginUserId,
                                             @RequestBody UserUpdateDTO userUpdateDTO){
        // todo: 需要根据loginUserId获取登录用户信息
        // todo: 需要根据id获取要获取的用户信息
        // todo: 需要根据数据权限决定是否可以操作这个用户

        // 校验手机号是否唯一
        TUser user = userService.checkUserPhone(userUpdateDTO.getId(), userUpdateDTO.getPhone());
        if(user != null){
            return BaseResponse.sendExistsMessage("手机号");
        }
        // 修改用户状态
        userService.updateUserInfo(userUpdateDTO);
        return BaseResponse.saveSuccessMessage();
    }
}
