package icu.easyj.spring.boot.sample.environment.enhanced.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * 测试各种”函数式配置“的配置类
 *
 * @author wangliang181230
 * @see icu.easyj.spring.boot.env.enhanced.EasyjFunctionPropertySource 函数式配置源
 * @see icu.easyj.spring.boot.env.enhanced.EasyjFunctionPropertySourceEnvironmentPostProcessor 加载函数式配置源的环境处理器
 */
@Component
@ConfigurationProperties("test")
public class TestEasyjFunctionPropertySourceProperties {

	/**
	 * @see icu.easyj.spring.boot.env.enhanced.util.CryptoPropertyUtils
	 */
	private String cryptoDecrypt;

	/**
	 * @see icu.easyj.spring.boot.env.enhanced.util.NetPropertyUtils
	 */
	private String localIpPattern;

	/**
	 * @see icu.easyj.spring.boot.env.enhanced.util.RandomPropertyUtils
	 */
	private String random;
	private String randomUuid32;
	private String randomUuid;
	private int randomPort;
	private short randomShort;
	private int randomInt;
	private long randomLong;
	private String randomChoose;


	//region Getter、Setter

	public String getCryptoDecrypt() {
		return cryptoDecrypt;
	}

	public void setCryptoDecrypt(String cryptoDecrypt) {
		this.cryptoDecrypt = cryptoDecrypt;
	}

	public String getLocalIpPattern() {
		return localIpPattern;
	}

	public void setLocalIpPattern(String localIpPattern) {
		this.localIpPattern = localIpPattern;
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

	//endregion
}
