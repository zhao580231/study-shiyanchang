package com.study.shiyanchang.api.controller;

import com.study.shiyanchang.api.service.PropertyService;
import com.study.shiyanchang.api.service.UserFeignService;
import com.study.shiyanchang.common.base.BaseResponse;
import com.study.shiyanchang.common.base.CurrentScope;
import com.study.shiyanchang.common.base.ResultCode;
import com.study.shiyanchang.common.entity.contants.UserStateEnum;
import com.study.shiyanchang.common.entity.dto.UserLoginDTO;
import com.study.shiyanchang.common.entity.dto.UserUpdateDTO;
import com.study.shiyanchang.common.entity.po.TUser;
import com.study.shiyanchang.common.entity.vo.UserInfoVO;
import com.study.shiyanchang.common.entity.vo.UserLoginVO;
import com.study.shiyanchang.common.util.DateUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/user")
@Api(tags = "用户接口")
public class UserController {
    @Autowired
    private PropertyService propertyService;
    @Autowired
    private UserFeignService userFeignService;

    @GetMapping("/login")
    @ApiOperation(value = "登录", notes = "如果返回的code不等于200，那么需要弹出message信息，反之可以获取到用户信息")
    public ResponseEntity<Object> login(UserLoginDTO userLoginDTO){
        // 属性检查
        ResponseEntity<Object> errorEntity = propertyService.checkUserLoginProperties(userLoginDTO);
        if(errorEntity != null){
            return errorEntity;
        }
        // 使用manager模块的user接口，最后一个参数是必须要跟着，才能通过底层服务的验证
        TUser user = userFeignService.login(userLoginDTO, System.currentTimeMillis());
        if(user == null){
            return BaseResponse.sendDataNull("用户");
        }
        if(user.getState() == UserStateEnum.LOCK.getId()){
            return BaseResponse.sendMessage(ResultCode.ERROR_METHODS,"用户已锁定");
        }
        UserLoginVO userLoginVO =  new UserLoginVO();
        BeanUtils.copyProperties(user, userLoginVO);
        return BaseResponse.sendData(userLoginVO);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据ID获取用户信息", notes = "需要根据当前登录用户ID判断是否可以获取到用户信息")
    public ResponseEntity<Object> getUserById(@PathVariable Long id){
        // 过滤器验证成功后，会将登录用户的ID和token存储到CurrentScope对象中
        long loginUserId = CurrentScope.getLoginUserId();
        // 每个feign请求都需要跟一个当前时间戳，System.currentTimeMillis()，要不底层服务模块验证不通过
        TUser user = userFeignService.getUserById(loginUserId, id, System.currentTimeMillis());
        if(user == null){
            return BaseResponse.sendDataNull("用户");
        }
        // 没问题了，那么就需要copy属性到出参对象里
        UserInfoVO userInfoVO = new UserInfoVO();
        BeanUtils.copyProperties(user, userInfoVO);
        // 设置注册时间和最后登录时间的格式
        Long gmtCreate = user.getGmtCreate();
        if(gmtCreate != null) {
            userInfoVO.setGmtCreate(DateUtil.long2DateTime(gmtCreate));
        }
        Long lastTime = user.getLastTime();
        if(lastTime != null) {
            userInfoVO.setLastTime(DateUtil.long2DateTime(lastTime));
        }
        return BaseResponse.sendData(userInfoVO);
    }

    @PostMapping("/exchange/{id}/{state}")
    @ApiOperation(value = "根据ID获取用户信息", notes = "需要根据当前登录用户ID判断是否可以获取到用户信息")
    public ResponseEntity<Object> setUserState(@PathVariable Long id, @PathVariable Integer state){
        // 验证用户要修改的状态是否为允许的数据，0或1，不能传其他
        boolean isHave = false;
        for(UserStateEnum userStateEnum: UserStateEnum.values()){
            if(userStateEnum.getId() == state){
                isHave = true;
                break;
            }
        }
        if(!isHave){
            return BaseResponse.sendDataNull("状态值");
        }
        // 验证通过，修改用户状态，参数说明：1=登陆人ID，2=修改的用户ID，3=要修改的状态，4=验证使用的时间戳
        return userFeignService.setUserState(CurrentScope.getLoginUserId(), id, state, System.currentTimeMillis());
    }

    @PostMapping("/update")
    @ApiOperation(value = "修改用户信息", notes = "仅能修改部分字段，并且必须要传ID")
    public ResponseEntity<Object> update(@RequestBody UserUpdateDTO userUpdateDTO){
        ResponseEntity<Object> errorEntity = propertyService.checkUserUpdateProperties(userUpdateDTO);
        if(errorEntity != null){
            return errorEntity;
        }
        return userFeignService.updateUser(userUpdateDTO, CurrentScope.getLoginUserId(), System.currentTimeMillis());
    }
}
