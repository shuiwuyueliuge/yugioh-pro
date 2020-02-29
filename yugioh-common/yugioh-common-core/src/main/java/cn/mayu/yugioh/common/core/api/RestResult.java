package cn.mayu.yugioh.common.core.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RestResult<D> {

	private int code;
	
	private String msg;
	
	private D data;
	
	public RestResult(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}
}
