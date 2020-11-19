package cn.mayu.yugioh.security.core.sms;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "yugioh.security.sms")
public class SmsCodeProperty {

    private String smsCodeUri = "/code/sms";

    private String smsCodeKeyParam = "mobile";

    private String smsCodeValueParam = "smsCode";
}
