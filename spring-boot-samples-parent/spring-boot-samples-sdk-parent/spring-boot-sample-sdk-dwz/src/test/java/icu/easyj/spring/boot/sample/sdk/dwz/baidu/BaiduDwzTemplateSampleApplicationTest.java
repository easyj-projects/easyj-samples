package icu.easyj.spring.boot.sample.sdk.dwz.baidu;

import javax.annotation.Resource;

import icu.easyj.sdk.baidu.cloud.dwz.BaiduDwzConfig;
import icu.easyj.sdk.baidu.cloud.dwz.BaiduDwzTemplateImpl;
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
@ActiveProfiles("baidu")
public class BaiduDwzTemplateSampleApplicationTest {

	@Resource
	IDwzTemplate template;

	@Resource
	BaiduDwzConfig config;

	/**
	 * 测试是否能够启动
	 */
	@Test
	public void testStartup() {
		Assertions.assertNotNull(template);
		Assertions.assertNotNull(config);
		Assertions.assertEquals(BaiduDwzTemplateImpl.class, template.getClass());

		// 校验是否复制了通用配置
		Assertions.assertEquals("https://dwz.cn/api/v3/short-urls", config.getServiceUrl());
		Assertions.assertEquals("14b303c38c494cb0bfe36fd80c8b8a69", config.getToken());
		Assertions.assertEquals("long-term", config.getTermOfValidity());
		Assertions.assertEquals("zh", config.getResponseLanguage());
	}
}
