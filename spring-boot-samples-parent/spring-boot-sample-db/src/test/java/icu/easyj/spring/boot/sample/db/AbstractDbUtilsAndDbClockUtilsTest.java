/*
 * Copyright 2021 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package icu.easyj.spring.boot.sample.db;

import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.sql.DataSource;

import icu.easyj.core.exception.NotSupportedException;
import icu.easyj.core.util.DateUtils;
import icu.easyj.core.util.version.VersionUtils;
import icu.easyj.db.constant.DbTypeConstants;
import icu.easyj.db.util.DbClockUtils;
import icu.easyj.db.util.DbUtils;
import icu.easyj.db.util.PrimaryDataSourceHolder;
import icu.easyj.test.util.TestUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * {@link DbUtils} 和 {@link DbClockUtils} 测试类
 *
 * @author wangliang181230
 */
public abstract class AbstractDbUtilsAndDbClockUtilsTest {

	private static final String SEQ_NAME = "test_seq";


	protected final DataSource dataSource;


	protected AbstractDbUtilsAndDbClockUtilsTest(DataSource dataSource) {
		this.dataSource = dataSource;
	}


	protected abstract String getDbType();

	protected abstract String getMinVersion();


	/**
	 * 测试获取数据库类型
	 */
	@Test
	void testGetDbType() {
		Assertions.assertEquals(dataSource, PrimaryDataSourceHolder.get());

		String dbType = DbUtils.getDbType(dataSource);
		System.out.println(dbType);
		Assertions.assertEquals(this.getDbType(), dbType.toLowerCase());
	}

	/**
	 * 测试获取数据库版本
	 */
	@Test
	void testGetDbVersion() {
		String version = DbUtils.getDbVersion(dataSource);
		System.out.println(version);
		System.out.println(VersionUtils.toLong(version));
		Assertions.assertTrue(VersionUtils.toLong(version) > VersionUtils.toLong(getMinVersion()));
	}

	/**
	 * 测试获取数据库时间
	 */
	@Test
	void testDbTime() {
		// 预热一下
		DbClockUtils.currentTimeMillis(dataSource);

		// DbClockUtils.xxx，用于对比
		long time0 = System.currentTimeMillis();
		System.out.println(DbClockUtils.currentTimeMillis(dataSource));
		System.out.println(DbClockUtils.now(dataSource).getTime());
		System.out.println(DateUtils.toMilliseconds(DbClockUtils.now(dataSource)));

		System.out.println();
		System.out.println(System.currentTimeMillis() - time0);
		System.out.println();

		// DbUtils.xxx
		time0 = System.currentTimeMillis();
		System.out.println(DbUtils.currentTimeMillis(dataSource));
		Date now = DbUtils.now(dataSource);
		System.out.println(now.getTime());
		System.out.println(DateUtils.toMilliseconds(now));

		System.out.println();
		System.out.println(System.currentTimeMillis() - time0);
		System.out.println();


		// DbClockUtils.xxx，用于对比
		time0 = System.currentTimeMillis();
		System.out.println(DbClockUtils.currentTimeMillis(dataSource));
		System.out.println(DbClockUtils.now(dataSource).getTime());
		System.out.println(DateUtils.toMilliseconds(DbClockUtils.now(dataSource)));

		System.out.println();
		System.out.println(System.currentTimeMillis() - time0);
	}

	@Test
	void testClock() {
		long time = DbClockUtils.currentTimeMillis(dataSource);
		System.out.println(time);
		System.out.println(DateUtils.toMilliseconds(new Date(time)));
		Assertions.assertTrue(time > 0);

		Date now = DbClockUtils.now(dataSource);
		System.out.println(now.getTime());
		System.out.println(DateUtils.toMilliseconds(now));
		Assertions.assertTrue(now.getTime() > new Date().getTime() - 60 * 1000);
	}

