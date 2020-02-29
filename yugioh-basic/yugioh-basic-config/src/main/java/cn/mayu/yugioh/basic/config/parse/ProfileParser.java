package cn.mayu.yugioh.basic.config.parse;

import java.io.InputStream;
import java.util.Map;

public interface ProfileParser {

	Map<Object, Object> parse(InputStream stream) throws Exception;
}
