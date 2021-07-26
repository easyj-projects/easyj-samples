package icu.easyj.spring.boot.sample.web.param.crypto;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import icu.easyj.crypto.CryptoFactory;
import icu.easyj.crypto.symmetric.ISymmetricCrypto;

/**
 * 在所有测试之前执行的类
 *
 * @author wangliang181230
 */
public class BeforeAllTest {

	public static final String ALGORITHM_TYPE = "AES";
	public static final String MODE = "CBC";
	public static final String PADDING = "PKCS7Padding";

	public static final String ALGORITHM = ALGORITHM_TYPE + "/" + MODE + "/" + PADDING;
	public static final String KEY = "12345678901234567890123456789012";
	public static final String IV = "1234567890123456";
	public static final Charset CHARSET = StandardCharsets.UTF_8;

	public static final ISymmetricCrypto SYMMETRIC_CRYPTO = CryptoFactory.getSymmetricCrypto(ALGORITHM, KEY, IV, CHARSET);
}
