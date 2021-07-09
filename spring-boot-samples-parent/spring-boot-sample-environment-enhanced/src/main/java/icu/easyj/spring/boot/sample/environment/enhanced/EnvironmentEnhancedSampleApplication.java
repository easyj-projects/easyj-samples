package icu.easyj.spring.boot.sample.environment.enhanced;

import icu.easyj.config.GlobalConfigs;
import icu.easyj.core.util.ReflectionUtils;
import icu.easyj.core.util.StringUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 使用 EasyJ的Excel相关功能 的样例
 *
 * @author wangliang181230
 * <p>
 * // 功能1：约定default、global、area、project、env配置位置的功能
 * @see icu.easyj.spring.boot.env.enhanced.EasyjAppointedEnvironmentPostProcessor
 * <p>
 * // 功能2：自动加载EasyJ定义的全局配置信息
 * @see icu.easyj.config.GlobalConfigs
 * <p>
 * // 功能3：待添加......
 * @see xxx
 */
@SpringBootApplication
public class EnvironmentEnhancedSampleApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(EnvironmentEnhancedSampleApplication.class, args);
		System.out.println("全局配置信息：\r\n\t" + StringUtils.toString(ReflectionUtils.invokeStaticMethod(GlobalConfigs.class, "getInstance")));

		System.out.println();
		System.out.println();
		System.out.println("注：为了方便观察示例打印的日志，已将日志打印级别设置为`warn`。");
	}
}
