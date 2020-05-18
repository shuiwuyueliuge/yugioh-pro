package cn.mayu.yugioh.transform.stream;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface PackageInputStream {
	
	String PACKAGE_SAVE_INPUT = "package";
	
	@Input(PACKAGE_SAVE_INPUT)
	SubscribableChannel packageSaveInput();
}