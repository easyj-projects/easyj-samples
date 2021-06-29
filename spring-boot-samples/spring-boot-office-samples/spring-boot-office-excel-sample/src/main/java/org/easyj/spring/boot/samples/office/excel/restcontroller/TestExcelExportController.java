package org.easyj.spring.boot.samples.office.excel.restcontroller;

import java.util.List;

import io.swagger.annotations.ApiImplicitParam;
import org.easyj.spring.boot.samples.office.excel.mockquery.MyEntity;
import org.easyj.spring.boot.samples.office.excel.mockquery.MyEntityStorage;
import org.easyj.spring.boot.samples.office.excel.mockquery.QueryParam;
import org.easyj.web.office.excel.ExcelExport;
import org.easyj.web.util.HttpUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试 @ExcelExport 的 Controller
 *
 * @author wangliang181230
 */
@RestController
public class TestExcelExportController {

	@ExcelExport(fileNamePre = "测试Excel导出功能", dataType = MyEntity.class)
	@ApiImplicitParam(name = "doExport", dataType = "Boolean", dataTypeClass = Boolean.class, defaultValue = "false", paramType = "query")
	@GetMapping("/test/excel-export")
	public List<MyEntity> testExcelExport(QueryParam param) {
		// 重要：当此次请求为excel导出请求时，将分布参数清除
		if (HttpUtils.isDoExportRequest()) {
			param.setPageSize(0); // 设置为0，表示不分页
		}

		return MyEntityStorage.doQuery(param);
	}
}
