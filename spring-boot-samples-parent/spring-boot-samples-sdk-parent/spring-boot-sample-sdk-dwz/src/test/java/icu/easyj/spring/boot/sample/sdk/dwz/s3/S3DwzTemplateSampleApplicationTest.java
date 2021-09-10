package icu.easyj.spring.boot.sample.sdk.dwz.s3;

import javax.annotation.Resource;

import icu.easyj.sdk.dwz.IDwzTemplate;
import icu.easyj.sdk.s3.dwz.S3DwzConfig;
import icu.easyj.sdk.s3.dwz.S3DwzTemplateImpl;
import icu.easyj.spring.boot.sample.sdk.dwz.DwzTemplateSampleApplication;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

/**
 * {@link DwzTemplateSampleApplication} 测试类
 *
 * @author wangliang181230
 */
@SpringBootTest
@ActiveProfiles("s3")
class S3DwzTemplateSampleApplicationTest {

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
		Assertions.assertEquals(S3DwzTemplateImpl.class, template.getClass());

		// 校验是否复制了通用配置
		Assertions.assertEquals("https://s-3.cn/api/v2/shorten/create", config.getServiceUrl());
		Assertions.assertEquals("108104", config.getClientId());
		Assertions.assertEquals("d49ca510520b0f02004e03ddc2de7c49", config.getClientSecret());
	}
}
