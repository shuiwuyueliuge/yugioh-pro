package cn.mayu.org.yugioh.security.core.base.validatecode;

import java.io.IOException;
import java.util.Set;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.filter.OncePerRequestFilter;
import cn.mayu.org.yugioh.security.core.base.exception.ValidateCodeException;
import cn.mayu.org.yugioh.security.core.base.property.ValidateCodeLoginProperty;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ValidateFilter extends OncePerRequestFilter {

	private ValidateCodeManager codeManager;

	private Set<RequestMatcher> requestMatcher;

	private VaildateCodeFailureHandler failureHandler;

	private ValidateCodeLoginProperty loginProperty;

	public ValidateFilter(ValidateCodeLoginProperty loginProperty, ValidateCodeManager codeManager,
			VaildateCodeFailureHandler failureHandler, Set<RequestMatcher> requestMatcher) {
		this.codeManager = codeManager;
		this.failureHandler = failureHandler;
		this.loginProperty = loginProperty;
		this.requestMatcher = requestMatcher;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		if (!verify(request, getRequestURI(request))) {
			failureHandler.onCheckCodeFailure(request, response, new ValidateCodeException("code error"));
			return;
		}

		filterChain.doFilter(request, response);
	}

	private boolean verify(HttpServletRequest request, String uri) {
		if (matches(request)) {
			String cached = codeManager.getAndRemove(getKeyParameter(request));
			String code = getValueParameter(request);
			if (log.isDebugEnabled()) {
				log.debug("validate code checking cached: [{}], code: [{}]", cached, code);
			}
			
			if (code == null || cached == null || !cached.equals(code)) {
				if (log.isDebugEnabled()) {
					log.debug("validate code check code error uri: [{}]", uri);
				}

				return false;
			}
		}

		return true;
	}

	private String getRequestURI(HttpServletRequest request) {
		return request.getRequestURI();
	}

	private String getValueParameter(HttpServletRequest request) {
		return request.getParameter(loginProperty.getCodeValueParam());
	}

	private String getKeyParameter(HttpServletRequest request) {
		return request.getParameter(loginProperty.getKeyParam());
	}

	private boolean matches(HttpServletRequest request) {
		for (RequestMatcher antPathRequestMatcher : requestMatcher) {
			if (antPathRequestMatcher.matches(request)) {
				return true;
			}
		}

		return false;
	}
}
