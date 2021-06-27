package org.easyj.samples.web.cache304.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger配置
 *
 * @author wangliang181230
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {

	/**
	 * 扫描包路径
	 */
	private static final String API_PACKAGE = "org.easyj.samples.web.cache304.restcontroller";

	@Bean
	public Docket createRestApi1() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("测试接口")
				.apiInfo(apiInfo())
				.select()
				.apis(RequestHandlerSelectors.basePackage(API_PACKAGE))
				.paths(PathSelectors.any())
				.build();
	}

	private ApiInfo apiInfo() {
		Contact contact = new Contact("EasyJ开源项目组", "https://gitee.com/easyj-projects", "841369634@qq.com");
		return new ApiInfoBuilder()
				.title("测试接口")
				.description("")
				.contact(contact)
				.build();
	}
}
