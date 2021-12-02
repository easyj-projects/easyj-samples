package icu.easyj.spring.boot.sample.poi.excel;

import javax.annotation.Resource;

import icu.easyj.poi.excel.converter.ExcelConverterUtils;
import icu.easyj.poi.excel.converter.IExcelConverter;
import icu.easyj.poi.excel.converter.impls.AfterTurnExcelConverter;
import icu.easyj.spring.boot.sample.poi.excel.mockquery.MyEntity;
import icu.easyj.spring.boot.sample.poi.excel.restcontroller.AfterTurnExcelExportController;
import icu.easyj.spring.boot.sample.poi.excel.restcontroller.AfterTurnExcelImportController;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * {@link AfterTurnPoiExcelSampleApplication} 测试类
 *
 * @author wangliang181230
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AfterTurnPoiExcelSampleApplicationTest {

	@Resource
	AfterTurnExcelExportController afterTurnExcelExportController;
	@Resource
	AfterTurnExcelImportController afterTurnExcelImportController;

	/**
	 * 测试是否能够启动
	 */
	@Test
	public void testStartup() {
		Assertions.assertNotNull(afterTurnExcelExportController);
		Assertions.assertNotNull(afterTurnExcelImportController);

		IExcelConverter converter = ExcelConverterUtils.getConverter(MyEntity.class);
		Assertions.assertEquals(AfterTurnExcelConverter.class, converter.getClass());
	}
}
