package icu.easyj.spring.boot.sample.web.param.crypto.restcontroller;

import javax.annotation.Resource;
import javax.servlet.Filter;

import icu.easyj.spring.boot.test.BaseSpringBootMockMvcTest;
import icu.easyj.spring.boot.test.MockRequest;
import icu.easyj.web.param.crypto.IParamCryptoFilterProperties;
import icu.easyj.web.param.crypto.IParamCryptoHandler;
import icu.easyj.web.param.crypto.IParamCryptoHandlerProperties;
import icu.easyj.web.param.crypto.ParamCryptoFilter;
import icu.easyj.web.param.crypto.exception.ParamDecryptException;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.StringUtils;

import static icu.easyj.spring.boot.sample.web.param.crypto.BeforeAllTest.SYMMETRIC_CRYPTO;

/**
 * {@link TestEncryptQueryStringController} 测试类
 *
 * @author wangliang181230
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestEncryptQueryStringControllerTest extends BaseSpringBootMockMvcTest {

	@Resource
	IParamCryptoFilterProperties cryptoFilterProperties;
	@Resource
	IParamCryptoHandlerProperties cryptoHandlerProperties;
	@Resource
	IParamCryptoHandler cryptoHandler;

	@Override
	@Before
	public void initMockMvcBeforeEachTestMethod() {
		// 创建需执行的过滤器
		Filter filter = new ParamCryptoFilter(cryptoFilterProperties, cryptoHandlerProperties, cryptoHandler);

		// 初始化MockMvc
		this.initMockMvc(filter);
	}

	/**
	 * case: 入参正常加密的情况
	 *
	 * @see TestEncryptQueryStringController#testQueryStringParamCrypto(String, String)
	 */
	@Test
	public void testEncryptQueryStringSuccess() throws Exception {
		String path = "/test/querystring";
		String queryString = "s1=111啊啊啊&s2=222呀呀呀&s2=哇哇哇";
		String encryptedQueryString = SYMMETRIC_CRYPTO.encryptBase64(queryString);

		// 创建模拟请求
		MockRequest mockRequest;
		if (StringUtils.hasText(cryptoFilterProperties.getQueryStringName())) {
			mockRequest = mockGet(path)
					.queryParam(cryptoFilterProperties.getQueryStringName(), encryptedQueryString);
		} else {
			mockRequest = mockGet(path + "?" + encryptedQueryString);
		}

		// 发送请求，并校验响应
		mockRequest.send() // 发送请求
				.status().isOk() // 判断请求成功
				.contentType().is(MediaType.TEXT_PLAIN) // 加密过，为Base64串，所以不是JSON
				.content().is(SYMMETRIC_CRYPTO.encryptBase64("{\"name\":\"111啊啊啊222呀呀呀,哇哇哇\"}"));
	}

	/**
	 * case: 入参未加密的情况
	 *
	 * @see TestEncryptQueryStringController#testQueryStringParamCrypto(String, String)
	 */
	@Test
	public void testEncryptQueryStringFail1() {
		String path = "/test/querystring";
		String queryString = "s1=111啊啊啊&s2=222呀呀呀&s2=哇哇哇";

		// 创建模拟请求
		MockRequest mockRequest;
		if (StringUtils.hasLength(cryptoFilterProperties.getQueryStringName())) {
			mockRequest = super.mockGet(path)
					.queryParam(cryptoFilterProperties.getQueryStringName(), queryString);
		} else {
			mockRequest = super.mockGet(path + "?" + queryString);
		}

		// 发送请求
		try {
			mockRequest.send();
		} catch (Exception e) {
			Assertions.assertEquals(ParamDecryptException.class, e.getClass());
			Assertions.assertEquals("QueryString入参未加密或格式有误，解密失败", e.getMessage());
			return;
		}

		throw new RuntimeException("未抛出异常，和预期不一样，应该会抛出ParamDecryptException才对。");
	}
}
