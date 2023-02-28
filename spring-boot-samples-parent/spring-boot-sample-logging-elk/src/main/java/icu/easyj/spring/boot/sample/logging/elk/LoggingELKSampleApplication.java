package icu.easyj.spring.boot.sample.logging.elk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * 快速集成分布式日志ELK的示例项目
 */
@SpringBootApplication
public class LoggingELKSampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoggingELKSampleApplication.class, args);
	}

}
