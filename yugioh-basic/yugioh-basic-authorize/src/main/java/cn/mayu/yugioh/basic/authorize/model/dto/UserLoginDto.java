package cn.mayu.yugioh.basic.authorize.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginDto {

	private String username;

	private String clientId;
	
	private String password;
}
