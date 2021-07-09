package icu.easyj.spring.boot.sample.web.cache304.restcontroller;

import icu.easyj.web.cache304.annotation.Cache304;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试Cache304的Controller
 *
 * @author wangliang181230
 */
@RestController
public class TestCache304Controller {

	@Cache304(value = 15, useMaxAge = true, limitMaxAge = 3)
	@GetMapping("/test/cache-304/10-true")
	public String test1() {
		return "test1";
	}

	@Cache304(value = 20, useMaxAge = false)
	@GetMapping("/test/cache-304/20-false")
	public String test2() {
		return "test2";
	}

	@GetMapping("/test/no-cache-304")
	public String testNoCache304() {
		return "testNoCache304";
	}
}
