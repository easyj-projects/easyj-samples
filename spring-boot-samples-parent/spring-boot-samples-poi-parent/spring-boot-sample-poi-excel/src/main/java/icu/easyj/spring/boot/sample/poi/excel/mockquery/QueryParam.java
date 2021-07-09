package icu.easyj.spring.boot.sample.poi.excel.mockquery;

import icu.easyj.core.constant.PageConstants;

/**
 * 模拟查询用的参数
 *
 * @author wangliang181230
 */
public class QueryParam {

	private String nameLike;
	private Integer maxAge;
	private Integer minAge;

	private Integer pageSize = PageConstants.DEFAULT_PAGE_SIZE;
	private Integer pageNumber = PageConstants.FIRST_PAGE_NUMBER;

	public String getNameLike() {
		return nameLike;
	}

	public void setNameLike(String nameLike) {
		this.nameLike = nameLike;
	}

	public Integer getMaxAge() {
		return maxAge;
	}

	public void setMaxAge(Integer maxAge) {
		this.maxAge = maxAge;
	}

	public Integer getMinAge() {
		return minAge;
	}

	public void setMinAge(Integer minAge) {
		this.minAge = minAge;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(Integer pageNumber) {
		this.pageNumber = pageNumber;
	}
}
