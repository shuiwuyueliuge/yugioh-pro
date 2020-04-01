package cn.mayu.yugioh.basic.route.manage.model.dto;

import java.util.LinkedHashMap;
import java.util.Map;
import lombok.Data;

@Data
public class FilterDTO {

  private String name;

  private Map<String, String> args = new LinkedHashMap<String, String>();
}