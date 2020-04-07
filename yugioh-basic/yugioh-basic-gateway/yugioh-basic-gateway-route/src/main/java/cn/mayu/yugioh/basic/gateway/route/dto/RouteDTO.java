package cn.mayu.yugioh.basic.gateway.route.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
public class RouteDTO {
 
  private int status;
  
  private RouteDefinition routeDefinition;
  
  @Data
  public static class RouteDefinition {
	  
	  private String id;
	  
	  private Integer tableId;

	  private List<PredicateDTO> predicates;

	  private List<FilterDTO> filters;

	  private String uri;

	  private int order;
  }
  
  @Getter
  @AllArgsConstructor
  public enum RouteStatus {
		
		CREATE(1), UPDATE(0), DELETE(-1);
		
		private int status;
	}
}

/**
[FilterDefinition{name='AddRequestHeader', args={_genkey_0=X-Request-red, _genkey_1=blue}}, 
FilterDefinition{name='AddRequestParameter', args={_genkey_0=red, _genkey_1=blue}}, 
FilterDefinition{name='AddResponseHeader', args={_genkey_0=X-Response-Red, _genkey_1=Blue}}, 
FilterDefinition{name='DedupeResponseHeader', args={_genkey_0=Access-Control-Allow-Credentials Access-Control-Allow-Origin}}, 
FilterDefinition{name='CircuitBreaker', args={_genkey_0=myCircuitBreaker}}, 
FilterDefinition{name='RewritePath', args={_genkey_0=/consumingServiceEndpoint, _genkey_1=/backingServiceEndpoint}}, 
FilterDefinition{name='RewriteLocationResponseHeader', args={_genkey_0=AS_IN_REQUEST, _genkey_1=Location}}, 
FilterDefinition{name='RewriteResponseHeader', args={_genkey_0=X-Response-Red, _genkey_1=password=[^&]+, _genkey_2=password=***}}, 
FilterDefinition{name='SaveSession', args={}}, 
FilterDefinition{name='SetPath', args={_genkey_0=/{segment}}}, 
FilterDefinition{name='SetRequestHeader', args={_genkey_0=X-Request-Red, _genkey_1=Blue}}, 
FilterDefinition{name='SetResponseHeader', args={_genkey_0=foo, _genkey_1=bar-{segment}}}, 
FilterDefinition{name='SetStatus', args={_genkey_0=401}}, 
FilterDefinition{name='StripPrefix', args={_genkey_0=2}}, 
FilterDefinition{name='MapRequestHeader', args={_genkey_0=Blue, _genkey_1=X-Request-Red}}, 
FilterDefinition{name='PreserveHostHeader', args={}}, 
FilterDefinition{name='PrefixPath', args={_genkey_0=/mypath}}, 
FilterDefinition{name='RedirectTo', args={_genkey_0=302, _genkey_1=https://acme.org}}, 
FilterDefinition{name='RemoveRequestHeader', args={_genkey_0=X-Request-Foo}}, 
FilterDefinition{name='RemoveResponseHeader', args={_genkey_0=X-Response-Foo}}, 
FilterDefinition{name='RemoveRequestParameter', args={_genkey_0=red}}, 
FilterDefinition{name='FallbackHeaders', args={executionExceptionTypeHeaderName=Test-Header}}, 
FilterDefinition{name='Hystrix', args={_genkey_0=myCommandName}}, 
FilterDefinition{name='Hystrix', args={name=fetchIngredients, fallbackUri=forward:/fallback}}, 
FilterDefinition{name='RequestRateLimiter', args={redis-rate-limiter.replenishRate=10, redis-rate-limiter.burstCapacity=20, redis-rate-limiter.requestedTokens=1}}, 
FilterDefinition{name='Retry', args={retries=3, statuses=BAD_GATEWAY, methods=GET,POST, backoff.firstBackoff=10ms, backoff.maxBackoff=50ms, backoff.factor=2, backoff.basedOnPreviousValue=false}}, 
FilterDefinition{name='RequestSize', args={maxSize=5000000}}]
 */

/**
[FilterDefinition{name='AddRequestHeader', args={_genkey_0=X-Request-red,blue, _genkey_1=X-Request-red,blue}}, 
FilterDefinition{name='SaveSession', args={}}, 
FilterDefinition{name='FallbackHeaders', args={executionExceptionTypeHeaderName=Test-Header}}, 
FilterDefinition{name='Retry', args={retries=3, statuses=BAD_GATEWAY, methods=GET,POST, backoff.firstBackoff=10ms, backoff.maxBackoff=50ms, backoff.factor=2, backoff.basedOnPreviousValue=false}}]
*/