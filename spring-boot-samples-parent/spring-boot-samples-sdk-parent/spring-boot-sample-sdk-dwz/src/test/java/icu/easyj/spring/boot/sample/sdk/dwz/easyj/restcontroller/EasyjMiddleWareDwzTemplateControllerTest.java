package icu.easyj.spring.boot.sample.sdk.dwz.easyj.restcontroller;

import java.nio.charset.StandardCharsets;

import icu.easyj.sdk.dwz.DwzRequest;
import icu.easyj.sdk.dwz.DwzResponse;
import icu.easyj.spring.boot.sample.sdk.dwz.restcontroller.DwzTemplateController;
import icu.easyj.spring.boot.test.BaseSpringBootMockMvcTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;

/**
 * 测试 {@link DwzTemplateController} 测试类
 *
 * @author wangliang181230
 */
@SpringBootTest
@ActiveProfiles("easyj")
@Disabled("需要先手动开启 http://github.com/easyj-projects/easyj 中的dwz-server-application")
public class EasyjMiddleWareDwzTemplateControllerTest extends BaseSpringBootMockMvcTest {

	/**
	 * 测试：长链接转换为短链接
	 *
	 * @throws Exception 异常
	 * @see DwzTemplateController#createShortUrl(String)
	 * @see icu.easyj.sdk.dwz.IDwzTemplate#createShortUrl(DwzRequest)
	 */
	@Test
	public void testCreateShortUrl() throws Exception {
		String path = "/test/dwz/create";

		super.mockGet(path)
				.queryParam("longUrl", "https://www.baidu.com")
				.send()
				.status().isOk()
				.contentType().is(MediaType.APPLICATION_JSON)
				.characterEncoding().is(StandardCharsets.UTF_8)
				.content(DwzResponse.class).is(resp -> {
					Assertions.assertTrue(resp.getShortUrl().startsWith("http://localhost:3001/"));
					Assertions.assertEquals(0L, resp.getExpireIn().longValue());
				});
	}
}
