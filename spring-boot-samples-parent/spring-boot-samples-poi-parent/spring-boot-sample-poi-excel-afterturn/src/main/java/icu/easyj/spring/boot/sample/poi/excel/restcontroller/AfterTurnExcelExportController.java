package icu.easyj.spring.boot.sample.poi.excel.restcontroller;

import java.util.List;

import icu.easyj.spring.boot.sample.poi.excel.mockquery.MyEntity;
import icu.easyj.spring.boot.sample.poi.excel.mockquery.MyEntityStorage;
import icu.easyj.spring.boot.sample.poi.excel.mockquery.MyPageResult;
import icu.easyj.spring.boot.sample.poi.excel.mockquery.QueryParam;
import icu.easyj.web.poi.excel.ExcelExport;
import icu.easyj.web.util.HttpUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试基于`cn.afterturn:easypoi`实现的`@ExcelExport`导出Excel文件功能
 *
 * @author wangliang181230
 */
@RestController
public class AfterTurnExcelExportController {

	@ExcelExport(fileNamePre = "AfterTurn的Excel功能导出的文件（list）", dataType = MyEntity.class)
	//@ApiImplicitParam(name = "doExport", dataType = "Boolean", dataTypeClass = Boolean.class, defaultValue = "false", paramType = "query")
	@RequestMapping(value = "/test/excel-export/list", method = RequestMethod.GET)
	public List<MyEntity> testExcelExportByListResult(QueryParam param) {
		//region 重要：当此次请求为excel导出请求时，将分页参数清除（这段代码是此功能唯一的代码入侵）
		if (HttpUtils.isDoExportRequest()) {
			param.setPageSize(0); // 设置为0，表示不分页
		}
		//endregion

		return MyEntityStorage.findList(param);
	}

	@ExcelExport(fileNamePre = "AfterTurn的Excel功能导出的文件（paging）", dataType = MyEntity.class/*, listFieldName = "list"*/) // listFieldName已全局配置
	//@ApiImplicitParam(name = "doExport", dataType = "Boolean", dataTypeClass = Boolean.class, defaultValue = "false", paramType = "query")
	@RequestMapping(value = "/test/excel-export/paging", method = RequestMethod.GET)
	public MyPageResult<MyEntity> testExcelExportByPageResult(QueryParam param) {
		//region 重要：当此次请求为excel导出请求时，将分页参数清除（这段代码是此功能唯一的代码入侵）
		if (HttpUtils.isDoExportRequest()) {
			param.setPageSize(0); // 设置为0，表示不分页
		}
		//endregion

		return MyEntityStorage.findPaging(param);
	}

	@ExcelExport(fileNamePre = "AfterTurn的Excel功能导出的文件（one）", dataType = MyEntity.class)
	//@ApiImplicitParam(name = "doExport", dataType = "Boolean", dataTypeClass = Boolean.class, defaultValue = "false", paramType = "query")
	@RequestMapping(value = "/test/excel-export/one", method = RequestMethod.GET)
	public MyEntity testExcelExportByOne(QueryParam param) {
		//region 重要：当此次请求为excel导出请求时，将分页参数清除（这段代码是此功能唯一的代码入侵）
		if (HttpUtils.isDoExportRequest()) {
			param.setPageSize(0); // 设置为0，表示不分页
		}
		//endregion

		List<MyEntity> list = MyEntityStorage.findList(param);
		if (list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}
	}
}
