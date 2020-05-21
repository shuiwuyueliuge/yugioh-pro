package cn.mayu.yugioh.sync.ourocg.model;

import java.util.List;

import lombok.Data;

@Data
public class OurocgMetaData {

	private List<OurocgCard> cards;
	
	private int list;
	
	private Meta meta;
}
