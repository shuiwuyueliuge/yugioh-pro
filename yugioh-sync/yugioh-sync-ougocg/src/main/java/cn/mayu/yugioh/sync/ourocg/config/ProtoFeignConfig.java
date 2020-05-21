package cn.mayu.yugioh.sync.ourocg.config;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.ResponseEntityDecoder;
import org.springframework.cloud.openfeign.support.SpringDecoder;
import org.springframework.cloud.openfeign.support.SpringEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.protobuf.ProtobufHttpMessageConverter;
import feign.codec.Decoder;
import feign.codec.Encoder;

@Configuration
public class ProtoFeignConfig {
	
  @Autowired
  private ObjectFactory<HttpMessageConverters> messageConverterObjectFactory;
  
  @Bean
  public ProtobufHttpMessageConverter protobufHttpMessageConverter() {
      return new ProtobufHttpMessageConverter();
  }

  @Bean
  public Encoder springEncoder() {
      return new SpringEncoder(messageConverterObjectFactory);
  }

  @Bean
  public Decoder springDecoder() {
      return new ResponseEntityDecoder(new SpringDecoder(messageConverterObjectFactory));
  }
}