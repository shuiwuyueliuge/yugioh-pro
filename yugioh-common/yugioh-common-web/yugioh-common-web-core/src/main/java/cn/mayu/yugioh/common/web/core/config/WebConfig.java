package cn.mayu.yugioh.common.web.core.config;

import cn.mayu.yugioh.common.web.core.intercept.RestInterceptor;
import cn.mayu.yugioh.common.web.core.intercept.UnderlineToCamelArgumentResolver;
import org.hibernate.validator.HibernateValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.core.convert.converter.Converter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class WebConfig implements WebMvcConfigurer {

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new RestInterceptor()).addPathPatterns("/**");
	}

	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
		argumentResolvers.add(new UnderlineToCamelArgumentResolver(dateConvert(), dateTimeConvert()));
	}

	@Bean
	public Converter<String, LocalDate> dateConvert() {
		return new Converter<String, LocalDate>() {
			@Override
			public LocalDate convert(String source) {
				return LocalDate.parse(source, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
			}
		};
	}

	@Bean
	public Converter<String, LocalDateTime> dateTimeConvert() {
		return new Converter<String, LocalDateTime>() {
			@Override
			public LocalDateTime convert(String source) {
				return LocalDateTime.parse(source, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
			}
		};
	}

	/**
	 * @see https://www.cnblogs.com/mr-yang-localhost/p/7812038.html#_lab2_3_4
	 */
	@Bean
	public Validator validator(){
		ValidatorFactory validatorFactory = Validation.byProvider( HibernateValidator.class )
				.configure()
				.addProperty( "hibernate.validator.fail_fast", "true" )
				.buildValidatorFactory();
		Validator validator = validatorFactory.getValidator();
		return validator;
	}
}