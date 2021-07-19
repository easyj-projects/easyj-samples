package icu.easyj.spring.boot.sample.environment.enhanced;

import java.lang.reflect.InvocationTargetException;

import icu.easyj.config.GlobalConfigs;
import icu.easyj.core.util.ReflectionUtils;
import icu.easyj.core.util.StringUtils;
import icu.easyj.crypto.GlobalCrypto;
import icu.easyj.crypto.ICrypto;
import icu.easyj.crypto.asymmetric.IAsymmetricCrypto;
import icu.easyj.spring.boot.autoconfigure.global.configs.GlobalProperties;
import icu.easyj.spring.boot.sample.environment.enhanced.properties.DataSourceProperties;
import icu.easyj.spring.boot.sample.environment.enhanced.properties.RabbitMQProperties;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.ConfigurableEnvironment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * {@link EnvironmentEnhancedSampleApplication} 测试类
 *
 * @author wangliang181230
 */
@SpringBootTest
class EnvironmentEnhancedSampleApplicationTest {

	@Autowired
	DataSourceProperties dataSourceProperties;
	@Autowired
	RabbitMQProperties rabbitMQProperties;
	@Autowired
	GlobalProperties globalProperties;

	@Autowired
	ConfigurableEnvironment environment;

	/**
	 * 测试是否能够启动
	 */
	@Test
	void testStartup() {
		assertNotNull(dataSourceProperties);
		assertNotNull(rabbitMQProperties);
		assertNotNull(environment);

		// 全局加密算法
		ICrypto crypto = GlobalCrypto.getCrypto();
		assertTrue(crypto instanceof IAsymmetricCrypto);

		String data = "123456abcdef";
		String encryptedData = crypto.encryptBase64(data);
		assertEquals(data, crypto.decryptBase64(encryptedData));
	}

	/**
	 * 测试约定配置文件的功能
	 */
	@Test
	void testEasyjAppointedEnvironmentPostProcessor() {
		// 校验约定的配置文件中的配置内容是否正常读取到
		assertEquals("DataSourceProperties(url=\"jdbc:mysql://192.168.1.1:3306\", username=\"root\", password=\"testpassword1\")",
				StringUtils.toString(dataSourceProperties));
		assertEquals("RabbitMQProperties(url=\"11.22.33.44:5672\", username=\"guest\", password=\"testpassword2\")",
				StringUtils.toString(rabbitMQProperties));

		// 校验环境中的配置源数量
		assertEquals(25, environment.getPropertySources().size());
	}

	/**
	 * 测试EasyJ定义的全局配置
	 */
	@Test
	void testGlobalConfigs() throws InvocationTargetException, NoSuchMethodException {
		// 判断全局配置是否已正常设置，并且内容正确
		String expected = "GlobalProperties(area=\"my-area\", areaName=\"我的区域\", project=\"my-project\", projectName=\"我的项目\", application=\"env-enhanced-sample\"," +
				" applicationName=\"环境增强功能示例\", env=\"dev\", envName=\"开发环境\", envType=null, runMode=null, configs=null)";
		assertEquals(expected, StringUtils.toString(globalProperties));

		GlobalConfigs globalConfigs = (GlobalConfigs)ReflectionUtils.invokeStaticMethod(GlobalConfigs.class, "getInstance");
		expected = "GlobalConfigs(area=\"my-area\", areaName=\"我的区域\", project=\"my-project\", projectName=\"我的项目\", application=\"env-enhanced-sample\"" +
				", applicationName=\"环境增强功能示例\", env=\"dev\", envName=\"开发环境\", envType=EnvironmentType.DEV, runMode=RunMode.RELEASE, configs={})";
		assertEquals(expected, StringUtils.toString(globalConfigs));
	}
}
