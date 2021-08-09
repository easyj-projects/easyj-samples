package icu.easyj.spring.boot.sample.poi.excel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 使用 EasyJ的Excel相关功能 的样例
 *
 * @author wangliang181230
 * <p>
 * 核心类，如下：
 * @see icu.easyj.web.poi.excel.ExcelExport
 * @see icu.easyj.web.poi.excel.ExcelExportAspect
 * @see icu.easyj.poi.excel.converter.IExcelConverter
 * @see icu.easyj.poi.excel.converter.impls.EasyjExcelConverter
 * @see icu.easyj.web.poi.excel.ExcelExportUtils
 * @see icu.easyj.poi.excel.util.ExcelUtils
 * @see icu.easyj.spring.boot.autoconfigure.web.poi.excel.export.EasyjExcelExporterAutoConfiguration
 */
@SpringBootApplication
public class PoiExcelSampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(PoiExcelSampleApplication.class, args);
	}
}
