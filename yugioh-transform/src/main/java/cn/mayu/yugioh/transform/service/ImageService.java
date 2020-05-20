package cn.mayu.yugioh.transform.service;

public interface ImageService {

	void uploadInFTP(byte[] imageData, Integer cardId, Integer cardType);
}
