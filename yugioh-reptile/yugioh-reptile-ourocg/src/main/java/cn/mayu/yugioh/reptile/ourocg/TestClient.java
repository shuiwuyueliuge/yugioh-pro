package cn.mayu.yugioh.reptile.ourocg;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("test-server")
public interface TestClient {

	@RequestMapping("/test")
	String test();
}
