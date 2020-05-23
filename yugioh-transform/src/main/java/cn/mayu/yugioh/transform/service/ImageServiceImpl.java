package cn.mayu.yugioh.transform.service;

import java.io.File;
import org.springframework.beans.factory.annotation.Autowired;
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
	public void uploadInFTP(byte[] imageData, Integer cardId, Integer cardType) {
		String imgPath = String.format("%simg%s", File.separator, File.separator);
		String imgName = String.format("%s_%s.jpg", cardId, cardType);
		try {
			FtpHelper.builder().host(ftpImgConfig.getHost()).port(ftpImgConfig.getPort()).user(ftpImgConfig.getUser())
					.psw(ftpImgConfig.getPsw()).build().uploadFilePort(imgPath, imgName, imageData);
		} catch (Exception e) {
			log.error("upload [{}] img error: [{}]", imgName, e);
		}
	}
}
