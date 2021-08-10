package icu.easyj.spring.boot.sample.poi.excel.mockquery;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import icu.easyj.core.util.DateUtils;

/**
 * MyEntity存储器
 *
 * @author wangliang181230
 */
public class MyEntityStorage {

	private static final List<MyEntity> DATA_LIST;

	static {
		try {
			DATA_LIST = new ArrayList<>();
			DATA_LIST.add(new MyEntity("aaabbb", 4, DateUtils.parseDate("2017-01-01"), "aaaaa"));
			DATA_LIST.add(new MyEntity("bbbccc", 5, DateUtils.parseDate("2016-01-01"), "bbbbb"));
			DATA_LIST.add(new MyEntity("cccddd", 6, DateUtils.parseDate("2015-01-01"), "ccccc"));
			DATA_LIST.add(new MyEntity("dddeee", 7, DateUtils.parseDate("2014-01-01"), "ddddd"));
			DATA_LIST.add(new MyEntity("eeefff", 8, DateUtils.parseDate("2013-01-01"), "eeeee"));
			DATA_LIST.add(new MyEntity("fffggg", 9, DateUtils.parseDate("2012-01-01"), "fffff"));
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}

	public static List<MyEntity> findList(QueryParam queryParam) {
		MyEntityQuerier querier = new MyEntityQuerier(queryParam);
		return querier.doQuery(DATA_LIST);
	}

	public static MyPageResult<MyEntity> findPaging(QueryParam queryParam) {
		MyPageResult<MyEntity> page = new MyPageResult<>();

		MyEntityQuerier querier = new MyEntityQuerier(queryParam);

		page.setTotal(querier.doCount(DATA_LIST));
		page.setList(querier.doQuery(DATA_LIST));

		return page;
	}
}
