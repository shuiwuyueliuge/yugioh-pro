package cn.mayu.yugioh.common.core.html;

import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class VisitResponse {

	private int statusCode;

	private Map<String, String> headers;

	private String html;
}