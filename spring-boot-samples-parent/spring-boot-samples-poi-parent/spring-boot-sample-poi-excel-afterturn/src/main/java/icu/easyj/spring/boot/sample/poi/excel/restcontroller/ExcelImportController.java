package icu.easyj.spring.boot.sample.poi.excel.restcontroller;

import java.util.List;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import icu.easyj.core.util.StringUtils;
import icu.easyj.spring.boot.sample.poi.excel.mockquery.MyEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * 测试 ExcelImportUtil实现Excel文件导入功能
 *
 * @author wangliang181230
 */
@RestController
public class ExcelImportController {

	@PostMapping("/test/excel-import")
	public List<MyEntity> testExcelImport(@RequestPart("file") MultipartFile file) throws Exception {
		// excel文件转为列表数据
		List<MyEntity> list = ExcelImportUtil.importExcel(file.getInputStream(), MyEntity.class, new ImportParams());

		// 打印一下
		System.out.println(StringUtils.toString(list));

		// 将转换后的数据直接返回，方便查看
		return list;
	}
}
