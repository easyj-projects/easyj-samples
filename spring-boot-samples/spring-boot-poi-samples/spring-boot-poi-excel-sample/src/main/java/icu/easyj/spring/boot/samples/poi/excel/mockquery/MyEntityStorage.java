package icu.easyj.spring.boot.samples.poi.excel.mockquery;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * MyEntity存储器
 *
 * @author wangliang181230
 */
public class MyEntityStorage {

	private static final List<MyEntity> DATA_LIST;

	static {
		DATA_LIST = new ArrayList<>();
		DATA_LIST.add(new MyEntity("aaabbb", 4, new Date(2021 - 1900 - 4, 1 - 1, 1), "aaaaa"));
		DATA_LIST.add(new MyEntity("bbbccc", 5, new Date(2021 - 1900 - 5, 1 - 1, 1), "bbbbb"));
		DATA_LIST.add(new MyEntity("cccddd", 6, new Date(2021 - 1900 - 6, 1 - 1, 1), "ccccc"));
		DATA_LIST.add(new MyEntity("dddeee", 7, new Date(2021 - 1900 - 7, 1 - 1, 1), "ddddd"));
		DATA_LIST.add(new MyEntity("eeefff", 8, new Date(2021 - 1900 - 8, 1 - 1, 1), "eeeee"));
		DATA_LIST.add(new MyEntity("fffggg", 9, new Date(2021 - 1900 - 9, 1 - 1, 1), "fffff"));
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
