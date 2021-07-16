package icu.easyj.spring.boot.sample.web.param.crypto.param;

/**
 * 测试POST接口的参数
 *
 * @author wangliang181230
 */
public class TestBodyParam {

	private String aaa;

	private String bbb;

	public TestBodyParam() {
	}

	public TestBodyParam(String aaa, String bbb) {
		this.aaa = aaa;
		this.bbb = bbb;
	}


	public String getAaa() {
		return aaa;
	}

	public void setAaa(String aaa) {
		this.aaa = aaa;
	}

	public String getBbb() {
		return bbb;
	}

	public void setBbb(String bbb) {
		this.bbb = bbb;
	}
}
