package org.easyj.samples.office.excel.restcontroller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.swagger.annotations.ApiImplicitParam;
import org.easyj.samples.office.excel.entity.MyEntity;
import org.easyj.samples.office.excel.param.QueryParam;
import org.easyj.web.office.excel.ExcelExportSupport;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试 @ExcelExportSupport 的 Controller
 *
 * @author wangliang181230
 */
@RestController
public class TestExcelExportController {

	@ExcelExportSupport(fileNamePre = "测试Excel导出功能", dataType = MyEntity.class)
	@ApiImplicitParam(name = "doExcelExport", dataType = "Boolean", dataTypeClass = Boolean.class, defaultValue = "false", paramType = "query")
	@GetMapping("/test/excel-export")
	public List<MyEntity> testExcelExport(QueryParam param) {
		return doQueryEntity(param);
	}

	/**
	 * 模拟查询操作
	 *
	 * @param param 查询参数
	 * @return list 查询到的列表数据
	 */
	private List<MyEntity> doQueryEntity(QueryParam param) {
		List<MyEntity> list = new ArrayList<>();

		if (param.getDataCount() != null) {
			if (param.getDataCount() > 0) {
				list.add(new MyEntity("aaa", 1, new Date(2020 - 1900, 1 - 1, 1), "desc111"));
			}
			if (param.getDataCount() > 1) {
				list.add(new MyEntity("bbb", 2, new Date(2019 - 1900, 2 - 1, 2), "desc222"));
			}
			if (param.getDataCount() > 2) {
				list.add(new MyEntity("ccc", 3, new Date(2018 - 1900, 3 - 1, 3), "desc333"));
			}
		}
		return list;
	}
}
