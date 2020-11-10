package com.study.shiyanchang.manager.dao;

import com.study.shiyanchang.common.entity.dto.UserLoginDTO;
import com.study.shiyanchang.common.entity.dto.UserUpdateDTO;
import com.study.shiyanchang.common.entity.po.TUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 用户表(TUser)表数据库访问层
 *
 * @author makejava
 * @since 2020-11-05 23:13:06
 */
@Mapper
@Repository
public interface TUserMapper {

    /**
     * 根据ID从t_user表中获取数据
     * @param id
     * @return
     */
    TUser getById(@Param("id") Long id);

    /**
     * 根据token从t_user表中获取数据
     * @param token
     * @return
     */
    TUser selectUserByToken(@Param("token") String token);

    /**
     * 根据账号和密码获取用户信息
     * @param userName
     * @param password
     * @return
     */
    TUser getByNameAndPwd(@Param("userName") String userName, @Param("password") String password);

    /**
     * 根据ID修改用户最后登录时间
     * @param id
     */
    void setUserLastDateTime(@Param("id") Long id);

    /**
     * 根据ID修改用户状态
     * @param id
     * @param state
     */
    void setUserState(@Param("id") Long id, @Param("state") Integer state);

    /**
     * 修改用户信息
     * @param userUpdateDTO
     */
    void updateUserInfo(UserUpdateDTO userUpdateDTO);

    /**
     * 根据<>ID和 ==手机号，获取用户
     * @param id
     * @param phone
     * @return
     */
    TUser checkUserPhone(@Param("id")Long id, @Param("phone")String phone);
}