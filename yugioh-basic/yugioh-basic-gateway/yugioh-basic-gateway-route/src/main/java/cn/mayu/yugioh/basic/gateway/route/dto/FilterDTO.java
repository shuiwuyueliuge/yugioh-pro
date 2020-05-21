package cn.mayu.yugioh.basic.gateway.route.dto;

import java.util.Map;
import lombok.Data;

@Data
public class FilterDTO {

  private String name;

  private Map<String, String> args;
  
}