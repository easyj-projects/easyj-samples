package icu.easyj.spring.boot.sample.environment.enhanced;

import icu.easyj.config.AppConfigs;
import icu.easyj.config.EnvironmentConfigs;
import icu.easyj.core.util.ReflectionUtils;
import icu.easyj.core.util.StringUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 使用 EasyJ的Excel相关功能 的样例
 *
 * @author wangliang181230
 *
 * <p>
 * // 功能1：约定`global、area、project、default、env`配置文件存放目录的功能。核心类，如下：
 * @see icu.easyj.spring.boot.env.enhanced.EasyjAppointedEnvironmentPostProcessor
 * <p>
 * // 功能2：自动加载EasyJ定义的项目及应用配置信息，以及环境配置信息。核心类，如下：
 * @see icu.easyj.config.AppConfigs
 * @see icu.easyj.spring.boot.autoconfigure.configs.AppProperties
 * @see icu.easyj.config.EnvironmentConfigs
 * @see icu.easyj.spring.boot.autoconfigure.configs.EnvironmentProperties
 * @see icu.easyj.spring.boot.autoconfigure.configs.EasyjConfigsAutoConfiguration
 * <p>
 * // 功能3：函数式配置，目前提供了三种函数。核心类，如下：
 * @see icu.easyj.spring.boot.env.enhanced.EasyjFunctionPropertySource 函数式配置源
 * @see icu.easyj.spring.boot.env.enhanced.EasyjFunctionPropertySourceEnvironmentPostProcessor 加载函数式配置源的环境处理器
 * @see icu.easyj.spring.boot.env.enhanced.util.CryptoPropertyUtils // ${easyj.crypto.xxx} 函数的工具类
 * @see icu.easyj.spring.boot.env.enhanced.util.NetPropertyUtils // ${easyj.net.xxx} 函数的工具类
 * @see icu.easyj.spring.boot.env.enhanced.util.RandomPropertyUtils // ${easyj.random.xxx} 函数的工具类
 * <p>
 * // 功能4：全局加密算法（含对称和非对称加密）
 * @see icu.easyj.crypto.GlobalCrypto // 全局加密算法生成器
 * @see icu.easyj.spring.boot.env.enhanced.EasyjAppointedEnvironmentPostProcessor // 负责加载配置并生成算法实例
 */
@SpringBootApplication
public class EnvironmentEnhancedSampleApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(EnvironmentEnhancedSampleApplication.class, args);
		System.out.println("项目及应用配置信息：\r\n\t" + StringUtils.toString(ReflectionUtils.invokeStaticMethod(AppConfigs.class, "getInstance")));
		System.out.println("环境配置信息：\r\n\t" + StringUtils.toString(ReflectionUtils.invokeStaticMethod(EnvironmentConfigs.class, "getInstance")));

		System.out.println();
		System.out.println();
		System.out.println("注：为了方便观察示例打印的日志，已将日志打印级别设置为`warn`。");
	}
}
