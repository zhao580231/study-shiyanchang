package com.study.shiyanchang.api.service;

import com.study.shiyanchang.common.base.BaseResponse;
import com.study.shiyanchang.common.entity.dto.UserLoginDTO;
import org.apache.commons.lang.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class PropertyService {

    /**
     * 检查用户登录时传的属性
     * @param userLoginDTO
     * @return
     */
    public ResponseEntity<Object> checkUserLoginProperties(UserLoginDTO userLoginDTO) {
        String userName = userLoginDTO.getUserName();
        String password = userLoginDTO.getPassword();
        if(userName == null || StringUtils.isEmpty(userName.trim())){
            return BaseResponse.sendPropertyNull("用户名");
        }
        if(password == null || StringUtils.isEmpty(password.trim())){
            return BaseResponse.sendPropertyNull("密码");
        }
        return null;
    }
}
