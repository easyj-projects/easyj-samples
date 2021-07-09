package icu.easyj.spring.boot.sample.poi.excel.mockquery;

import java.util.List;

/**
 * 分页查询出参
 *
 * @author wangliang181230
 */
public class MyPageResult<T> {

	private int total;

	private List<T> list;


	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}
}
