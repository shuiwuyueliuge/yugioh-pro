package cn.mayu.yugioh.security.core.base.verificationcode;

import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.Assert;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserNameAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

	private String username;
	private boolean postOnly = true;

	public UserNameAuthenticationFilter(String processingUrl, String username) {
		super(new AntPathRequestMatcher(processingUrl, "POST"));
		this.username = username;
	}

	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		if (postOnly && !request.getMethod().equals("POST")) {
			throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
		}

		String username = obtainUsername(request);
		if (username == null) {
			username = "";
		}

		username = username.trim();
		UserNameAuthenticationToken authRequest = new UserNameAuthenticationToken(username);
		setDetails(request, authRequest);
		return this.getAuthenticationManager().authenticate(authRequest);
	}

	protected String obtainUsername(HttpServletRequest request) {
		return request.getParameter(username);
	}

	protected void setDetails(HttpServletRequest request, UserNameAuthenticationToken authRequest) {
		authRequest.setDetails(authenticationDetailsSource.buildDetails(request));
	}
	
	@Override
	protected boolean requiresAuthentication(HttpServletRequest request, HttpServletResponse response) {
		
		return super.requiresAuthentication(request, response);
	}

	public void setUsername(String username) {
		Assert.hasText(username, username);
		this.username = username;
	}

	public void setPostOnly(boolean postOnly) {
		this.postOnly = postOnly;
	}

	public final String getUsername() {
		return username;
	}
}
