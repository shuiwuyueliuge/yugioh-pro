package cn.mayu.yugioh.security.core.imagecode;

import cn.mayu.yugioh.common.core.util.ImgCodeHelper;
import cn.mayu.yugioh.security.core.base.config.EhcacheConfig;
import org.ehcache.Cache;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.util.Random;

public class DefaultImageVerificationCodeManager extends ImageVerificationCodeManager {

    private Cache<String, String> cache;

    public DefaultImageVerificationCodeManager(EhcacheConfig ehcacheConfig) {
        this.cache = ehcacheConfig.getCache();
    }

    @Override
    public void genCode(String key, HttpServletResponse response) throws Exception {
        // generate image code
        String imageCode = new Random().ints(1000, 9999).findFirst().getAsInt() + "";
        byte[] img = ImgCodeHelper.getWriteByteArray(imageCode);
        // send image code
        OutputStream out = response.getOutputStream();
        response.setContentType("image/png");
        out.write(img);
        // store image code
        cache.put(key, imageCode);
    }

    @Override
    public boolean validate(final String key, final String value) {
        String cached = "";
        synchronized ("") {
            cached = cache.get(key);
            cache.remove(key);
        }

        return (cached != null && cached.equals(value)) ? true : false;
    }
}
