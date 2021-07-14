package icu.easyj.spring.boot.sample.web.param.crypto;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import icu.easyj.crypto.CryptoFactory;
import icu.easyj.crypto.symmetric.ISymmetricCrypto;
import icu.easyj.web.param.crypto.IParamCryptoHandler;
import icu.easyj.web.param.crypto.IParamCryptoHandlerProperties;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * {@link WebParamCryptoSampleApplication} 测试类
 *
 * @author wangliang181230
 */
@SpringBootTest
class WebParamCryptoSampleApplicationTest {
	static final String ALGORITHM_TYPE = "AES";
	static final String MODE = "CBC";
	static final String PADDING = "PKCS7Padding";

	static final String ALGORITHM = ALGORITHM_TYPE + "/" + MODE + "/" + PADDING;
	static final String KEY = "12345678901234567890123456789012";
	static final String IV = "1234567890123456";
	static final Charset CHARSET = StandardCharsets.UTF_8;

	static final ISymmetricCrypto SYMMETRIC_CRYPTO = CryptoFactory.getSymmetricCrypto(ALGORITHM, KEY, IV, CHARSET);

	@Autowired
	IParamCryptoHandlerProperties cryptoHandlerProperties;

	@Autowired
	IParamCryptoHandler cryptoHandler;

	/**
	 * 测试是否能够启动
	 */
	@Test
	void testStartup() {
		assertNotNull(cryptoHandlerProperties);
		assertNotNull(cryptoHandler);
	}

	/**
	 * 测试读取的配置信息是否正确
	 */
	@Test
	void testHandlerProperties() {
		assertEquals(ALGORITHM, cryptoHandlerProperties.getAlgorithm());
		assertEquals(KEY, cryptoHandlerProperties.getKey());
		assertEquals(IV, cryptoHandlerProperties.getIv());
		assertEquals(CHARSET, cryptoHandlerProperties.getCharset());

		assertTrue(cryptoHandlerProperties.isNeedEncryptInputParam());
		assertTrue(cryptoHandlerProperties.isNeedEncryptOutputParam());
	}

	@Test
	void testHandler() throws Exception {
		String data = "112233";

		String encryptedData1 = cryptoHandler.encrypt(data);
		String data1 = cryptoHandler.decrypt(encryptedData1);
		assertEquals(data1, data);

		String encryptedData2 = SYMMETRIC_CRYPTO.encryptBase64(data, StandardCharsets.UTF_8);
		String data2 = SYMMETRIC_CRYPTO.decryptStr(encryptedData2, StandardCharsets.UTF_8);
		assertEquals(data2, data);

		assertEquals(encryptedData1, encryptedData2);
	}
}
