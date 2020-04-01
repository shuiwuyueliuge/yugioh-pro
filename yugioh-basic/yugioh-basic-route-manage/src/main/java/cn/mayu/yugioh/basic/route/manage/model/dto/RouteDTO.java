package cn.mayu.yugioh.basic.route.manage.model.dto;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Data
public class RouteDTO {

  private String id;

  private List<PredicateDTO> predicates = new ArrayList<PredicateDTO>();

  private List<FilterDTO> filters = new ArrayList<FilterDTO>();

  private String uri;

  private int order;
}