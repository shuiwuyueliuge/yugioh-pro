package cn.mayu.yugioh.basic.gateway.route;

import java.util.LinkedHashMap;
import java.util.Map;
import lombok.Data;

//2.创建过滤器模型
@Data
public class GatewayFilterDefinition {
  //Filter Name
  private String name;
  //对应的路由规则
  private Map<String, String> args = new LinkedHashMap<>();
}