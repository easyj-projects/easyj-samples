package icu.easyj.spring.boot.sample.sdk.dwz.easyj;

import javax.annotation.Resource;

import icu.easyj.middleware.dwz.template.impls.http.HttpEasyjMiddleWareDwzTemplateConfig;
import icu.easyj.middleware.dwz.template.impls.http.HttpEasyjMiddleWareDwzTemplateImpl;
import icu.easyj.sdk.dwz.IDwzTemplate;
import icu.easyj.spring.boot.sample.sdk.dwz.DwzTemplateSampleApplication;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * {@link DwzTemplateSampleApplication} 测试类
 *
 * @author wangliang181230
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("easyj")
public class EasyjMiddleWareDwzTemplateSampleApplicationTest {

	@Resource
	IDwzTemplate template;

	@Resource
	HttpEasyjMiddleWareDwzTemplateConfig httpEasyjMiddleWareDwzTemplateConfig;

	/**
	 * 测试是否能够启动
	 */
	@Test
	public void testStartup() {
		Assertions.assertEquals(HttpEasyjMiddleWareDwzTemplateImpl.class, template.getClass());

		Assertions.assertEquals("http://localhost:3001/api/v1/create-short-url", httpEasyjMiddleWareDwzTemplateConfig.getServiceUrl());
	}
}
