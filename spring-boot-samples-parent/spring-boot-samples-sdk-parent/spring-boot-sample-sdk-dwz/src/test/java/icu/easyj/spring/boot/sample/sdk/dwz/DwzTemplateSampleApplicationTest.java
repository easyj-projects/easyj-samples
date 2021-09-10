package icu.easyj.spring.boot.sample.sdk.dwz;

import javax.annotation.Resource;

import icu.easyj.sdk.dwz.IDwzTemplate;
import icu.easyj.sdk.s3.dwz.config.S3DwzConfig;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * {@link DwzTemplateSampleApplication} 测试类
 *
 * @author wangliang181230
 */
@SpringBootTest
class DwzTemplateSampleApplicationTest {

	@Resource
	IDwzTemplate template;

	@Resource
	S3DwzConfig config;

	/**
	 * 测试是否能够启动
	 */
	@Test
	void testStartup() {
		Assertions.assertNotNull(template);
		Assertions.assertNotNull(config);

		// 校验是否复制了通用配置
		Assertions.assertEquals("https://s-3.cn/api/v2/shorten/create", config.getServiceUrl());
		Assertions.assertEquals("108104", config.getClientId());
		Assertions.assertEquals("d49ca510520b0f02004e03ddc2de7c49", config.getClientSecret());
	}
}
