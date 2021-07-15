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
class BeforeAllTest {

	static final String ALGORITHM_TYPE = "AES";
	static final String MODE = "CBC";
	static final String PADDING = "PKCS7Padding";

	static final String ALGORITHM = ALGORITHM_TYPE + "/" + MODE + "/" + PADDING;
	static final String KEY = "12345678901234567890123456789012";
	static final String IV = "1234567890123456";
	static final Charset CHARSET = StandardCharsets.UTF_8;

	static final ISymmetricCrypto SYMMETRIC_CRYPTO = CryptoFactory.getSymmetricCrypto(ALGORITHM, KEY, IV, CHARSET);
}
