package cn.mayu.yugioh.sync.ourocg.model;

import lombok.Data;

@Data
public class Meta {

	private String keyword;
	
	private int count;
	
	private int totalPage;
	
	private int curPage;
}
