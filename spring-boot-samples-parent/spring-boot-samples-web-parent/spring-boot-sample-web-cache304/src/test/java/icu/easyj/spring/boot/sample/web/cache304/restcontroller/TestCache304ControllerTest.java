package icu.easyj.spring.boot.sample.web.cache304.restcontroller;

import java.util.Date;

import icu.easyj.core.enums.DateFormatType;
import icu.easyj.core.util.DateUtils;
import icu.easyj.spring.boot.test.BaseSpringBootMockMvcTest;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.util.NestedServletException;

import static icu.easyj.spring.boot.sample.web.cache304.restcontroller.TestCache304Controller.CACHE_SECONDS1;
import static icu.easyj.spring.boot.sample.web.cache304.restcontroller.TestCache304Controller.CACHE_SECONDS2;

/**
 * {@link TestCache304Controller} 测试类
 *
 * @author wangliang181230
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestCache304ControllerTest extends BaseSpringBootMockMvcTest {

	/**
	 * @throws Exception 异常
	 * @see TestCache304Controller#useCache304AndUseMaxAgeAndUseCacheIfException(Boolean)
	 */
	@Test
	public void testUseCache304AndUseMaxAgeAndUseCacheIfException() throws Exception {
		String path = "/test/cache-304/true";

		// 无缓存时，发送请求
		super.mockGet(path)
				.send()
				.status().isOk()
				.content().is("useCache304AndUseMaxAgeAndUseCacheIfException").end()
				.header()
				.contains(HttpHeaders.LAST_MODIFIED, HttpHeaders.EXPIRES, HttpHeaders.CACHE_CONTROL)
				.is(HttpHeaders.CACHE_CONTROL, "max-age=4"); // 多缓存一秒

		// 有缓存，且未过期时，发送请求
		Date now = new Date();
		super.mockGet(path)
				.header(HttpHeaders.IF_MODIFIED_SINCE, DateUtils.format(DateFormatType.SS, now))
				.send()
				.status().is(HttpStatus.NOT_MODIFIED); // 304

		// 有缓存，但已过期时，发送请求，故意抛出异常
		now = new Date(now.getTime() - CACHE_SECONDS1 * 1000);
		super.mockGet(path)
				.queryParam("needThrow", "true") // 故意抛出异常用于测试`useCacheIfException = true`时的功能
				.header(HttpHeaders.IF_MODIFIED_SINCE, DateUtils.format(DateFormatType.SS, now))
				.send()
				.status().is(HttpStatus.NOT_MODIFIED); // 304：因为开启了出现异常允许客户端继续使用缓存的功能，所以该请求响应了304

		// 有缓存，但已过期时，发送请求
		now = new Date(now.getTime() - CACHE_SECONDS1 * 1000);
		super.mockGet(path)
				.header(HttpHeaders.IF_MODIFIED_SINCE, DateUtils.format(DateFormatType.SS, now))
				.send()
				.status().isOk()
				.content().is("useCache304AndUseMaxAgeAndUseCacheIfException").end()
				.header()
				.contains(HttpHeaders.LAST_MODIFIED, HttpHeaders.EXPIRES, HttpHeaders.CACHE_CONTROL)
				.is(HttpHeaders.CACHE_CONTROL, "max-age=4"); // 多缓存一秒
	}

	/**
	 * @throws Exception 异常
	 * @see TestCache304Controller#useCache304NoMaxAge(Boolean)
	 */
	@Test
	public void testUseCache304NoMaxAge() throws Exception {
		String path = "/test/cache-304/false";

		// 无缓存时，发送请求
		super.mockGet(path)
				.send()
				.status().isOk()
				.content().is("useCache304NoMaxAge").end()
				.header()
				.contains(HttpHeaders.LAST_MODIFIED)
				.notContains(HttpHeaders.EXPIRES, HttpHeaders.CACHE_CONTROL);

		// 有缓存，且未过期时，发送请求
		Date now = new Date();
		super.mockGet(path)
				.header(HttpHeaders.IF_MODIFIED_SINCE, DateUtils.format(DateFormatType.SS, now))
				.send()
				.status().is(HttpStatus.NOT_MODIFIED); // 304

		// 有缓存，但已过期时，发送请求，故意抛出异常，看看异常是否正常抛出
		final Date nowFinal = new Date(now.getTime() - CACHE_SECONDS2 * 1000);
		Assertions.assertThrows(NestedServletException.class, () -> {
			super.mockGet(path)
					.queryParam("needThrow", "true") // 故意抛出异常，用于测试`useCacheIfException = false`时，异常能否正常抛出
					.header(HttpHeaders.IF_MODIFIED_SINCE, DateUtils.format(DateFormatType.SS, nowFinal))
					.send();
		});

		// 有缓存，但已过期时，发送请求
		now = new Date(now.getTime() - CACHE_SECONDS2 * 1000);
		super.mockGet(path)
				.header(HttpHeaders.IF_MODIFIED_SINCE, DateUtils.format(DateFormatType.SS, now))
				.send()
				.status().isOk()
				.content().is("useCache304NoMaxAge").end()
				.header()
				.contains(HttpHeaders.LAST_MODIFIED)
				.notContains(HttpHeaders.EXPIRES, HttpHeaders.CACHE_CONTROL);
	}

	/**
	 * @throws Exception 异常
	 * @see TestCache304Controller#noCache304()
	 */
	@Test
	public void testNoCache304() throws Exception {
		String path = "/test/no-cache-304";

		// 无缓存时，发送请求
		super.mockGet(path)
				.send()
				.status().isOk()
				.content().is("noCache304").end()
				.header()
				.notContains(HttpHeaders.LAST_MODIFIED, HttpHeaders.EXPIRES, HttpHeaders.CACHE_CONTROL);

		// 有缓存，发送请求（实际上是无意义的头信息）
		Date now = new Date();
		super.mockGet(path)
				.header(HttpHeaders.IF_MODIFIED_SINCE, DateUtils.format(DateFormatType.SS, now))
				.send()
				.status().isOk()
				.content().is("noCache304").end()
				.header()
				.notContains(HttpHeaders.LAST_MODIFIED, HttpHeaders.EXPIRES, HttpHeaders.CACHE_CONTROL);
	}
}
