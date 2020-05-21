package cn.mayu.yugioh.common.core.util;

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import javax.crypto.Cipher;

public class RSAUtil {

	private static final String RSA = "RSA";

	public static final String SHA1WITHRSA = "SHA1_With_RSA";

	public static final String SHA256WITHRSA = "SHA256_With_RSA";

	public static final String MD5WITHRSA = "MD5_With_RSA";

	private static final String UTF8 = "UTF-8";

	/**
	 * 还原私钥
	 * 
	 * @param keyBytes
	 * @return
	 * @throws Exception
	 */
	public static PrivateKey restorePrivateKey(String key) throws Exception {
		PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(key));
		return KeyFactory.getInstance(RSA).generatePrivate(keySpec);
	}

	/**
	 * 还原公钥
	 */
	public static PublicKey restorePublicKey(String key) throws Exception {
		X509EncodedKeySpec keySpec = new X509EncodedKeySpec(Base64.getDecoder().decode(key));
		return KeyFactory.getInstance(RSA).generatePublic(keySpec);
	}

	/**
	 * 私钥签名
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static String sign1WithRsa(PrivateKey privateKey, String msg, String algorithm) throws Exception {
		Signature signature = Signature.getInstance(algorithm);
		signature.initSign(privateKey);
		signature.update(msg.getBytes(UTF8));
		return Base64.getEncoder().encodeToString(signature.sign());
	}

	/**
	 * 公钥验签
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static Boolean checkSign(String msg, String sign, PublicKey key, String algorithm) throws Exception {
		Signature signature = Signature.getInstance(algorithm);
		signature.initVerify(key);
		signature.update(msg.getBytes(UTF8));
		return signature.verify(Base64.getDecoder().decode(sign));
	}

	/**
	 * RSA公私钥
	 * 
	 * @return
	 * @throws Exception
	 */
	public static KeyPair genKeyPair() throws Exception {
		KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(RSA);
		keyPairGen.initialize(1024);
		return keyPairGen.generateKeyPair();
	}

	/**
	 * RSA私钥字符串
	 */
	public static String getPrivateKey(KeyPair keyPair) {
		return Base64.getEncoder().encodeToString(keyPair.getPrivate().getEncoded());
	}

	/**
	 * RSA公钥字符串
	 */
	public static String getPublicKey(KeyPair keyPair) {
		return Base64.getEncoder().encodeToString(keyPair.getPublic().getEncoded());
	}

	/**
	 * 公钥加密
	 * @param content
	 * @param publicKey
	 * @return
	 * @throws Exception
	 */
	public static byte[] encrypt(byte[] content, PublicKey publicKey) throws Exception {
		Cipher cipher = Cipher.getInstance(RSA);
		cipher.init(Cipher.ENCRYPT_MODE, publicKey);// java默认"RSA"="RSA/ECB/PKCS1Padding"
		return cipher.doFinal(content);
	}

	/**
	 * 私钥解密
	 * @param content
	 * @param privateKey
	 * @return
	 * @throws Exception
	 */
	public static byte[] decrypt(byte[] content, PrivateKey privateKey) throws Exception {
		Cipher cipher = Cipher.getInstance(RSA);
		cipher.init(Cipher.DECRYPT_MODE, privateKey);
		return cipher.doFinal(content);
	}
}
