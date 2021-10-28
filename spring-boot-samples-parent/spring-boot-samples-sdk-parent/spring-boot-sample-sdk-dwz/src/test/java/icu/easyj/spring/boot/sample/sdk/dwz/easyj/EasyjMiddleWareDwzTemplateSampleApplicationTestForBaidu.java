package icu.easyj.spring.boot.sample.sdk.dwz.easyj;

import javax.annotation.Resource;

import icu.easyj.sdk.dwz.IDwzTemplate;
import icu.easyj.sdk.middleware.dwz.impls.http.HttpEasyjMiddleWareDwzTemplateConfig;
import icu.easyj.sdk.middleware.dwz.impls.http.HttpEasyjMiddleWareDwzTemplateImpl;
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
@ActiveProfiles("easyj")
class EasyjMiddleWareDwzTemplateSampleApplicationTestForBaidu {

	@Resource
	IDwzTemplate template;

	@Resource
	HttpEasyjMiddleWareDwzTemplateConfig httpEasyjMiddleWareDwzTemplateConfig;

	/**
	 * 测试是否能够启动
	 */
	@Test
	void testStartup() {
		Assertions.assertEquals(HttpEasyjMiddleWareDwzTemplateImpl.class, template.getClass());

		Assertions.assertEquals("http://localhost:3001/api/v1/create-short-url", httpEasyjMiddleWareDwzTemplateConfig.getServiceUrl());
	}
}
