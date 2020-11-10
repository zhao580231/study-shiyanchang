package com.study.shiyanchang.api.service;

import com.study.shiyanchang.common.entity.dto.UserLoginDTO;
import com.study.shiyanchang.common.entity.dto.UserUpdateDTO;
import com.study.shiyanchang.common.entity.po.TUser;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(value = "manager")
public interface UserFeignService {
    @GetMapping(value = "manager/user/{token}/{times}")
    TUser getUser(@PathVariable("token") String token, @PathVariable("times") long times);

    @GetMapping(value = "manager/user/login/{times}")
    TUser login(@SpringQueryMap UserLoginDTO userLoginDTO, @PathVariable("times") long currentTimeMillis);

    @GetMapping(value = "manager/user/info/{loginUserId}/{id}/{times}")
    TUser getUserById(@PathVariable("loginUserId") long loginUserId,
                      @PathVariable("id") Long id,
                      @PathVariable("times") long currentTimeMillis);

    @PostMapping(value = "manager/user/state/{loginUserId}/{id}/{state}/{times}")
    ResponseEntity<Object> setUserState(@PathVariable("loginUserId") long loginUserId,
                                        @PathVariable("id") Long id,
                                        @PathVariable("state") Integer state,
                                        @PathVariable("times") long currentTimeMillis);

    @PostMapping(value = "manager/user/update/{loginUserId}/{times}")
    ResponseEntity<Object> updateUser(@RequestBody UserUpdateDTO userUpdateDTO,
                                      @PathVariable("loginUserId") long loginUserId,
                                      @PathVariable("times") long currentTimeMillis);
}
