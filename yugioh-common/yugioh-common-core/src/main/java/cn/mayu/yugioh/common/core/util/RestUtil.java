package cn.mayu.yugioh.common.core.util;

import java.time.Duration;
import java.util.Map;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.client.RestTemplateCustomizer;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

public class RestUtil {

	public static <T> T get(String url, Class<T> responseType, long connectTimeout, long readTimeout,
			RestTemplateCustomizer... customizers) {
		return doGet(initRestTemplate(connectTimeout, readTimeout, customizers), url, responseType);
	}

	public static <T> T get(String url, Class<T> responseType, long connectTimeout, long readTimeout) {
		return doGet(initRestTemplate(connectTimeout, readTimeout), url, responseType);
	}

	public static <T> T get(String url, Class<T> responseType, RestTemplateCustomizer... customizers) {
		return doGet(initRestTemplate(customizers), url, responseType);
	}

	public static <T> T get(String url, Class<T> responseType) {
		return doGet(initRestTemplate(), url, responseType);
	}

	public static <T> T post(String url, Map<String, String> paramsMap, Class<T> responseType) {
		return doPost(initRestTemplate(), url, paramsMap, responseType);
	}

	public static <T> T post(String url, Map<String, String> paramsMap, Class<T> responseType, long connectTimeout,
			long readTimeout, RestTemplateCustomizer... customizers) {
		return doPost(initRestTemplate(connectTimeout, readTimeout, customizers), url, paramsMap, responseType);
	}

	public static <T> T post(String url, Map<String, String> paramsMap, Class<T> responseType, long connectTimeout,
			long readTimeout) {
		return doPost(initRestTemplate(connectTimeout, readTimeout), url, paramsMap, responseType);
	}

	public static <T> T post(String url, Map<String, String> paramsMap, Class<T> responseType,
			RestTemplateCustomizer... customizers) {
		return doPost(initRestTemplate(customizers), url, paramsMap, responseType);
	}
	
	public static <T> T postForJson(String url, String jsonData, Class<T> responseType) {
		return doPostForJson(initRestTemplate(), url, jsonData, responseType);
	}

	public static <T> T postForJson(String url, String jsonData, Class<T> responseType, long connectTimeout,
			long readTimeout, RestTemplateCustomizer... customizers) {
		return doPostForJson(initRestTemplate(connectTimeout, readTimeout, customizers), url, jsonData, responseType);
	}

	public static <T> T postForJson(String url, String jsonData, Class<T> responseType, long connectTimeout, long readTimeout) {
		return doPostForJson(initRestTemplate(connectTimeout, readTimeout), url, jsonData, responseType);
	}

	public static <T> T postForJson(String url, String jsonData, Class<T> responseType, RestTemplateCustomizer... customizers) {
		return doPostForJson(initRestTemplate(customizers), url, jsonData, responseType);
	}

	private static RestTemplate initRestTemplate(long connectTimeout, long readTimeout,
			RestTemplateCustomizer... customizers) {
		RestTemplateBuilder builder = new RestTemplateBuilder(customizers);
		builder.setConnectTimeout(Duration.ofMillis(connectTimeout));
		builder.setReadTimeout(Duration.ofMillis(readTimeout));
		return builder.build();
	}

	private static RestTemplate initRestTemplate(RestTemplateCustomizer... customizers) {
		RestTemplateBuilder builder = new RestTemplateBuilder(customizers);
		return builder.build();
	}

	private static RestTemplate initRestTemplate() {
		RestTemplateBuilder builder = new RestTemplateBuilder(new DefaultRestTemplateCustomizer());
		return builder.build();
	}

	private static RestTemplate initRestTemplate(long connectTimeout, long readTimeout) {
		RestTemplateBuilder builder = new RestTemplateBuilder(new DefaultRestTemplateCustomizer());
		builder.setConnectTimeout(Duration.ofMillis(connectTimeout));
		builder.setReadTimeout(Duration.ofMillis(readTimeout));
		return builder.build();
	}

	private static <T> T doGet(RestTemplate restTemplate, String url, Class<T> responseType) {
		return restTemplate.getForObject(url, responseType);
	}

	private static <T> T doPost(RestTemplate restTemplate, String url, Map<String, String> paramsMap,
			Class<T> responseType) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<MultiValueMap<String, String>>(
				getMultiValueMap(paramsMap), headers);
		return restTemplate.postForObject(url, requestEntity, responseType);
	}
	
	private static <T> T doPostForJson(RestTemplate restTemplate, String url, String jsonData, Class<T> responseType) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> requestEntity = new HttpEntity<String>(jsonData, headers);
		return restTemplate.postForObject(url, requestEntity, responseType);
	}

	private static MultiValueMap<String, String> getMultiValueMap(final Map<String, String> paramsMap) {
		MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
		paramsMap.entrySet().stream().forEach(entity -> {
			params.add(entity.getKey(), entity.getValue());
		});
		return params;
	}
}

class DefaultRestTemplateCustomizer implements RestTemplateCustomizer {
	@Override
	public void customize(RestTemplate restTemplate) {
		// restTemplate.getInterceptors().add(new CustomClientHttpRequestInterceptor());
	}
}