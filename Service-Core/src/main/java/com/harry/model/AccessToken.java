package com.harry.model;

import lombok.Data;

/**
 * @author Harry
 * @Description 接口访问凭证
 * @date  2017年7月10日
 */
@Data
public class AccessToken {

	// 获取到的凭证
	private String token;
	// 凭证有效时间，单位：秒
	private int expiresIn;
	
}
