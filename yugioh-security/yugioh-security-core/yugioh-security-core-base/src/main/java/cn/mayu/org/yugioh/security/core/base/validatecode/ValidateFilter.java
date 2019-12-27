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
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ValidateFilter extends OncePerRequestFilter {

	private ValidateCodeManager codeManager;

	private Set<RequestMatcher> requestMatcher;

	private VaildateCodeFailureHandler failureHandler;
	
	private ValidateCodeProcessorHolder processorHolder;

	public ValidateFilter(ValidateCodeProcessorHolder processorHolder, ValidateCodeManager codeManager,
			VaildateCodeFailureHandler failureHandler, Set<RequestMatcher> requestMatcher) {
		this.codeManager = codeManager;
		this.failureHandler = failureHandler;
		this.requestMatcher = requestMatcher;
		this.processorHolder = processorHolder;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		if (!verify(request, getRequestURI(request))) {
			failureHandler.onCheckCodeFailure(request, response, new ValidateCodeException("validate code error"));
			return;
		}

		filterChain.doFilter(request, response);
	}

	private boolean verify(HttpServletRequest request, String uri) {
		if (!matches(request)) return true;
		String key = getKeyParameter(request);
		if (key == null) return false;
		String cached = codeManager.getAndRemove(key);
		String code = getValueParameter(request);
		if (code == null) return false;
		if (log.isDebugEnabled()) {
			log.debug("validate code checking cached: [{}], code: [{}]", cached, code);
		}
		
		return (code == null || cached == null || !cached.equals(code)) ? false : true;
	}

	private String getValueParameter(HttpServletRequest request) {
		Set<String> keys = processorHolder.getValidateCode();
		for (String string : keys) {
			String key = request.getParameter(string);
			if (key == null) continue;
			return key;
		}
		
		return null;
	}

	private String getKeyParameter(HttpServletRequest request) {
		Set<String> keys = processorHolder.getValidateCodeKey();
		for (String string : keys) {
			String key = request.getParameter(string);
			if (key == null) continue;
			return key;
		}
		
		return null; 
	}

	private String getRequestURI(HttpServletRequest request) {
		return request.getRequestURI();
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
