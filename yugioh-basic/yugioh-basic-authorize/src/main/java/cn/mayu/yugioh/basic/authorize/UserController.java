package cn.mayu.yugioh.basic.authorize;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

	// 资源API
    @RequestMapping("/api/userinfo")
    public ResponseEntity<String> getUserInfo() {
        try {
        	User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            return ResponseEntity.ok(user.getUsername());
		} catch (Exception e) {
			return ResponseEntity.ok("12312312");
		}
    }
}