package org.easyj.spring.boot.samples.office.excel.restcontroller;

import java.util.List;

import org.easyj.core.util.StringUtils;
import org.easyj.office.excel.util.ExcelUtils;
import org.easyj.spring.boot.samples.office.excel.mockquery.MyEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * 测试 @ExcelExport 的 Controller
 *
 * @author wangliang181230
 */
@RestController
public class TestExcelImportController {

	@PostMapping("/test/excel-import")
	public List<MyEntity> testExcelImport(@RequestPart("file") MultipartFile file) throws Exception {
		// excel文件转为列表数据
		List<MyEntity> list = ExcelUtils.toList(file.getInputStream(), MyEntity.class);

		// 打印一下
		System.out.println(StringUtils.toString(list));

		// 将转换后的数据直接返回，方便查看
		return list;
	}
}
