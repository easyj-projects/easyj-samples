package icu.easyj.spring.boot.sample.web.cache304;

import javax.annotation.Resource;

import icu.easyj.spring.boot.sample.web.cache304.restcontroller.TestCache304Controller;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * {@link WebCache304SampleApplication} 测试类
 *
 * @author wangliang181230
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class WebCache304SampleApplicationTest {

	@Resource
	TestCache304Controller testCache304Controller;

	/**
	 * 测试是否能够启动
	 */
	@Test
	public void testStartup() {
		Assertions.assertNotNull(testCache304Controller);
	}
}
