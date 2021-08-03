package icu.easyj.spring.boot.sample.poi.excel;

import icu.easyj.spring.boot.sample.poi.excel.restcontroller.AfterTurnExcelExportController;
import icu.easyj.spring.boot.sample.poi.excel.restcontroller.AfterTurnExcelImportController;
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
	AfterTurnExcelExportController afterTurnExcelExportController;
	@Autowired
	AfterTurnExcelImportController afterTurnExcelImportController;

	@Autowired
	IExcelExporter excelExporter;

	/**
	 * 测试是否能够启动
	 */
	@Test
	void testStartup() {
		Assertions.assertNotNull(afterTurnExcelExportController);
		Assertions.assertNotNull(afterTurnExcelImportController);

		// 判断Excel导出器，是否为基于afterturn的实现
		Assertions.assertTrue(excelExporter instanceof AfterturnExcelExporterImpl);
	}
}
