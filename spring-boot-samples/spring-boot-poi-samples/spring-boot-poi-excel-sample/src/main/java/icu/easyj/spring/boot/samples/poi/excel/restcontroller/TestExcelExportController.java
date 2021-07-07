package icu.easyj.spring.boot.samples.poi.excel.restcontroller;

import java.util.List;

import icu.easyj.spring.boot.samples.poi.excel.mockquery.MyEntity;
import icu.easyj.spring.boot.samples.poi.excel.mockquery.MyEntityStorage;
import icu.easyj.spring.boot.samples.poi.excel.mockquery.MyPageResult;
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

	@ExcelExport(fileNamePre = "EasyJ的Excel功能导出的文件（list）", dataType = MyEntity.class)
	@ApiImplicitParam(name = "doExport", dataType = "Boolean", dataTypeClass = Boolean.class, defaultValue = "false", paramType = "query")
	@GetMapping("/test/excel-export/list")
	public List<MyEntity> testExcelExportByListResult(QueryParam param) {
		// 重要：当此次请求为excel导出请求时，将分页参数清除
		if (HttpUtils.isDoExportRequest()) {
			param.setPageSize(0); // 设置为0，表示不分页
		}

		return MyEntityStorage.findList(param);
	}

	@ExcelExport(fileNamePre = "EasyJ的Excel功能导出的文件（paging）", dataType = MyEntity.class/*, listFieldName = "list"*/) // listFieldName已全局配置
	@ApiImplicitParam(name = "doExport", dataType = "Boolean", dataTypeClass = Boolean.class, defaultValue = "false", paramType = "query")
	@GetMapping("/test/excel-export/paging")
	public MyPageResult<MyEntity> testExcelExportByPageResult(QueryParam param) {
		// 重要：当此次请求为excel导出请求时，将分页参数清除
		if (HttpUtils.isDoExportRequest()) {
			param.setPageSize(0); // 设置为0，表示不分页
		}

		return MyEntityStorage.findPaging(param);
	}

	@ExcelExport(fileNamePre = "EasyJ的Excel功能导出的文件（one）", dataType = MyEntity.class)
	@ApiImplicitParam(name = "doExport", dataType = "Boolean", dataTypeClass = Boolean.class, defaultValue = "false", paramType = "query")
	@GetMapping("/test/excel-export/one")
	public MyEntity testExcelExportByOne(QueryParam param) {
		// 重要：当此次请求为excel导出请求时，将分页参数清除
		if (HttpUtils.isDoExportRequest()) {
			param.setPageSize(0); // 设置为0，表示不分页
		}

		List<MyEntity> list = MyEntityStorage.findList(param);
		if (list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}
	}
}
