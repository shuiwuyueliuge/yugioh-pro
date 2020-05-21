package cn.mayu.yugioh.common.core.util;

import java.security.Key;
import java.util.Map.Entry;
import java.util.Set;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

public class JwtUtil {

	public static Set<Entry<String, Object>> parse(Key rsa, String token) throws Exception {
		Claims claims = Jwts.parser().setSigningKey(rsa).parseClaimsJws(token).getBody();
	    return claims.entrySet();
	}
}
