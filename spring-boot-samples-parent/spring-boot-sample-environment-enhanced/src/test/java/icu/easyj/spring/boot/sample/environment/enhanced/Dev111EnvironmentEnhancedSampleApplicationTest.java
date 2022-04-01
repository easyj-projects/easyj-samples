package icu.easyj.spring.boot.sample.environment.enhanced;

import java.lang.reflect.InvocationTargetException;
import javax.annotation.Resource;

import cn.hutool.core.lang.PatternPool;
import icu.easyj.config.AppConfigs;
import icu.easyj.config.EnvironmentConfigs;
import icu.easyj.core.util.PatternUtils;
import icu.easyj.core.util.ReflectionUtils;
import icu.easyj.core.util.StringUtils;
import icu.easyj.crypto.GlobalCrypto;
import icu.easyj.crypto.asymmetric.IAsymmetricCrypto;
import icu.easyj.crypto.symmetric.ISymmetricCrypto;
import icu.easyj.spring.boot.autoconfigure.configs.AppProperties;
import icu.easyj.spring.boot.autoconfigure.configs.EnvironmentProperties;
import icu.easyj.spring.boot.sample.environment.enhanced.properties.DataSourceProperties;
import icu.easyj.spring.boot.sample.environment.enhanced.properties.RabbitMQProperties;
import icu.easyj.spring.boot.sample.environment.enhanced.properties.TestEasyjFunctionPropertySourceProperties;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.PropertySource;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * {@link EnvironmentEnhancedSampleApplication} 测试类
 *
 * @author wangliang181230
 */
@ActiveProfiles("dev111")
@SpringBootTest
public class Dev111EnvironmentEnhancedSampleApplicationTest {

	@Resource
	DataSourceProperties dataSourceProperties;
	@Resource
	RabbitMQProperties rabbitMQProperties;
	@Resource
	TestEasyjFunctionPropertySourceProperties testProperties;

	@Resource
	AppProperties appProperties;
	@Resource
	EnvironmentProperties environmentProperties;

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

		// 判断：EnvironmentConfigs.isInUnitTest() == true
		assertTrue(EnvironmentConfigs.isInUnitTest());

		// 校验环境中的配置源数量
		int size = 0;
		for (PropertySource<?> propertySource : environment.getPropertySources()) {
			if (propertySource.getName().contains("[EasyJ]")) {
				size++;
			}
		}
		assertEquals(20, size);
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
	 * 功能2.1：测试EasyJ定义的项目及应用配置
	 */
	@Test
	public void testAppConfigs() throws InvocationTargetException, NoSuchMethodException {
		// 判断全局配置是否已正常设置，并且内容正确
		String expected = "AppProperties(area=\"my-area\", areaName=\"我的区域\", project=\"my-project\", projectName=\"我的项目\", application=\"env-enhanced-sample\", applicationName=\"环境增强功能示例\")";
		assertEquals(expected, StringUtils.toString(appProperties));

		AppConfigs appConfigs = (AppConfigs)ReflectionUtils.invokeStaticMethod(AppConfigs.class, "getInstance");
		expected = "AppConfigs(area=\"my-area\", areaName=\"我的区域\", project=\"my-project\", projectName=\"我的项目\", application=\"env-enhanced-sample\", applicationName=\"环境增强功能示例\")";
		assertEquals(expected, StringUtils.toString(appConfigs));
	}

	/**
	 * 功能2.2：测试EasyJ定义的环境配置
	 * <p>
	 * inUnitTest 在 application-dev111.yml 中配置成了false，为了校验自定义配置优先级是否比默认配置优先级要高
	 */
	@Test
	public void testEnvConfigs() throws InvocationTargetException, NoSuchMethodException {
		// 判断全局配置是否已正常设置，并且内容正确
		String expected = "EnvironmentProperties(env=\"dev111\", envName=\"开发环境\", envType=null, runMode=null)";
		assertEquals(expected, StringUtils.toString(environmentProperties));

		EnvironmentConfigs globalConfigs = (EnvironmentConfigs)ReflectionUtils.invokeStaticMethod(EnvironmentConfigs.class, "getInstance");
		expected = "EnvironmentConfigs(env=\"dev111\", envName=\"开发环境\", envType=EnvironmentType.DEV, runMode=RunMode.RELEASE, inUnitTest=true)";
		assertEquals(expected, StringUtils.toString(globalConfigs));
	}

	/**
	 * 功能3：测试函数式配置
	 */
	@Test
	public void testEasyjFunctionPropertySource() {
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

		String timeNow1 = testProperties.getTimeNow1();
		String timeNow2 = testProperties.getTimeNow2();


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
		assertTrue(PatternUtils.validate("^\\d{4}-\\d{2}-\\d{2}\\s\\d{2}\\:\\d{2}\\:\\d{2}\\.\\d{3}$", timeNow1));
		assertTrue(PatternUtils.validate("^\\d{4}-\\d{2}-\\d{2}$", timeNow2));
	}

	/**
	 * 功能4：全局加密算法（含对称和非对称加密）
	 */
	@Test
	public void testGlobalCrypto() {
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
