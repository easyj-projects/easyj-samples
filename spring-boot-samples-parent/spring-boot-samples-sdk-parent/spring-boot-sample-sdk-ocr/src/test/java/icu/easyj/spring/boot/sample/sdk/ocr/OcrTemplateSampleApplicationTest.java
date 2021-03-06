package icu.easyj.spring.boot.sample.sdk.ocr;

import javax.annotation.Resource;

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
public class OcrTemplateSampleApplicationTest {

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
	public void testStartup() {
		Assertions.assertNotNull(template);

		// 校验是否复制了通用配置
		Assertions.assertEquals(idCardOcrConfig.getSecretId(), commonProperties.getSecretId());
		Assertions.assertEquals(idCardOcrConfig.getSecretKey(), commonProperties.getSecretKey());
		Assertions.assertEquals(idCardOcrConfig.getRegion(), commonProperties.getRegion());
		Assertions.assertEquals(idCardOcrConfig.getConnTimeout(), commonProperties.getConnTimeout());
		Assertions.assertEquals(idCardOcrConfig.getReadTimeout(), commonProperties.getReadTimeout());
		Assertions.assertEquals(idCardOcrConfig.getWriteTimeout(), commonProperties.getWriteTimeout());
		Assertions.assertEquals(idCardOcrConfig.getLanguage(), commonProperties.getLanguage());
		Assertions.assertEquals(idCardOcrConfig.getDebug(), commonProperties.getDebug());
	}
}
