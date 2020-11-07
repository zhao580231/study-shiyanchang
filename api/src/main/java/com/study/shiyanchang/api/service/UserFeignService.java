package com.study.shiyanchang.api.service;

import com.study.shiyanchang.common.entity.po.TUser;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "manager")
public interface UserFeignService {
    @GetMapping(value = "manager/user/{token}/{times}")
    TUser getUser(@PathVariable("token") String token, @PathVariable("times") long times);
}
