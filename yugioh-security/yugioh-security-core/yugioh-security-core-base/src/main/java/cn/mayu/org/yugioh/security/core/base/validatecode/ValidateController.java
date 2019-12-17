package cn.mayu.org.yugioh.security.core.base.validatecode;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class ValidateController {
	
	private static final String RESULT_VIEW = "/code/result";

	@Autowired
	private ValidateCodeProcessorHolder holder;
	
	@RequestMapping("/code/{type}")
	public String validateCode(Model model, HttpServletRequest request, HttpServletResponse response, @PathVariable("type") String type) {
		ValidateCodeProcessor processer = holder.getProcessor(type);
		log.debug("validate code type: [{}]", type);
		if (processer != null) {
			model.addAttribute("sendResult", processer.sendCode(request.getParameter("key")));
		} else {
			model.addAttribute("sendResult", Boolean.FALSE);
		}
		
		return createView();
	}
	
	private String createView() {
		return RESULT_VIEW;
	}
}