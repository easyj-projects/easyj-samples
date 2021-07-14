package icu.easyj.spring.boot.sample.web.param.crypto;

import java.nio.charset.StandardCharsets;

import icu.easyj.spring.boot.sample.web.param.crypto.restcontroller.TestEncryptQueryStringController;
import icu.easyj.web.param.crypto.IParamCryptoFilterProperties;
import icu.easyj.web.param.crypto.IParamCryptoHandler;
import icu.easyj.web.param.crypto.IParamCryptoHandlerProperties;
import icu.easyj.web.param.crypto.ParamCryptoFilter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.StringUtils;
import org.springframework.web.context.WebApplicationContext;

import static icu.easyj.spring.boot.sample.web.param.crypto.WebParamCryptoSampleApplicationTest.SYMMETRIC_CRYPTO;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * {@link TestEncryptQueryStringController} 测试类
 *
 * @author wangliang181230
 */
@SpringBootTest
class TestEncryptQueryStringControllerTest {

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
	 * 测试正常的情况
	 *
	 * @throws Exception
	 */
	@Test
	void testEncryptQueryStringSuccess() throws Exception {
		String path = "/test/querystring";
		String queryString = "s1=111啊啊啊&s2=222呀呀呀&s2=哇哇哇";
		String encryptedQueryString = SYMMETRIC_CRYPTO.encryptBase64(queryString);

		// 创建模拟请求
		MockHttpServletRequestBuilder mockRequest;
		if (StringUtils.hasLength(cryptoFilterProperties.getParameterName())) {
			mockRequest = MockMvcRequestBuilders.get(path).queryParam(cryptoFilterProperties.getParameterName(), encryptedQueryString);
		} else {
			mockRequest = MockMvcRequestBuilders.get(path + "?" + encryptedQueryString);
		}

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
		assertEquals("{\"name\":\"111啊啊啊222呀呀呀,哇哇哇\"}", content);
	}

	/**
	 * 未加密
	 */
	@Test
	void testEncryptQueryStringFail1() throws Exception {
		String path = "/test/querystring";
		String queryString = "s1=111啊啊啊&s2=222呀呀呀&s2=哇哇哇";
		String encryptedQueryString = SYMMETRIC_CRYPTO.encryptBase64(queryString);

		// 创建模拟请求
		MockHttpServletRequestBuilder mockRequest;
		if (StringUtils.hasLength(cryptoFilterProperties.getParameterName())) {
			mockRequest = MockMvcRequestBuilders.get(path).queryParam(cryptoFilterProperties.getParameterName(), encryptedQueryString);
		} else {
			mockRequest = MockMvcRequestBuilders.get(path + "?" + encryptedQueryString);
		}

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
		assertEquals("{\"name\":\"111啊啊啊222呀呀呀,哇哇哇\"}", content);
	}
}
