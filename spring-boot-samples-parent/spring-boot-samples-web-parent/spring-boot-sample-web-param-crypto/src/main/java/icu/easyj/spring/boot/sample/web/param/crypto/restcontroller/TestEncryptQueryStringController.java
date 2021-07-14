package icu.easyj.spring.boot.sample.web.param.crypto.restcontroller;

import icu.easyj.spring.boot.sample.web.param.crypto.model.TestResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试 加密QueryString 的Controller
 *
 * @author wangliang181230
 */
@RestController
public class TestEncryptQueryStringController {

	@GetMapping("/test/querystring")
	public TestResult testQueryStringParamCrypto(String s1, String s2) {
		return new TestResult(s1 + s2);
	}
}
