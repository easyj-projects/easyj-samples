package icu.easyj.spring.boot.sample.sdk.ocr;

import javax.annotation.Resource;

import icu.easyj.core.util.StringUtils;
import icu.easyj.sdk.ocr.IOcrTemplate;
import icu.easyj.sdk.tencent.cloud.ocr.idcardocr.TencentCloudIdCardOcrConfig;
import icu.easyj.spring.boot.autoconfigure.sdk.tencent.cloud.TencentCloudCommonProperties;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * {@link OcrTemplateSampleApplication} 测试类
 *
 * @author wangliang181230
 */
@SpringBootTest
class OcrTemplateSampleApplicationTest {

	@Resource
	IOcrTemplate template;

	@Resource
	TencentCloudCommonProperties commonProperties;

	@Resource
	TencentCloudIdCardOcrConfig idCardOcrConfig;

	/**
	 * 测试是否能够启动
	 */
	@Test
	void testStartup() {
		Assertions.assertNotNull(template);

		// 校验是否复制了通用配置
		String commonPropertiesStr = StringUtils.toString(commonProperties);
		String idCardOcrConfigStr = StringUtils.toString(idCardOcrConfig);
		Assertions.assertEquals(commonPropertiesStr.substring(commonPropertiesStr.indexOf("(")),
				idCardOcrConfigStr.substring(idCardOcrConfigStr.indexOf("(")));
	}
}
