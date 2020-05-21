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

	@Autowired
	private ValidateCodeProcessorHolder holder;
	
	@RequestMapping("/validate_code/{type}")
	public String validateCode(Model model, HttpServletRequest request, HttpServletResponse response, @PathVariable("type") String type) {
		ValidateCodeProcessor processer = holder.getProcessor(type);
		if (log.isDebugEnabled()) {
			log.debug("validate code type: [{}]", type);
		}

		ValidateCodeContext context = ValidateCodeContext.builder().request(request).build();
		if (processer != null) {
			processer.sendCode(context);
			model.addAttribute(ValidateCodeConstants.CODE_SEND_RESULT, context.getSendRes());
		}
		
		return createView();
	}
	
	private String createView() {
		return ValidateCodeConstants.RESULT_VIEW;
	}
}