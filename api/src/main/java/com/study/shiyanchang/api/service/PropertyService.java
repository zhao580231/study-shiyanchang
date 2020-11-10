package com.study.shiyanchang.api.service;

import com.study.shiyanchang.common.base.BaseResponse;
import com.study.shiyanchang.common.entity.dto.UserLoginDTO;
import com.study.shiyanchang.common.entity.dto.UserUpdateDTO;
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
        // 先判断为null，是怕对象为null了trim方法报空指针
        if(userName == null || StringUtils.isEmpty(userName.trim())){
            return BaseResponse.sendPropertyNull("用户名");
        }
        if(password == null || StringUtils.isEmpty(password.trim())){
            return BaseResponse.sendPropertyNull("密码");
        }
        return null;
    }

    /**
     * 检查修改用户的属性
     * @param userUpdateDTO
     * @return
     */
    public ResponseEntity<Object> checkUserUpdateProperties(UserUpdateDTO userUpdateDTO) {
        String name = userUpdateDTO.getName();
        String phone = userUpdateDTO.getPhone();
        Long id = userUpdateDTO.getId();
        if(id == null || id == 0){
            return BaseResponse.sendPropertyNull("用户唯一标识");
        }
        // 先判断为null，是怕对象为null了trim方法报空指针
        if(name == null || StringUtils.isEmpty(name.trim())){
            return BaseResponse.sendPropertyNull("用户姓名");
        }
        if(phone == null || StringUtils.isEmpty(phone.trim())){
            return BaseResponse.sendPropertyNull("手机号");
        }
        return null;
    }
}
