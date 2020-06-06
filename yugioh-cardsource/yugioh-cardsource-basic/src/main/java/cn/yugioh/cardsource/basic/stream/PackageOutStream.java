package cn.yugioh.cardsource.basic.stream;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface PackageOutStream {
	
	String PACKAGE_OUTPUT = "package";
	
	@Output(PACKAGE_OUTPUT)
	MessageChannel packageDataOutput();
}