package icu.easyj.spring.boot.sample.environment.enhanced;

import java.lang.reflect.InvocationTargetException;

import cn.hutool.core.lang.PatternPool;
import icu.easyj.config.GlobalConfigs;
import icu.easyj.core.util.PatternUtils;
import icu.easyj.core.util.ReflectionUtils;
import icu.easyj.core.util.StringUtils;
import icu.easyj.crypto.GlobalCrypto;
import icu.easyj.crypto.asymmetric.IAsymmetricCrypto;
import icu.easyj.crypto.symmetric.ISymmetricCrypto;
import icu.easyj.spring.boot.autoconfigure.global.configs.GlobalProperties;
import icu.easyj.spring.boot.sample.environment.enhanced.properties.DataSourceProperties;
import icu.easyj.spring.boot.sample.environment.enhanced.properties.RabbitMQProperties;
import icu.easyj.spring.boot.sample.environment.enhanced.properties.TestEasyjFunctionPropertySourceProperties;
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
	TestEasyjFunctionPropertySourceProperties testProperties;

	@Autowired
	GlobalProperties globalProperties;

	@Autowired
	ConfigurableEnvironment environment;

	/**
	 * 测试是否能够启动
	 */
	@Test
	void testStartup() {
		// 配置的bean不为空
		assertNotNull(dataSourceProperties);
		assertNotNull(rabbitMQProperties);
		assertNotNull(testProperties);

		// 环境不为空
		assertNotNull(environment);
		// 校验环境中的配置源数量
		assertEquals(27, environment.getPropertySources().size());
	}

	/**
	 * 功能1：测试约定配置文件的功能
	 */
	@Test
	void testEasyjAppointedEnvironmentPostProcessor() {
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
	void testGlobalConfigs() throws InvocationTargetException, NoSuchMethodException {
		// 判断全局配置是否已正常设置，并且内容正确
		String expected = "GlobalProperties(area=\"my-area\", areaName=\"我的区域\", project=\"my-project\", projectName=\"我的项目\", application=\"env-enhanced-sample\"," +
				" applicationName=\"环境增强功能示例\", env=\"dev\", envName=\"开发环境\", envType=null, runMode=null, inUnitTest=true, configs=null)";
		assertEquals(expected, StringUtils.toString(globalProperties));

		GlobalConfigs globalConfigs = (GlobalConfigs)ReflectionUtils.invokeStaticMethod(GlobalConfigs.class, "getInstance");
		expected = "GlobalConfigs(area=\"my-area\", areaName=\"我的区域\", project=\"my-project\", projectName=\"我的项目\", application=\"env-enhanced-sample\"" +
				", applicationName=\"环境增强功能示例\", env=\"dev\", envName=\"开发环境\", envType=EnvironmentType.DEV, runMode=RunMode.RELEASE, inUnitTest=true, configs={})";
		assertEquals(expected, StringUtils.toString(globalConfigs));
	}

	/**
	 * 功能3：测试函数式配置
	 */
	@Test
	void testEasyjFunctionPropertySource() {
		String cryptoDecrypt = testProperties.getCryptoDecrypt();

		String localIpPattern = testProperties.getLocalIpPattern();

		String random = testProperties.getRandom();
		String randomUuid32 = testProperties.getRandomUuid32();
		String randomUuid = testProperties.getRandomUuid();
		int randomPort = testProperties.getRandomPort();
		short randomShort = testProperties.getRandomShort();
		int randomInt = testProperties.getRandomInt();
		long randomLong = testProperties.getRandomLong();
		String randomChoose = testProperties.getRandomChoose();


		assertEquals("开发环境", cryptoDecrypt);

		System.out.println("localIpPattern = " + localIpPattern);
		assertTrue(PatternUtils.validate(PatternPool.IPV4, localIpPattern));

		assertEquals(32, random.length());
		assertEquals(32, randomUuid32.length());
		assertEquals(36, randomUuid.length());
		assertTrue(PatternUtils.validate(PatternPool.UUID_SIMPLE, random));
		assertTrue(PatternUtils.validate(PatternPool.UUID_SIMPLE, randomUuid32));
		assertTrue(PatternUtils.validate(PatternPool.UUID, randomUuid));
		assertTrue(randomPort >= 10000 && randomPort <= 20000);
		assertTrue(randomShort >= 1001 && randomShort <= 2000);
		assertTrue(randomInt >= 2001 && randomInt <= 3000);
		assertTrue(randomLong >= 3001 && randomLong <= 4000);
		assertTrue("1,2,3,4".contains(randomChoose));
	}

	/**
	 * 功能4：全局加密算法（含对称和非对称加密）
	 */
	@Test
	void testGlobalCrypto() {
		//region 全局非加密算法

		IAsymmetricCrypto asymmetricCrypto = GlobalCrypto.getAsymmetricCrypto();
		assertNotNull(asymmetricCrypto);

		String data1 = "@wangliang181230是一名架构师。";
		String encryptedData1 = asymmetricCrypto.encryptBase64(data1);
		assertEquals(data1, asymmetricCrypto.decryptBase64(encryptedData1));

		//endregion


		//region 全局加密算法

		ISymmetricCrypto symmetricCrypto = GlobalCrypto.getSymmetricCrypto();
		assertNotNull(symmetricCrypto);

		String data2 = "@wangliang181230是一名打工人。";
		String encryptedData2 = symmetricCrypto.encryptBase64(data2);
		assertEquals(data2, symmetricCrypto.decryptBase64(encryptedData2));

		//endregion
	}
}
