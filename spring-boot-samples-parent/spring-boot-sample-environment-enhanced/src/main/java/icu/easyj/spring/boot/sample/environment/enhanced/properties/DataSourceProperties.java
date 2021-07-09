package icu.easyj.spring.boot.sample.environment.enhanced.properties;

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
@ConfigurationProperties("datasource")
public class DataSourceProperties implements InitializingBean {

	private String url = "jdbc:mysql://127.0.0.1:3306";
	private String username = "root";
	private String password = "root";

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
