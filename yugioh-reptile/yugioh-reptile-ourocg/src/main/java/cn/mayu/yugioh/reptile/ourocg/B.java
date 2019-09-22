package cn.mayu.yugioh.reptile.ourocg;

import org.springframework.web.client.RestTemplate;

public class B implements Runnable {
	
	private int num;
	
	public B(int num) {
		this.setNum(num);
	}

	@Override
	public void run() {
		CardMetaDataFinder.exec(num);
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}
}
