package org.easyj.samples.web.cache304.config;

import org.easyj.web.cache304.Cache304Aspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Cache304配置
 *
 * @author wangliang181230
 */
@Configuration
public class EasyjCache304Config {

	@Bean
	public Cache304Aspect cache304Aspect() {
		return new Cache304Aspect();
	}
}
