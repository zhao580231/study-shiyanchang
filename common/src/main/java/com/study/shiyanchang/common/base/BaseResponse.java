package com.study.shiyanchang.common.base;

import com.alibaba.fastjson.JSONObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.ResponseEntity;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class BaseResponse implements Serializable {

	private static final long serialVersionUID = -5055168314128607695L;

	/**
	 * 返回json格式的消息{"code": 状态码, "message":"消息内容"}
	 * @param code
	 * @param obj
	 * @return
	 */
	public static ResponseEntity<Object> sendMessage(int code, Object obj) {
		JSONObject json = new JSONObject();
		json.put("code", code);
		json.put("message", obj);
		return ResponseEntity.status(ResultCode.SUCCESS).body(json);
	}

	/**
	 * 返回json格式的数据{"code": 200, "data": (Object)数据内容}
	 * @param obj
	 * @return
	 */
	public static ResponseEntity<Object> sendData(Object obj) {
		JSONObject json = new JSONObject();
		json.put("code", ResultCode.SUCCESS);
		json.put("data", obj);
		return ResponseEntity.status(ResultCode.SUCCESS).body(json);
	}

	/**
	 * 返回json格式的操作成功数据{"code": 200, "data":"操作成功"}
	 * @return
	 */
	public static ResponseEntity<Object> operateSuccessMessage() {
		return sendMessage(ResultCode.SUCCESS,"操作成功");
	}

	/**
	 * 返回json格式的保存成功数据{"code": 200, "data":"保存成功"}
	 * @return
	 */
	public static ResponseEntity<Object> saveSuccessMessage() {
		return sendMessage(ResultCode.SUCCESS,"保存成功");
	}

	/**
	 * 返回json格式的操作失败数据{"code": 500, "data":"操作失败"}
	 * @return
	 */
	public static ResponseEntity<Object> operateErrorMessage() {
		return sendMessage(ResultCode.ERROR_SYSTEM,"操作失败");
	}

	/**
	 * 返回json格式的保存失败数据{"code": 500, "data":"保存失败"}
	 * @return
	 */
	public static ResponseEntity<Object> saveErrorMessage() {
		return sendMessage(ResultCode.ERROR_SYSTEM,"保存失败");
	}

	/**
	 * 返回json格式的保存失败数据{"code": 408, "data": message+"已存在"}
	 * @param message
	 * @return
	 */
	public static ResponseEntity<Object> sendExistsMessage(String message) {
		return sendMessage(ResultCode.ERROR_DATA_ALERADY,message+"已存在");
	}

	/**
	 * 返回json格式的属性检查失败数据{"code": 405, "data": "请输入"+propertyName}
	 * @param propertyName
	 * @return
	 */
	public static ResponseEntity<Object> sendPropertyNull(String propertyName) {
		return sendMessage(ResultCode.ERROR_METHODS,"请输入"+propertyName);
	}
	/**
	 * 返回json格式的属性检查失败数据{"code": 405, "data": dataName+"不存在，请重新输入"}
	 * @param dataName
	 * @return
	 */
	public static ResponseEntity<Object> sendDataNull(String dataName) {
		return sendMessage(ResultCode.ERROR_METHODS,dataName+"不存在");
	}
}