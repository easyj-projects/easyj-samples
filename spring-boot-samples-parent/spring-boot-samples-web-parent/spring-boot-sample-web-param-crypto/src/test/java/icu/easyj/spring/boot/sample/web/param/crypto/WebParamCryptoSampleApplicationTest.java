package icu.easyj.spring.boot.sample.web.param.crypto;

import java.nio.charset.StandardCharsets;
import javax.annotation.Resource;

import icu.easyj.web.param.crypto.IParamCryptoHandler;
import icu.easyj.web.param.crypto.IParamCryptoHandlerProperties;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static icu.easyj.spring.boot.sample.web.param.crypto.BeforeAllTest.ALGORITHM;
import static icu.easyj.spring.boot.sample.web.param.crypto.BeforeAllTest.CHARSET;
import static icu.easyj.spring.boot.sample.web.param.crypto.BeforeAllTest.IV;
import static icu.easyj.spring.boot.sample.web.param.crypto.BeforeAllTest.KEY;
import static icu.easyj.spring.boot.sample.web.param.crypto.BeforeAllTest.SYMMETRIC_CRYPTO;
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

	@Resource
	IParamCryptoHandlerProperties cryptoHandlerProperties;

	@Resource
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
	void testParamCryptoHandlerProperties() {
		assertEquals(ALGORITHM, cryptoHandlerProperties.getAlgorithm());
		assertEquals(KEY, cryptoHandlerProperties.getKey());
		assertEquals(IV, cryptoHandlerProperties.getIv());
		assertEquals(CHARSET, cryptoHandlerProperties.getCharset());

		assertTrue(cryptoHandlerProperties.isNeedEncryptInputParam());
		assertTrue(cryptoHandlerProperties.isNeedEncryptOutputParam());
	}

	@Test
	void testParamCryptoHandler() {
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
