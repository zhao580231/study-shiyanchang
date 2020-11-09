package com.study.shiyanchang.api.service;

import com.study.shiyanchang.common.entity.dto.UserLoginDTO;
import com.study.shiyanchang.common.entity.po.TUser;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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
}
