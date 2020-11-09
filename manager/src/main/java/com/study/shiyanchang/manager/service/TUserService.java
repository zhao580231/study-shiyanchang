package com.study.shiyanchang.manager.service;

import com.study.shiyanchang.common.entity.dto.UserLoginDTO;
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
     * @param i
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
}