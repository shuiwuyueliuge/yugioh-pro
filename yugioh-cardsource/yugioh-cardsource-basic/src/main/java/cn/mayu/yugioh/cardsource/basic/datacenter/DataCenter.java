package cn.mayu.yugioh.cardsource.basic.datacenter;

public interface DataCenter {

	boolean exists();
	
	String description();

	default String url() {
		return null;
	}
}
