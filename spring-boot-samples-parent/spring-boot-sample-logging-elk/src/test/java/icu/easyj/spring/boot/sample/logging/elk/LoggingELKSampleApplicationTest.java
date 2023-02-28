package icu.easyj.spring.boot.sample.logging.elk;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Test {@link LoggingELKSampleApplication}
 */
@SpringBootTest
public class LoggingELKSampleApplicationTest {

	private static final Logger LOGGER = LoggerFactory.getLogger(LoggingELKSampleApplicationTest.class);


	@Test
	void test() {
		MDC.put("id", UUID.randomUUID().toString());
		LOGGER.info("test");
		MDC.remove("id");
	}

}
