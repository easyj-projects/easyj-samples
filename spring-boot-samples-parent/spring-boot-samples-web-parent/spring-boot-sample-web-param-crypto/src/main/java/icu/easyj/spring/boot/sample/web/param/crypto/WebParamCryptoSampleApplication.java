package icu.easyj.spring.boot.sample.web.param.crypto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 使用出入参加密解密功能的样例
 *
 * @author wangliang181230
 *
 * <p>
 * 核心类，如下：
 * @see icu.easyj.web.filter.AbstractFilter
 * @see icu.easyj.web.param.crypto.ParamCryptoFilter
 * @see icu.easyj.web.filter.IFilterProperties
 * @see icu.easyj.web.param.crypto.IParamCryptoHandlerProperties
 * @see icu.easyj.web.param.crypto.IParamCryptoHandler
 * @see icu.easyj.spring.boot.autoconfigure.web.param.crypto.EasyjWebParamCryptoAutoConfiguration
 */
@SpringBootApplication
public class WebParamCryptoSampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebParamCryptoSampleApplication.class, args);
	}
}
