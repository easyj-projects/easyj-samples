package icu.easyj.spring.boot.sample.poi.excel;

import icu.easyj.spring.boot.sample.poi.excel.restcontroller.TestExcelExportController;
import icu.easyj.spring.boot.sample.poi.excel.restcontroller.TestExcelImportController;
import icu.easyj.web.poi.excel.IExcelExporter;
import icu.easyj.web.poi.excel.impls.AfterturnExcelExporterImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * {@link AfterTurnPoiExcelSampleApplication} 测试类
 *
 * @author wangliang181230
 */
@SpringBootTest
class AfterTurnPoiExcelSampleApplicationTest {

	@Autowired
	TestExcelExportController testExcelExportController;
	@Autowired
	TestExcelImportController testExcelImportController;

	@Autowired
	IExcelExporter excelExporter;

	/**
	 * 测试是否能够启动
	 */
	@Test
	void testStartup() {
		Assertions.assertNotNull(testExcelExportController);
		Assertions.assertNotNull(testExcelImportController);

		// 判断Excel导出器，是否为基于afterturn的实现
		Assertions.assertTrue(excelExporter instanceof AfterturnExcelExporterImpl);
	}
}
