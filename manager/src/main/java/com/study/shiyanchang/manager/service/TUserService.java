package com.study.shiyanchang.manager.service;

import com.study.shiyanchang.common.entity.dto.UserLoginDTO;
import com.study.shiyanchang.common.entity.dto.UserUpdateDTO;
import com.study.shiyanchang.common.entity.po.TUser;

/**
 * 用户表(TUser)表服务接口
 *
 * @author makejava
 * @since 2020-11-05 23:13:06
 */
public interface TUserService {

    /**
     * 根据ID获取实体
     * @param id
     * @return
     */
    TUser getById(Long id);

    /**
     * 根据token获取用户信息
     * @param token
     * @return
     */
    TUser selectUserByToken(String token);

    /**
     * 根据账号和密码获取用户信息
     * @param userLoginDTO
     * @return
     */
    TUser getByNameAndPwd(UserLoginDTO userLoginDTO);

    /**
     * 根据ID修改用户状态
     * @param id
     * @param state
     */
    void setUserState(Long id, Integer state);

    /**
     * 修改用户信息
     * @param userUpdateDTO
     */
    void updateUserInfo(UserUpdateDTO userUpdateDTO);

    /**
     * 根据用户ID和手机号，校验手机号是否重复
     * @param id
     * @param phone
     * @return
     */
    TUser checkUserPhone(Long id, String phone);
}