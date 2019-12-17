package cn.mayu.org.yugioh.security.core.base.validatecode;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;

public interface VaildateCodeFailureHandler {

	void onCheckCodeFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException;
}
