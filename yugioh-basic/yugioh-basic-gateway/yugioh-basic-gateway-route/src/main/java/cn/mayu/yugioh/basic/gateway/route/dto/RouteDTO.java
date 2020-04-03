package cn.mayu.yugioh.basic.gateway.route.dto;

import java.util.ArrayList;
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
	  
	  private String serviceId;

	  private List<PredicateDTO> predicates = new ArrayList<PredicateDTO>();

	  private List<FilterDTO> filters = new ArrayList<FilterDTO>();

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