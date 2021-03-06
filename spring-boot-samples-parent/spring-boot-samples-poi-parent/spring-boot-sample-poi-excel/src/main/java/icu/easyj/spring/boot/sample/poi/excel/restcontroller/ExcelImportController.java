package icu.easyj.spring.boot.sample.poi.excel.restcontroller;

import java.util.List;

import icu.easyj.core.util.StringUtils;
import icu.easyj.poi.excel.util.ExcelUtils;
import icu.easyj.spring.boot.sample.poi.excel.mockquery.MyEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * 测试 ExcelUtils实现Excel文件导入功能
 *
 * @author wangliang181230
 */
@RestController
public class ExcelImportController {

	@RequestMapping(value = "/test/excel-import", method = RequestMethod.POST)
	public List<MyEntity> testExcelImport(@RequestPart("file") MultipartFile file) throws Exception {
		// excel文件转为列表数据
		List<MyEntity> list = ExcelUtils.toList(file.getInputStream(), MyEntity.class);

		// 打印一下
		System.out.println(StringUtils.toString(list));

		// 将转换后的数据直接返回，方便查看
		return list;
	}
}
