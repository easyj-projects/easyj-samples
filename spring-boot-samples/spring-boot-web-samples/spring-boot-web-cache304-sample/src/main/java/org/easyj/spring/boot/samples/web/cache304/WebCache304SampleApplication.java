package org.easyj.spring.boot.samples.web.cache304;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 使用 {@link org.easyj.web.cache304.annotation.Cache304} 的样例
 *
 * @author wangliang181230
 */
@SpringBootApplication
public class WebCache304SampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebCache304SampleApplication.class, args);
	}
}