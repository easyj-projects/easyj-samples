package icu.easyj.spring.boot.sample.environment.enhanced;

import java.lang.reflect.InvocationTargetException;
import javax.annotation.Resource;

import icu.easyj.config.GlobalConfigs;
import icu.easyj.core.util.ReflectionUtils;
import icu.easyj.core.util.StringUtils;
import icu.easyj.spring.boot.autoconfigure.configs.GlobalProperties;
import icu.easyj.spring.boot.sample.environment.enhanced.properties.DataSourceProperties;
import icu.easyj.spring.boot.sample.environment.enhanced.properties.RabbitMQProperties;
import icu.easyj.spring.boot.sample.environment.enhanced.properties.TestEasyjFunctionPropertySourceProperties;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.PropertySource;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * {@link EnvironmentEnhancedSampleApplication} 测试类
 *
 * @author wangliang181230
 */
@ActiveProfiles("test111")
@RunWith(SpringRunner.class)
@SpringBootTest
public class Test111EnvironmentEnhancedSampleApplicationTest {

	@Resource
	DataSourceProperties dataSourceProperties;
	@Resource
	RabbitMQProperties rabbitMQProperties;
	@Resource
	TestEasyjFunctionPropertySourceProperties testProperties;

	@Resource
	GlobalProperties globalProperties;

	@Resource
	ConfigurableEnvironment environment;


	/**
	 * 测试是否能够启动
	 */
	@Test
	public void testStartup() {
		// 配置的bean不为空
		assertNotNull(dataSourceProperties);
		assertNotNull(rabbitMQProperties);
		assertNotNull(testProperties);

		// 环境不为空
		assertNotNull(environment);
		// 校验环境中的配置源数量

		int size = 0;
		for (PropertySource propertySource : environment.getPropertySources()) {
			if (propertySource.getName().contains("[EasyJ]")) {
				size++;
			}
		}
		assertEquals(17, size);
	}

	/**
	 * 功能1：测试约定配置文件的功能
	 */
	@Test
	public void testEasyjAppointedEnvironmentPostProcessor() {
		// 校验约定的配置文件中的配置内容是否正常读取到
		assertEquals("DataSourceProperties(url=\"jdbc:mysql://192.168.1.1:3306\", username=\"root\", password=\"testpassword1\")",
				StringUtils.toString(dataSourceProperties));
		assertEquals("RabbitMQProperties(url=\"11.22.33.44:5672\", username=\"guest\", password=\"testpassword2\")",
				StringUtils.toString(rabbitMQProperties));
	}

	/**
	 * 功能2：测试EasyJ定义的全局配置
	 */
	@Test
	public void testGlobalConfigs() throws InvocationTargetException, NoSuchMethodException {
		// 判断全局配置是否已正常设置，并且内容正确
		String expected = "GlobalProperties(area=\"my-area\", areaName=\"我的区域\", project=\"my-project\", projectName=\"我的项目\", application=\"env-enhanced-sample\"," +
				" applicationName=\"环境增强功能示例\", env=\"test111\", envName=\"开发环境\", envType=null, runMode=null, inUnitTest=true, configs=null)";
		assertEquals(expected, StringUtils.toString(globalProperties));

		GlobalConfigs globalConfigs = (GlobalConfigs)ReflectionUtils.invokeStaticMethod(GlobalConfigs.class, "getInstance");
		expected = "GlobalConfigs(area=\"my-area\", areaName=\"我的区域\", project=\"my-project\", projectName=\"我的项目\", application=\"env-enhanced-sample\"" +
				", applicationName=\"环境增强功能示例\", env=\"test111\", envName=\"开发环境\", envType=EnvironmentType.TEST, runMode=RunMode.RELEASE, inUnitTest=true, configs={})";
		assertEquals(expected, StringUtils.toString(globalConfigs));
	}
}
