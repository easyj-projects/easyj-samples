package icu.easyj.spring.boot.sample.web.param.crypto;

import icu.easyj.spring.boot.sample.web.param.crypto.restcontroller.TestParamCryptoController;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * {@link WebParamCryptoSampleApplication} 测试类
 *
 * @author wangliang181230
 */
@SpringBootTest
class WebParamCryptoSampleApplicationTest {

	@Autowired
	TestParamCryptoController testParamCryptoController;

	/**
	 * 测试是否能够启动
	 */
	@Test
	void testStartup() {
		Assertions.assertNotNull(testParamCryptoController);
	}
}