	/**
	 * 测试数据库序列
	 */
	@Test
	void testDbSequence() {
		// setval
		try {
			long seqval = DbUtils.seqSetVal(dataSource, SEQ_NAME, 2);
			Assertions.assertEquals(1, seqval);
		} catch (NotSupportedException e) {
			e.printStackTrace();
		}

		// currval
		try {
			long seqval = DbUtils.seqCurrVal(dataSource, SEQ_NAME);
			Assertions.assertEquals(2, seqval);
			seqval = DbUtils.seqCurrVal(dataSource, SEQ_NAME);
			Assertions.assertEquals(2, seqval);
		} catch (NotSupportedException e) {
			e.printStackTrace();
		}

		// nextval
		try {
			long seqval = DbUtils.seqNextVal(dataSource, SEQ_NAME);
			Assertions.assertEquals(3, seqval);
			seqval = DbUtils.seqNextVal(dataSource, SEQ_NAME);
			Assertions.assertEquals(4, seqval);
		} catch (NotSupportedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 测试数据库序列的线程安全问题
	 */
	@Test
	void testDbSequenceThreadSafe() {
		int totalTimes = 1000, threadCount, times;

		// 1个线程
		threadCount = 1;
		times = totalTimes / threadCount;
		this.testDbSequenceThreadSafe(threadCount, times);

		// 2个线程
		threadCount = 2;
		times = totalTimes / threadCount;
		this.testDbSequenceThreadSafe(threadCount, times);

		// 8个线程
		threadCount = 8;
		times = totalTimes / threadCount;
		this.testDbSequenceThreadSafe(threadCount, times);

		// 10个线程
		threadCount = 10;
		times = totalTimes / threadCount;
		this.testDbSequenceThreadSafe(threadCount, times);

		// 20个线程
		threadCount = 20;
		times = totalTimes / threadCount;
		this.testDbSequenceThreadSafe(threadCount, times);

		// 50个线程
		threadCount = 50;
		times = totalTimes / threadCount;
		this.testDbSequenceThreadSafe(threadCount, times);

		// 100个线程
		threadCount = 100;
		times = totalTimes / threadCount;
		this.testDbSequenceThreadSafe(threadCount, times);
	}

	private void testDbSequenceThreadSafe(int threadCount, int times) {
		final Set<Long> valSet = Collections.synchronizedSet(new HashSet<>());

		// currval

		try {
			TestUtils.performanceTest(threadCount, times, () -> {
				long currVal = DbUtils.seqCurrVal(dataSource, SEQ_NAME);
				valSet.add(currVal);
				return "func_currval";
			});
			if (DbTypeConstants.ORACLE.equals(getDbType()) && threadCount > 1) {
				// Oracle每个连接，都必须先调用一次nextval，才能成功调用currval。hikari默认连接池最大数量是10，我测试时故意设置成了9，用来测试。
				// 并发会导致最终拿到的值大于连接池的数量
				Assertions.assertTrue(valSet.size() >= Math.min(threadCount, 9));
			} else {
				Assertions.assertEquals(1, valSet.size());
			}
		} catch (NotSupportedException e) {
			e.printStackTrace();
		}

		// nextval
		valSet.clear();
		try {
			TestUtils.performanceTest(threadCount, times, () -> {
				valSet.add(DbUtils.seqNextVal(dataSource, SEQ_NAME));
				return "func_nextval";
			});
			Assertions.assertEquals((threadCount + 1) * times + 1, valSet.size(), "数量不一致，nextval方法线程安全无法保证，请检查问题");
		} catch (NotSupportedException e) {
			e.printStackTrace();
		}

		// setval
		try {
			TestUtils.performanceTest(threadCount, times, () -> {
				valSet.add(DbUtils.seqSetVal(dataSource, SEQ_NAME, 2));
				return "func_setval";
			});
		} catch (NotSupportedException e) {
			e.printStackTrace();
		}
	}
}
