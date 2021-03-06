package icu.easyj.spring.boot.sample.poi.excel;

import javax.annotation.Resource;

import icu.easyj.poi.excel.converter.ExcelConverterUtils;
import icu.easyj.poi.excel.converter.IExcelConverter;
import icu.easyj.poi.excel.converter.impls.EasyjExcelConverter;
import icu.easyj.spring.boot.sample.poi.excel.mockquery.MyEntity;
import icu.easyj.spring.boot.sample.poi.excel.restcontroller.ExcelExportController;
import icu.easyj.spring.boot.sample.poi.excel.restcontroller.ExcelImportController;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * {@link PoiExcelSampleApplication} 测试类
 *
 * @author wangliang181230
 */
@SpringBootTest
public class PoiExcelSampleApplicationTest {

	@Resource
	ExcelExportController excelExportController;
	@Resource
	ExcelImportController excelImportController;

	/**
	 * 测试是否能够启动
	 */
	@Test
	public void testStartup() {
		Assertions.assertNotNull(excelExportController);
		Assertions.assertNotNull(excelImportController);

		IExcelConverter converter = ExcelConverterUtils.getConverter(MyEntity.class);
		Assertions.assertEquals(EasyjExcelConverter.class, converter.getClass());
	}
}
