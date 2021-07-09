package icu.easyj.spring.boot.samples.env.enhanced.properties;

import icu.easyj.core.util.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 测试用的配置类
 *
 * @author wangliang181230
 */
@Configuration(proxyBeanMethods = false)
@ConfigurationProperties("rabbitmq")
public class RabbitMQProperties implements InitializingBean {

	private String url = "127.0.0.1:5672";
	private String username = "guest";
	private String password = "guest";

	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("从约定的配置文件中读取到：\r\n\t" + StringUtils.toString(this));
	}


	//region Getter、Setter

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	//endregion
}
