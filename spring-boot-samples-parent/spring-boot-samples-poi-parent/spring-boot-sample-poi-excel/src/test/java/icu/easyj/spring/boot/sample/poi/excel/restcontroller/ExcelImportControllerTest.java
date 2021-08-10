package icu.easyj.spring.boot.sample.poi.excel.restcontroller;

import icu.easyj.spring.boot.test.BaseSpringBootMockMvcTest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;

/**
 * {@link ExcelImportController} 测试用例
 *
 * @author wangliang181230
 */
@SpringBootTest
class ExcelImportControllerTest extends BaseSpringBootMockMvcTest {

	@Test
	void testExcelImport() throws Exception {
		super.mockPostMultipart("/test/excel-import")
				.file("file", "testExcelImport.xlsx")
				.send()
				.isOk()
				.contentType().is(MediaType.APPLICATION_JSON)
				.content().is(3)
				.is("[{\"name\":\"aaabbb\",\"age\":1,\"birthday\":\"2020-01-01 00:00:00.000\",\"desc\":null},{\"name\":\"bbbccc\",\"age\":2,\"birthday\":\"2019-02-02 00:00:00.000\",\"desc\":null},{\"name\":\"cccddd\",\"age\":3,\"birthday\":\"2018-03-03 00:00:00.000\",\"desc\":null}]");
	}
}
