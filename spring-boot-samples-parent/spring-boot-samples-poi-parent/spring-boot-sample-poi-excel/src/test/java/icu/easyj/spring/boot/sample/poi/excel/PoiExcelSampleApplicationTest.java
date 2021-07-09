package icu.easyj.spring.boot.sample.poi.excel;

import icu.easyj.spring.boot.sample.poi.excel.restcontroller.TestExcelExportController;
import icu.easyj.spring.boot.sample.poi.excel.restcontroller.TestExcelImportController;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author wangliang181230
 */
@SpringBootTest
public class PoiExcelSampleApplicationTest {

	@Autowired
	TestExcelExportController testExcelExportController;
	@Autowired
	TestExcelImportController testExcelImportController;

	@Test
	void testStartup() {
		Assertions.assertNotNull(testExcelExportController);
		Assertions.assertNotNull(testExcelImportController);
	}
}
