package com.web.test;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.AbstractView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Component("/validate_code/result")
public class Test2 extends AbstractView {

    @Override
    protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
                                           HttpServletResponse response) throws Exception {
        if (!(boolean)model.get("success")) {
            response.getOutputStream().write("error".getBytes());
            return;
        }

        response.getOutputStream().write("send success".getBytes());
    }
}