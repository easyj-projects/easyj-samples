package icu.easyj.spring.boot.sample.poi.excel;

import icu.easyj.spring.boot.sample.poi.excel.restcontroller.ExcelExportController;
import icu.easyj.spring.boot.sample.poi.excel.restcontroller.ExcelImportController;
import icu.easyj.web.poi.excel.IExcelExporter;
import icu.easyj.web.poi.excel.impls.DefaultExcelExporterImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * {@link PoiExcelSampleApplication} 测试类
 *
 * @author wangliang181230
 */
@SpringBootTest
class PoiExcelSampleApplicationTest {

	@Autowired
	ExcelExportController excelExportController;
	@Autowired
	ExcelImportController excelImportController;

	@Autowired
	IExcelExporter excelExporter;

	/**
	 * 测试是否能够启动
	 */
	@Test
	void testStartup() {
		Assertions.assertNotNull(excelExportController);
		Assertions.assertNotNull(excelImportController);

		// 判断Excel导出器，是否为基于easyj-poi-excel的实现
		Assertions.assertTrue(excelExporter instanceof DefaultExcelExporterImpl);
	}
}
