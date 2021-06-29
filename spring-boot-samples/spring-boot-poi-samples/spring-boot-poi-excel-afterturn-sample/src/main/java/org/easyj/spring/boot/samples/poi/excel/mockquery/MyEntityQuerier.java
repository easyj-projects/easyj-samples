package org.easyj.spring.boot.samples.poi.excel.mockquery;

import org.easyj.core.util.ComparableUtils;
import org.easyj.data.memory.querier.AbstractQuerier;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

/**
 * MyEntity查询器
 *
 * @author wangliang181230
 */
public class MyEntityQuerier extends AbstractQuerier<MyEntity> {

	/**
	 * 查询参数
	 */
	private final QueryParam queryParam;

	/**
	 * 构造函数
	 *
	 * @param queryParam 查询参数
	 */
	public MyEntityQuerier(QueryParam queryParam) {
		Assert.notNull(queryParam, "queryParam must be not null");
		this.queryParam = queryParam;

		if (queryParam.getPageNumber() != null) {
			super.pageNumber = queryParam.getPageNumber();
		}
		if (queryParam.getPageSize() != null) {
			super.pageSize = queryParam.getPageSize();
		}
	}

	@Override
	public <D extends MyEntity> boolean isMatch(D data) {
		// 姓名模糊查询
		if (StringUtils.hasText(queryParam.getNameLike())) {
			if (data.getName() == null || !data.getName().contains(queryParam.getNameLike())) {
				return false; // 姓名不匹配
			}
		}

		// 年龄范围查询
		if (queryParam.getMinAge() != null || queryParam.getMaxAge() != null) {
			if (data.getAge() == null) {
				return false; // 年龄为空，不匹配
			}
			if (queryParam.getMinAge() != null) {
				if (data.getAge() < queryParam.getMinAge()) {
					return false; // 最小年龄不匹配
				}
			}
			if (queryParam.getMaxAge() != null) {
				return data.getAge() <= queryParam.getMaxAge(); // 最大年龄不匹配
			}
		}

		// 匹配成功
		return true;
	}

	@Override
	public <D extends MyEntity> int compareByFieldName(D a, D b, String sortFieldName) {
		switch (sortFieldName) {
			case "name":
				return ComparableUtils.compare(a.getName(), b.getName());
			case "age":
				return ComparableUtils.compare(a.getAge(), b.getAge());
			case "birthday":
				return ComparableUtils.compare(a.getBirthday(), b.getBirthday());
			default:
				throw new RuntimeException("未知的属性名: " + sortFieldName);
		}
	}
}
