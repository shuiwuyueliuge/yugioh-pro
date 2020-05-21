package cn.mayu.yugioh.common.core.util;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class FtpHelper {

	private String host;

	private int port;

	private String user;

	private String psw;

	public boolean uploadFile(String ftpPath, String fileName, byte[] data) throws Exception {
		FTPClient client = null;
		try {
			client = connect();
			client.setControlEncoding("UTF-8");
			client.setFileType(FTPClient.BINARY_FILE_TYPE);
			makeDir(ftpPath, client);
			client.enterLocalPassiveMode();
			client.changeWorkingDirectory(ftpPath);
			ByteArrayInputStream byStream = new ByteArrayInputStream(data);
			client.storeFile(fileName, byStream);
			byStream.close();
			client.logout();
		} finally {
			if (client.isConnected()) {
				try {
					client.disconnect();
				} catch (IOException ioe) {
				}
			}
		}

		return true;
	}

	private void makeDir(String fileName, FTPClient client) throws Exception {
		while (true) {
			int separatorIndex = fileName.indexOf(File.separator);
			if (separatorIndex == -1) {
				break;
			}

			String subStr = fileName.substring(0, separatorIndex);
			if (!subStr.equals("")) {
				client.makeDirectory(subStr);
				client.changeWorkingDirectory(subStr);
			}

			fileName = fileName.substring(separatorIndex + 1, fileName.length());
		}
	}

	private FTPClient connect() throws Exception {
		FTPClient client = new FTPClient();
		client.connect(host, port);
		client.login(user, psw);
		if (!FTPReply.isPositiveCompletion(client.getReplyCode())) {
			client.disconnect();
			throw new RuntimeException();
		}

		return client;
	}
}
