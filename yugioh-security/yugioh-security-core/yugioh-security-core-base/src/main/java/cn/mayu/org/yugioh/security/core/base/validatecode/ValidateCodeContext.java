package cn.mayu.org.yugioh.security.core.base.validatecode;

import javax.servlet.http.HttpServletRequest;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ValidateCodeContext {
	
	private String code;
	
	private String sendRes;
	
	private HttpServletRequest request;
	
	private String key;
	
	public String getKeyByParameter(String paramName) {
		String key = request.getParameter(paramName);
		this.key = key;
		return key;
	}
	
	public String getKeyByHeader(String headerName) {
		String key =  request.getHeader(headerName);
		this.key = key;
		return key;
	}
}
