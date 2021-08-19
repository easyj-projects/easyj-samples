package icu.easyj.spring.boot.sample.sdk.ocr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 使用 {@link icu.easyj.sdk.ocr.IOcrTemplate} 的样例
 *
 * @author wangliang181230
 *
 * <p>
 * 核心类，如下：
 * @see icu.easyj.sdk.ocr.IOcrTemplate
 * @see icu.easyj.sdk.ocr.idcardocr.IIdCardOcrTemplate
 */
@SpringBootApplication
public class OcrTemplateSampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(OcrTemplateSampleApplication.class, args);
	}
}
