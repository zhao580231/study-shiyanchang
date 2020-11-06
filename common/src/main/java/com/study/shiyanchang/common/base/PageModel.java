package com.study.shiyanchang.common.base;

import lombok.Data;

import java.io.Serializable;

@Data
public class PageModel<T> implements Serializable
{
	private static final long serialVersionUID = 1L;

	private Integer pageSize =10;

	private Integer current=1;
}
