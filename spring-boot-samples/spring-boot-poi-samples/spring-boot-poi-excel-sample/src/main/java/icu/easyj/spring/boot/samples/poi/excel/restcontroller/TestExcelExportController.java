package icu.easyj.spring.boot.samples.poi.excel.restcontroller;

import java.util.List;

import icu.easyj.spring.boot.samples.poi.excel.mockquery.MyEntity;
import icu.easyj.spring.boot.samples.poi.excel.mockquery.MyEntityStorage;
import icu.easyj.spring.boot.samples.poi.excel.mockquery.QueryParam;
import icu.easyj.web.poi.excel.ExcelExport;
import icu.easyj.web.util.HttpUtils;
import io.swagger.annotations.ApiImplicitParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试基于`icu.easyj:easyj-poi-excel`实现的`@ExcelExport`导出Excel文件功能
 *
 * @author wangliang181230
 */
@RestController
public class TestExcelExportController {

	@ExcelExport(fileNamePre = "测试Excel导出功能", dataType = MyEntity.class)
	@ApiImplicitParam(name = "doExport", dataType = "Boolean", dataTypeClass = Boolean.class, defaultValue = "false", paramType = "query")
	@GetMapping("/test/excel-export")
	public List<MyEntity> testExcelExport(QueryParam param) {
		// 重要：当此次请求为excel导出请求时，将分页参数清除
		if (HttpUtils.isDoExportRequest()) {
			param.setPageSize(0); // 设置为0，表示不分页
		}

		return MyEntityStorage.doQuery(param);
	}
}
