package org.easyj.samples.office.excel.config;

import org.easyj.web.office.excel.ExcelExportAspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Excel导出的AOP配置
 *
 * @author wangliang181230
 */
@Configuration
public class EasyjWebOfficeExcelConfig {

	@Bean
	public ExcelExportAspect excelExportAspect() {
		return new ExcelExportAspect();
	}
}
