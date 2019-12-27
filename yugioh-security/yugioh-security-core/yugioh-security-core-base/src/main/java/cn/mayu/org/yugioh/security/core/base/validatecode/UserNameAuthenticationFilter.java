package cn.mayu.org.yugioh.security.core.base.validatecode;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.Assert;

public class UserNameAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

	private String mobile;
	private boolean postOnly = true;

	public UserNameAuthenticationFilter(String processingUrl, String mobile) {
		super(new AntPathRequestMatcher(processingUrl, "POST"));
		this.mobile = mobile;
	}

	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		if (postOnly && !request.getMethod().equals("POST")) {
			throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
		}

		String mobile = obtainMobile(request);
		if (mobile == null) {
			mobile = "";
		}

		mobile = mobile.trim();
		UserNameAuthenticationToken authRequest = new UserNameAuthenticationToken(mobile);
		setDetails(request, authRequest);
		return this.getAuthenticationManager().authenticate(authRequest);
	}

	protected String obtainMobile(HttpServletRequest request) {
		return request.getParameter(mobile);
	}

	protected void setDetails(HttpServletRequest request, UserNameAuthenticationToken authRequest) {
		authRequest.setDetails(authenticationDetailsSource.buildDetails(request));
	}
	
	@Override
	protected boolean requiresAuthentication(HttpServletRequest request, HttpServletResponse response) {
		
		return super.requiresAuthentication(request, response);
	}

	public void setMobile(String mobile) {
		Assert.hasText(mobile, "mobile parameter must not be empty or null");
		this.mobile = mobile;
	}

	public void setPostOnly(boolean postOnly) {
		this.postOnly = postOnly;
	}

	public final String getMobile() {
		return mobile;
	}
}
