package org.easyj.samples.office.excel.restcontroller;

import java.util.List;

import org.easyj.core.util.StringUtils;
import org.easyj.office.excel.util.ExcelUtils;
import org.easyj.samples.office.excel.entity.MyEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * 测试 @ExcelExportSupport 的 Controller
 *
 * @author wangliang181230
 */
@RestController
public class TestExcelImportController {

	@PostMapping("/test/excel-import")
	public String testExcelImport(@RequestPart("file") MultipartFile file) throws Exception {
		List<MyEntity> list = ExcelUtils.toList(file.getInputStream(), MyEntity.class);
		for (MyEntity entity : list) {
			// 打印一下
			System.out.println(StringUtils.toString(entity));

			// 实际开发中，保存这条数据
			//service.doSave(entity);
		}
		return "success";
	}
}
