package cn.mayu.yugioh.basic.gateway.route.service;

import java.util.concurrent.ThreadFactory;

public interface DictService extends Runnable, ThreadFactory  {

	String getValue(int type, int valueId);
}
