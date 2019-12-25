package cn.mayu.org.yugioh.security.core.base.validatecode;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.filter.OncePerRequestFilter;
import cn.mayu.org.yugioh.security.core.base.exception.ValidateCodeException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ValidateFilter extends OncePerRequestFilter {
	
	private ValidateCodeProcessorHolder holder;
	
	private VaildateCodeFailureHandler failureHandler;
	
	public ValidateFilter(ValidateCodeProcessorHolder holder, VaildateCodeFailureHandler failureHandler) {
		this.holder = holder;
		this.failureHandler = failureHandler;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		if (!verify(request, getRequestURI(request))) {
			failureHandler.onCheckCodeFailure(request, response, new ValidateCodeException("code error"));
			return;
		}
		
		filterChain.doFilter(request, response);
	}
	
	private boolean verify(HttpServletRequest request, String uri) {
		if (uri.indexOf("/login/") != -1) {
			if (log.isDebugEnabled()) {
				log.debug("验证码登陆url[" + uri + "]");
			}
			
			ValidateCodeProcessor processor = holder.getProcessor(uri.replace("/login/", ""));
		    if (processor == null) {
		    	if (log.isDebugEnabled()) {
		    		log.debug("验证码登陆url[" + uri + "]错误");
				}
		    	
		    	return false;
		    }
		    
		    if (!processor.check(getParameter(request, "key"), getParameter(request, "code"))) {
		    	if (log.isDebugEnabled()) {
		    		log.debug("验证码登陆url[" + uri + "]验证码验证失败");
				}
		    	
		    	return false;
		    }
		}
		
		return true;
	}
	
	private String getRequestURI(HttpServletRequest request) {
		return request.getRequestURI();
	}
	
	private String getParameter(HttpServletRequest request, String key) {
		return request.getParameter(key);
	}
}
