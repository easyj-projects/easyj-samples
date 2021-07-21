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
 * @see icu.easyj.web.poi.excel.IExcelExporter
 * @see icu.easyj.web.poi.excel.impls.AfterturnExcelExporterImpl
 * @see cn.afterturn.easypoi.excel.ExcelExportUtil
 * @see cn.afterturn.easypoi.excel.ExcelImportUtil
 * @see icu.easyj.spring.boot.autoconfigure.web.poi.excel.export.AfterturnExcelExporterAutoConfiguration
 */
@SpringBootApplication
public class AfterTurnPoiExcelSampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(AfterTurnPoiExcelSampleApplication.class, args);
	}
}
