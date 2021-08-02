package icu.easyj.spring.boot.sample.web.cache304.restcontroller;

import java.util.Date;

import icu.easyj.core.enums.DateFormatType;
import icu.easyj.core.util.DateUtils;
import icu.easyj.spring.boot.test.BaseSpringBootMockMvcTest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

import static icu.easyj.spring.boot.sample.web.cache304.restcontroller.TestCache304Controller.CACHE_SECONDS1;
import static icu.easyj.spring.boot.sample.web.cache304.restcontroller.TestCache304Controller.CACHE_SECONDS2;

/**
 * {@link TestCache304Controller} 测试类
 *
 * @author wangliang181230
 */
@SpringBootTest
class TestCache304ControllerTest extends BaseSpringBootMockMvcTest {

	@Test
	void testUseCache304AndUseMaxAge() throws Exception {
		String path = "/test/cache-304/true";

		// 无缓存时，发送请求
		super.mockGet(path)
				.send()
				.status().isOk()
				.content().is("useCache304AndUseMaxAge").end()
				.header()
				.contains(HttpHeaders.LAST_MODIFIED, HttpHeaders.EXPIRES, HttpHeaders.CACHE_CONTROL)
				.is(HttpHeaders.CACHE_CONTROL, "max-age=4"); // 多缓存一秒

		// 有缓存，且未过期时，发送请求
		Date now = new Date();
		super.mockGet(path)
				.header(HttpHeaders.IF_MODIFIED_SINCE, DateUtils.format(DateFormatType.SS, now))
				.send()
				.status().is(HttpStatus.NOT_MODIFIED); // 304

		// 有缓存，但已过期时，发送请求
		now = new Date(now.getTime() - CACHE_SECONDS1 * 1000);
		super.mockGet(path)
				.header(HttpHeaders.IF_MODIFIED_SINCE, DateUtils.format(DateFormatType.SS, now))
				.send()
				.status().isOk()
				.content().is("useCache304AndUseMaxAge").end()
				.header()
				.contains(HttpHeaders.LAST_MODIFIED, HttpHeaders.EXPIRES, HttpHeaders.CACHE_CONTROL)
				.is(HttpHeaders.CACHE_CONTROL, "max-age=4"); // 多缓存一秒
	}

	@Test
	void testUseCache304NoMaxAge() throws Exception {
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

	@Test
	void testNoCache304() throws Exception {
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
