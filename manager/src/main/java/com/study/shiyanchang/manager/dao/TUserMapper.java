package com.study.shiyanchang.manager.dao;

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
    TUser getById(@Param("id") int id);
}