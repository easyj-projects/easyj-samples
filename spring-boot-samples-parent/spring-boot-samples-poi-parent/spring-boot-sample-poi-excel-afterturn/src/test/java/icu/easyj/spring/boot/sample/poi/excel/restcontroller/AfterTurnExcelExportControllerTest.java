package icu.easyj.spring.boot.sample.poi.excel.restcontroller;

import icu.easyj.spring.boot.sample.poi.excel.mockquery.MyEntity;
import icu.easyj.spring.boot.test.BaseSpringBootMockMvcTest;
import icu.easyj.web.constant.HttpHeaderConstants;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * {@link AfterTurnExcelExportController} 测试用例
 *
 * @author wangliang181230
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AfterTurnExcelExportControllerTest extends BaseSpringBootMockMvcTest {

	@Test
	public void testExcelExport_NotDoExport() throws Exception {
		super.mockGet("/test/excel-export/list")
				.queryParam("nameLike", "bbb")
				.queryParam("pageSize", "1")
				.send()
				.isOk()
				.contentType().is(MediaType.APPLICATION_JSON)
				.header().notContains(HttpHeaders.CONTENT_DISPOSITION).end()
				.content().is(1)
				.isMatch("[{\"name\":\"aaabbb\",\"age\":4,\"birthday\":\"2017-01-01*\",\"desc\":\"aaaaa\"}]");
	}

	@Test
	public void testExcelExport_DoExport() throws Exception {
		super.mockGet("/test/excel-export/list")
				.queryParam("doExport", "true")
				.queryParam("nameLike", "bbb")
				.queryParam("pageSize", "1")
				.send()
				.isOk()
				.contentType().is("application/vnd.ms-excel")
				.header()
				.isMatch(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"*\"")
				.is(HttpHeaders.CACHE_CONTROL, HttpHeaderConstants.NO_CACHE).end()
				.file().excelToList(MyEntity.class)
				.is(2)
				// TODO: birthday字段加过注解，但是没有解析出来，应该是afterturn的BUG。已提交issue给afterturn。
				.is("[MyEntity(name=\"aaabbb\", age=4, birthday=null, desc=null), MyEntity(name=\"bbbccc\", age=5, birthday=null, desc=null)]");
	}
}
