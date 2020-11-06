package com.study.shiyanchang.common.base;

/**
 * 常用返回码
 * 
 * @author mengpp
 * @date 2019年9月16日21:06:59
 */
public class ResultCode {

	/**
	 * 成功，在请求不是被成功执行的情况下，严禁返回此状态
	 */
	public static final int SUCCESS = 200;

	/**
	 * 针对这个资源所请求的方法被禁止
	 */
	public static final int ERROR_METHODS = 405;

	/**
	 * 针对这个方法传递的参数已存在于DB,请刷新页面后尝试
	 */
	public static final int ERROR_DATA_ALERADY = 408;

	/**
	 * 服务端错误，错误原因未知（可在返回此状态同时附加message说明具体错误）
	 */
	public static final int ERROR_SYSTEM = 500;

}
