package cn.mayu.yugioh.basic.authorize.config;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import com.fasterxml.jackson.databind.ObjectMapper;
import cn.mayu.yugioh.security.application.token.OAuth2AccessTokenHelper;

@Configuration
public class LoginSuccessHandler implements AuthenticationSuccessHandler {
	
	@Autowired
	private OAuth2AccessTokenHelper tokenHelper;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		String clientId = request.getParameter("client_id");
		if (clientId == null ) throw new RuntimeException();
		OAuth2AccessToken accessToken = tokenHelper.createToken(clientId, "test1", authentication);
		response.setHeader("Content-Type", "application/json");
		response.getWriter().write(new ObjectMapper().writeValueAsString(accessToken));
	}
}
