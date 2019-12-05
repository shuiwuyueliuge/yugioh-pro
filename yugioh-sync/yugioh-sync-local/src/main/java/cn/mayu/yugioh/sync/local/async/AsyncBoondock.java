package cn.mayu.yugioh.sync.local.async;

import java.io.File;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import cn.mayu.yugioh.common.core.util.Base64Util;
import cn.mayu.yugioh.common.core.util.FtpHelper;
import cn.mayu.yugioh.facade.sync.home.CardProto.CardEntity;
import cn.mayu.yugioh.sync.local.config.AsyncConfig;
import cn.mayu.yugioh.sync.local.config.FtpImgConfig;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class AsyncBoondock {
	
	@Autowired
	private FtpImgConfig ftpImgConfig;
	
	@Async(AsyncConfig.ASYNC_EXECUTOR_NAME)
	public void saveImageInDisk(CardEntity entity) {
		if (entity.getImgUrl() == null) {
			return;
		}

		String imgPath = String.format("%simg%s", File.separator, File.separator);
		String imgName = String.format("%s.jpg", entity.getId());
		try {
			FtpHelper.builder().host(ftpImgConfig.getHost()).port(ftpImgConfig.getPort())
							   .user(ftpImgConfig.getUser()).psw(ftpImgConfig.getPsw()).build()
							   .uploadFile(imgPath, imgName, Base64Util.decoder(entity.getImgUrl()));
		} catch (Exception e) {
			log.error("upload img error: [{}]", e);
		}
	}
}
