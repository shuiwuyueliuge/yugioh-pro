package cn.mayu.yugioh.transform.service;

import static cn.mayu.yugioh.transform.config.AsyncConfig.ASYNC_EXECUTOR_NAME;
import java.io.File;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import cn.mayu.yugioh.common.core.util.FtpHelper;
import cn.mayu.yugioh.transform.config.FtpImgConfig;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ImageServiceImpl implements ImageService {

	@Autowired
	private FtpImgConfig ftpImgConfig;

	@Override
	@Async(ASYNC_EXECUTOR_NAME)
	public void uploadInFTP(byte[] imageData, Integer cardId, Integer cardType) {
		String imgPath = String.format("%simg%s", File.separator, File.separator);
		String imgName = String.format("%s_%s.jpg", cardId, cardType);
		try {
			FtpHelper.builder().host(ftpImgConfig.getHost()).port(ftpImgConfig.getPort()).user(ftpImgConfig.getUser())
					.psw(ftpImgConfig.getPsw()).build().uploadFile(imgPath, imgName, imageData);
		} catch (Exception e) {
			log.error("upload [{}] img error: [{}]", imgName, e);
		}
	}
}
