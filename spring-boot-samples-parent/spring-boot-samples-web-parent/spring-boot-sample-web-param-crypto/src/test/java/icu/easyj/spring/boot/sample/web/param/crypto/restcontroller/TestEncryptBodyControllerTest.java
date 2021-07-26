package icu.easyj.spring.boot.sample.web.param.crypto.restcontroller;

import java.nio.charset.StandardCharsets;

import com.alibaba.fastjson.JSON;
import icu.easyj.spring.boot.sample.web.param.crypto.param.TestBodyParam;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.util.NestedServletException;

import static icu.easyj.spring.boot.sample.web.param.crypto.BeforeAllTest.SYMMETRIC_CRYPTO;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * {@link TestEncryptBodyController} 测试类
 *
 * @author wangliang181230
 */
@SpringBootTest
class TestEncryptBodyControllerTest {

	@Autowired
	WebApplicationContext wac;

	@Autowired
	IParamCryptoFilterProperties cryptoFilterProperties;
	@Autowired
	IParamCryptoHandlerProperties cryptoHandlerProperties;
	@Autowired
	IParamCryptoHandler cryptoHandler;

	MockMvc mockMvc;

	@BeforeEach
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(wac)
				.addFilter(new ParamCryptoFilter(cryptoFilterProperties, cryptoHandlerProperties, cryptoHandler))
				.build();
	}

	/**
	 * case: 入参正常加密的情况
	 */
	@Test
	void testEncryptBodySuccess() throws Exception {
		String path = "/test/body";
		TestBodyParam bodyParam = new TestBodyParam("111", "222");
		String encryptedBody = SYMMETRIC_CRYPTO.encryptBase64(JSON.toJSONString(bodyParam));

		// 创建模拟请求
		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
				.post(path)
				.contentType(MediaType.APPLICATION_JSON)
				.content(encryptedBody);

		// 发送请求
		ResultActions resultActions = mockMvc.perform(mockRequest);
		// 接收响应
		MvcResult result = resultActions.andReturn();
		// 获取响应
		int status = result.getResponse().getStatus();
//		String contentType = result.getResponse().getContentType();
//		int contentLength = result.getResponse().getContentLength();
		String content = result.getResponse().getContentAsString(StandardCharsets.UTF_8);
		// 校验响应内容
		assertEquals(HttpStatus.OK.value(), status);
		assertEquals(SYMMETRIC_CRYPTO.encryptBase64("{\"name\":\"" + bodyParam.getAaa() + bodyParam.getBbb() + "\"}"), content);
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
		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
				.post(path)
				.contentType(MediaType.APPLICATION_JSON)
				.content(body);

		// 发送请求
		try {
			mockMvc.perform(mockRequest);
		} catch (Exception e) {
			Assertions.assertEquals(NestedServletException.class, e.getClass());
			Assertions.assertEquals(ParamDecryptException.class, e.getCause().getClass());
			Assertions.assertEquals("Body入参未加密或格式有误，解密失败", e.getCause().getMessage());
			return;
		}

		throw new RuntimeException("未抛出异常，和预期不一样，应该会抛出ParamDecryptException才对。");
	}
}
