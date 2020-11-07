package com.study.shiyanchang.api.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.study.shiyanchang.common.entity.po.TUser;
import com.study.shiyanchang.common.util.RedisUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.concurrent.locks.ReentrantLock;

@Service
public class UserTokenService {
    @Autowired
    private UserFeignService userFeignService;
    @Autowired
    RedisUtil redisUtil;
    @Value("${redis.database:0}")
    private int redisDatabase;

    private ReentrantLock reentrantLock=new ReentrantLock();

    public TUser getUserByToken(String token) {
        String tokenKey = "token:"+token;
        String value = redisUtil.get(tokenKey, redisDatabase);
        TUser user = null;
        if(StringUtils.isEmpty(value)){
            user = getUserLockByFeign(token,tokenKey);
        }else{
            user = JSONObject.parseObject(value, TUser.class);
        }
        return user;
    }

    /**
     * 附带redis锁机制，重新从redis中获取，如有则转换对象，若没有，走feign
     * @param token
     * @param tokenKey
     * @return
     */
    private TUser getUserLockByFeign(String token, String tokenKey) {
        TUser user = null;
        //只加了try
        try {
            // 先加了锁
            reentrantLock.lock();
            // 在获取一次。
            String value = redisUtil.get(tokenKey, redisDatabase);
            if(StringUtils.isEmpty(value)){
                // 还是没有，落db
                user = userFeignService.getUser(token, System.currentTimeMillis());
                if(user != null) {
                    redisUtil.set(tokenKey, JSONObject.toJSONString(user), redisDatabase);
                }
            }else {
                // 有数据，转换
                user = JSONObject.parseObject(value, TUser.class);
            }
        }finally {
            // 解锁
            reentrantLock.unlock();
        }
        return user;
    }
}
