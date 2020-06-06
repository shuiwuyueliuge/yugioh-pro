package cn.yugioh.cardsource.core.ourocg.model;

import lombok.Data;

@Data
public class Meta {

	private String keyword;
	
	private int count;
	
	private int totalPage;
	
	private int curPage;
	
	private String title;
	
	private String metaDesc;
	
	private String metaKw;
}
