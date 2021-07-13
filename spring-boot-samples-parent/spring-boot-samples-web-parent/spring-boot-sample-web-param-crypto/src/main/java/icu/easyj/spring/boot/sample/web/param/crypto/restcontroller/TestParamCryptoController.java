package icu.easyj.spring.boot.sample.web.param.crypto.restcontroller;

import icu.easyj.spring.boot.sample.web.param.crypto.model.TestResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试Cache304的Controller
 *
 * @author wangliang181230
 */
@RestController
public class TestParamCryptoController {

	@GetMapping("/test/querystring")
	public TestResult testQueryStringParamCrypto() {
		return new TestResult("testQueryStringParamCrypto");
	}
}
