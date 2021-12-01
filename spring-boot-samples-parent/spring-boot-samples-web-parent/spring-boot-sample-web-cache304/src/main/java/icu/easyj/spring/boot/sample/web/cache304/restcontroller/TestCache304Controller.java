package icu.easyj.spring.boot.sample.web.cache304.restcontroller;

import icu.easyj.web.cache304.annotation.Cache304;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试Cache304的Controller
 *
 * @author wangliang181230
 */
@RestController
public class TestCache304Controller {

	public static final long CACHE_SECONDS1 = 15L;
	public static final long CACHE_SECONDS2 = 20L;

	@Cache304(value = CACHE_SECONDS1, useMaxAge = true, limitMaxAge = 3, useCacheIfException = true)
	@RequestMapping(value = "/test/cache-304/true", method = RequestMethod.GET)
	public String useCache304AndUseMaxAgeAndUseCacheIfException(@RequestParam(required = false) Boolean needThrow) {
		if (Boolean.TRUE.equals(needThrow)) {
			throw new RuntimeException("故意抛出异常，用于测试`useCacheIfException = true`时的功能");
		}

		return "useCache304AndUseMaxAgeAndUseCacheIfException";
	}

	@Cache304(value = CACHE_SECONDS2, useMaxAge = false)
	@RequestMapping(value = "/test/cache-304/false", method = RequestMethod.GET)
	public String useCache304NoMaxAge(@RequestParam(required = false) Boolean needThrow) {
		if (Boolean.TRUE.equals(needThrow)) {
			throw new RuntimeException("故意抛出异常，用于测试`useCacheIfException = false`时，异常能否正常抛出");
		}

		return "useCache304NoMaxAge";
	}

	@RequestMapping(value = "/test/no-cache-304", method = RequestMethod.GET)
	public String noCache304() {
		return "noCache304";
	}
}
