package icu.easyj.spring.boot.sample.web.cache304;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 使用 {@link icu.easyj.web.cache304.annotation.Cache304} 的样例
 *
 * @author wangliang181230
 *
 * <p>
 * 核心类，如下：
 * @see icu.easyj.web.cache304.annotation.Cache304
 * @see icu.easyj.web.cache304.Cache304Aspect
 * @see icu.easyj.web.cache304.Cache304Utils
 * @see icu.easyj.spring.boot.autoconfigure.web.cache304.EasyjWebCache304AutoConfiguration
 */
@SpringBootApplication
public class WebCache304SampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebCache304SampleApplication.class, args);
	}
}
