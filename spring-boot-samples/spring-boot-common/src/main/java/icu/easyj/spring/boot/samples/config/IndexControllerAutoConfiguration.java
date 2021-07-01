package icu.easyj.spring.boot.samples.config;

import icu.easyj.spring.boot.samples.controller.IndexController;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Lazy;

/**
 * @author wangliang181230
 */
@Lazy // 当前自动装配类，在项目启动过程中，无需实例化，所以添加了 @Lazy
@Configuration(proxyBeanMethods = false)
@Import(IndexController.class)
public class IndexControllerAutoConfiguration {
}
