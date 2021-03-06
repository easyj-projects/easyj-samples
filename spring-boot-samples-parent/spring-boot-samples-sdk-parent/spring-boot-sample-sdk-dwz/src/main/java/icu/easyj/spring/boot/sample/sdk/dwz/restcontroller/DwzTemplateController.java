package icu.easyj.spring.boot.sample.sdk.dwz.restcontroller;

import javax.annotation.Resource;

import icu.easyj.sdk.dwz.DwzResponse;
import icu.easyj.sdk.dwz.IDwzTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试 短链接服务 的Controller
 *
 * @author wangliang181230
 */
@RestController
public class DwzTemplateController {

	@Resource
	private IDwzTemplate dwzTemplate;

	/**
	 * 单面身份证识别
	 *
	 * @param longUrl 长链接
	 * @return response 短链接服务响应
	 */
	@RequestMapping(value = "/test/dwz/create", method = RequestMethod.GET)
	public DwzResponse createShortUrl(@RequestParam String longUrl) {
		return dwzTemplate.createShortUrl(longUrl);
	}
}
