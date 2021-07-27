package icu.easyj.spring.boot.sample.web.param.crypto.restcontroller;

import javax.servlet.Filter;

import com.alibaba.fastjson.JSON;
import icu.easyj.spring.boot.sample.web.param.crypto.param.TestBodyParam;
import icu.easyj.spring.boot.test.BaseSpringBootMockMvcTest;
import icu.easyj.spring.boot.test.MockRequest;
import icu.easyj.web.param.crypto.IParamCryptoFilterProperties;
import icu.easyj.web.param.crypto.IParamCryptoHandler;
import icu.easyj.web.param.crypto.IParamCryptoHandlerProperties;
import icu.easyj.web.param.crypto.ParamCryptoFilter;
import icu.easyj.web.param.crypto.exception.ParamDecryptException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
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
class TestEncryptBodyControllerTest extends BaseSpringBootMockMvcTest {

	@Autowired
	IParamCryptoFilterProperties cryptoFilterProperties;
	@Autowired
	IParamCryptoHandlerProperties cryptoHandlerProperties;
	@Autowired
	IParamCryptoHandler cryptoHandler;

	@Override
	@BeforeEach
	public void beforeEach() {
		// 创建需执行的过滤器
		Filter filter = new ParamCryptoFilter(cryptoFilterProperties, cryptoHandlerProperties, cryptoHandler);

		// 初始化MockMvc
		this.initMockMvc(filter);
	}

	/**
	 * case: 入参正常加密的情况
	 */
	@Test
	void testEncryptBodySuccess() throws Exception {
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
	 * case: 入参未加密的情况
	 */
	@Test
	void testEncryptBodyFail1() throws Exception {
		String path = "/test/body";
		TestBodyParam bodyParam = new TestBodyParam("111", "222");
		String body = JSON.toJSONString(bodyParam);

		// 创建模拟请求
		MockRequest mockRequest = super.mockPost(path)
				.contentType(MediaType.APPLICATION_JSON)
				.content(body);

		// 发送请求
		try {
			mockRequest.send();
		} catch (Exception e) {
			Assertions.assertEquals(NestedServletException.class, e.getClass());
			Assertions.assertEquals(ParamDecryptException.class, e.getCause().getClass());
			Assertions.assertEquals("Body入参未加密或格式有误，解密失败", e.getCause().getMessage());
			return;
		}

		throw new RuntimeException("未抛出异常，和预期不一样，应该会抛出ParamDecryptException才对。");
	}
}
