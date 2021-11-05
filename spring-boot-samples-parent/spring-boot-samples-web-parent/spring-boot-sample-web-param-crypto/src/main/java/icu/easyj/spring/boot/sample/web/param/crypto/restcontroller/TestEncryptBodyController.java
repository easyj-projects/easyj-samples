package icu.easyj.spring.boot.sample.web.param.crypto.restcontroller;

import icu.easyj.spring.boot.sample.web.param.crypto.model.TestResult;
import icu.easyj.spring.boot.sample.web.param.crypto.param.TestBodyParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试 加密body 的Controller
 *
 * @author wangliang181230
 */
@RestController
public class TestEncryptBodyController {

	@PostMapping("/test/body")
	public TestResult testBodyParamCrypto(@RequestBody TestBodyParam param) {
		return new TestResult(param.getAaa() + param.getBbb());
	}
}
