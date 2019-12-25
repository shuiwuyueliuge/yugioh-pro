package com.yugioh.start;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.AbstractView;

@Component("/code/result")
public class DefaultValidateController extends AbstractView {

	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (model.get("sendResult") == null) {
			response.getOutputStream().write("send fail".getBytes());
			return;
		}
		
		response.getOutputStream().write(model.get("sendResult").toString().getBytes());
	}
}