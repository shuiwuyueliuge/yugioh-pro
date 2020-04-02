package cn.mayu.yugioh.basic.gateway.route.dto;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Data
public class RouteDTO {

  private Integer id;
  
  private String serviceId;

  private List<PredicateDTO> predicates = new ArrayList<PredicateDTO>();

  private List<FilterDTO> filters = new ArrayList<FilterDTO>();

  private String uri;

  private int order;
  
}