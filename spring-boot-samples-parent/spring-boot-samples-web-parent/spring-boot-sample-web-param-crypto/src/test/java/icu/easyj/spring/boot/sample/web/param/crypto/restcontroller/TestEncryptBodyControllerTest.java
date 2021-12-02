package icu.easyj.spring.boot.sample.web.param.crypto.restcontroller;

import javax.annotation.Resource;
import javax.servlet.Filter;

import com.alibaba.fastjson.JSON;
import icu.easyj.spring.boot.sample.web.param.crypto.param.TestBodyParam;
import icu.easyj.spring.boot.test.BaseSpringBootMockMvcTest;
import icu.easyj.web.param.crypto.IParamCryptoFilterProperties;
import icu.easyj.web.param.crypto.IParamCryptoHandler;
import icu.easyj.web.param.crypto.IParamCryptoHandlerProperties;
import icu.easyj.web.param.crypto.ParamCryptoFilter;
import icu.easyj.web.param.crypto.exception.ParamDecryptException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.web.util.NestedServletException;

import static icu.easyj.spring.boot.sample.web.param.crypto.BeforeAllTest.SYMMETRIC_CRYPTO;

/**
 * {@link TestEncryptBodyController} 测试类
 *
 * @author wangliang181230
 */
@SpringBootTest
public class TestEncryptBodyControllerTest extends BaseSpringBootMockMvcTest {

	@Resource
	IParamCryptoFilterProperties cryptoFilterProperties;
	@Resource
	IParamCryptoHandlerProperties cryptoHandlerProperties;
	@Resource
	IParamCryptoHandler cryptoHandler;

	@Override
	@BeforeEach
	public void initMockMvcBeforeEachTestMethod() {
		// 创建需执行的过滤器
		Filter filter = new ParamCryptoFilter(cryptoFilterProperties, cryptoHandlerProperties, cryptoHandler);

		// 初始化MockMvc
		this.initMockMvc(filter);
	}

	/**
	 * 正常case: 测试入参正常加密的情况
	 *
	 * @see TestEncryptBodyController#testBodyParamCrypto(TestBodyParam)
	 */
	@Test
	public void testEncryptBodySuccess() throws Exception {
		String path = "/test/body";
		TestBodyParam bodyParam = new TestBodyParam("111", "222");
		String encryptedBody = SYMMETRIC_CRYPTO.encryptBase64(JSON.toJSONString(bodyParam));

		super.mockPost(path)
				// 设置请求内容
				.contentType(MediaType.APPLICATION_JSON)
				.content(encryptedBody)
				// 发送请求
				.send()
				// 校验响应内容
				.status().isOk()
				.contentType().is(MediaType.TEXT_PLAIN)
				.content().is(SYMMETRIC_CRYPTO.encryptBase64("{\"name\":\"" + bodyParam.getAaa() + bodyParam.getBbb() + "\"}"));
	}

	/**
	 * 异常case: 测试入参未加密的情况
	 *
	 * @see TestEncryptBodyController#testBodyParamCrypto(TestBodyParam)
	 */
	@Test
	public void testEncryptBodyFail() {
		String path = "/test/body";
		TestBodyParam bodyParam = new TestBodyParam("111", "222");

		try {
			super.mockPost(path)
					.content(bodyParam)
					.send();
			throw new AssertionError("未抛出异常，和预期不一样，预期应该会抛出NestedServletException才对。");
		} catch (Exception e) {
			Assertions.assertEquals(NestedServletException.class, e.getClass());
			Assertions.assertEquals(ParamDecryptException.class, e.getCause().getClass());
			Assertions.assertEquals("Body入参未加密或格式有误，解密失败", e.getCause().getMessage());
			// 执行到这里，表示和预期一致，测试异常case成功
		}
	}
}
