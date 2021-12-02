package icu.easyj.spring.boot.sample.poi.excel.restcontroller;

import icu.easyj.spring.boot.test.BaseSpringBootMockMvcTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * {@link AfterTurnExcelImportController} 测试用例
 *
 * @author wangliang181230
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AfterTurnExcelImportControllerTest extends BaseSpringBootMockMvcTest {

	@Test
	public void testExcelImport() throws Exception {
		super.mockPostMultipart("/test/excel-import")
				.file("file", "testAfterTurnExcelImport.xlsx")
				.send()
				.isOk()
				.contentType().is(MediaType.APPLICATION_JSON)
				.content().is(3)
				.isMatch("[{\"name\":\"aaabbb\",\"age\":1,\"birthday\":\"2020-01-01*\",\"desc\":null},{\"name\":\"bbbccc\",\"age\":2,\"birthday\":\"2019-02-02*\",\"desc\":null},{\"name\":\"cccddd\",\"age\":3,\"birthday\":\"2018-03-03*\",\"desc\":null}]");
	}
}
