package cn.mayu.yugioh.common.core.result;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class YgoResult<D> {

	private int code;
	
	private String msg;
	
	private D data;
	
	public YgoResult(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}
}
