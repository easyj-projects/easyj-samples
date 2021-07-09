package icu.easyj.spring.boot.sample.web.cache304;

import icu.easyj.spring.boot.sample.web.cache304.restcontroller.TestCache304Controller;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author wangliang181230
 */
@SpringBootTest
public class WebCache304SampleApplicationTest {

	@Autowired
	TestCache304Controller testCache304Controller;

	/**
	 * 测试是否能够启动
	 */
	@Test
	void testStartup() {
		Assertions.assertNotNull(testCache304Controller);
	}
}
