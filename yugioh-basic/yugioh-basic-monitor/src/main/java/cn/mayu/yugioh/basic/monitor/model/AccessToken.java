package cn.mayu.yugioh.basic.monitor.model;

import lombok.Data;

@Data
public class AccessToken {
	
	private String access_token;
	
	private String token_type;
	
	private String expires_in;
}
