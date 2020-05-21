package cn.mayu.yugioh.common.core.api;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDetialsVO {

	private String username;
	
	private String password;
	
	private List<String> authorities;
	
	private boolean status;
}
