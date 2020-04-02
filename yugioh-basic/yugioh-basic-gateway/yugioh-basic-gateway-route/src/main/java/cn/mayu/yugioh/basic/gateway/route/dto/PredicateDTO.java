package cn.mayu.yugioh.basic.gateway.route.dto;

import java.util.LinkedHashMap;
import java.util.Map;
import lombok.Data;

@Data
public class PredicateDTO {

  private String name;

  private Map<String, String> args = new LinkedHashMap<String, String>();
  
}