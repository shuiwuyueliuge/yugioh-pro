//package com.yugioh.start;
//
//import java.util.Map;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.springframework.stereotype.Component;
//import org.springframework.web.servlet.view.AbstractView;
//
//import cn.mayu.org.yugioh.security.core.base.validatecode.ValidateCodeConstants;
//
//@Component(ValidateCodeConstants.RESULT_VIEW)
//public class DefaultValidateController extends AbstractView {
//
//	@Override
//	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
//			HttpServletResponse response) throws Exception {
//		if (model.get(ValidateCodeConstants.CODE_SEND_RESULT) == null) {
//			response.getOutputStream().write("send fail".getBytes());
//			return;
//		}
//		
//		response.getOutputStream().write(model.get(ValidateCodeConstants.CODE_SEND_RESULT).toString().getBytes());
//	}
//}