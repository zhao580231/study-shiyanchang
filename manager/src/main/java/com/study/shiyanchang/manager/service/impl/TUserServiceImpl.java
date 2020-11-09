package com.study.shiyanchang.manager.service.impl;

import com.study.shiyanchang.common.entity.dto.UserLoginDTO;
import com.study.shiyanchang.common.entity.po.TUser;
import com.study.shiyanchang.manager.dao.TUserMapper;
import com.study.shiyanchang.manager.service.TUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 用户表(TUser)表服务实现类
 *
 * @author makejava
 * @since 2020-11-05 23:13:06
 */
@Service
public class TUserServiceImpl implements TUserService {
    @Resource
    private TUserMapper userMapper;

    @Override
    public TUser getById(Long i) {
        return userMapper.getById(i);
    }

    @Override
    public TUser selectUserByToken(String token) {
        return userMapper.selectUserByToken(token);
    }

    @Override
    public TUser getByNameAndPwd(UserLoginDTO userLoginDTO) {
        TUser user = userMapper.getByNameAndPwd(userLoginDTO.getUserName(), userLoginDTO.getPassword());
        if(user != null){
            userMapper.setUserLastDateTime(user.getId());
        }
        return user;
    }
}