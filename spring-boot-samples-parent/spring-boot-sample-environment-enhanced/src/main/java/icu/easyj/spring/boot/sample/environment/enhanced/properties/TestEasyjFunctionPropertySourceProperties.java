package icu.easyj.spring.boot.sample.environment.enhanced.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 测试各种函数式配置
 *
 * @author wangliang181230
 * @see icu.easyj.spring.boot.env.enhanced.EasyjFunctionPropertySource // 函数式配置源
 */
@Configuration(proxyBeanMethods = false)
@ConfigurationProperties("test")
public class TestEasyjFunctionPropertySourceProperties {

	private String decrypt;

	private String random;

	private String randomUuid32;

	private String randomUuid;

	private int randomPort;

	private short randomShort;

	private int randomInt;

	private long randomLong;

	private String randomChoose;


	public String getDecrypt() {
		return decrypt;
	}

	public void setDecrypt(String decrypt) {
		this.decrypt = decrypt;
	}

	public String getRandom() {
		return random;
	}

	public void setRandom(String random) {
		this.random = random;
	}

	public String getRandomUuid32() {
		return randomUuid32;
	}

	public void setRandomUuid32(String randomUuid32) {
		this.randomUuid32 = randomUuid32;
	}

	public String getRandomUuid() {
		return randomUuid;
	}

	public void setRandomUuid(String randomUuid) {
		this.randomUuid = randomUuid;
	}

	public int getRandomPort() {
		return randomPort;
	}

	public void setRandomPort(int randomPort) {
		this.randomPort = randomPort;
	}

	public short getRandomShort() {
		return randomShort;
	}

	public void setRandomShort(short randomShort) {
		this.randomShort = randomShort;
	}

	public int getRandomInt() {
		return randomInt;
	}

	public void setRandomInt(int randomInt) {
		this.randomInt = randomInt;
	}

	public long getRandomLong() {
		return randomLong;
	}

	public void setRandomLong(long randomLong) {
		this.randomLong = randomLong;
	}

	public String getRandomChoose() {
		return randomChoose;
	}

	public void setRandomChoose(String randomChoose) {
		this.randomChoose = randomChoose;
	}
}
