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

import java.util.Date;
import javax.sql.DataSource;

import icu.easyj.core.util.DateUtils;
import icu.easyj.core.util.version.VersionUtils;
import icu.easyj.db.util.DbClockUtils;
import icu.easyj.db.util.DbUtils;
import icu.easyj.db.util.PrimaryDataSourceHolder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * {@link DbUtils} 测试类
 *
 * @author wangliang181230
 */
public abstract class AbstractDbUtilsTest {

	protected final DataSource dataSource;


	protected AbstractDbUtilsTest(DataSource dataSource) {
		this.dataSource = dataSource;
	}


	protected abstract String getDbType();

	protected abstract String getMinVersion();


	@Test
	void testGetDbType() {
		Assertions.assertEquals(dataSource, PrimaryDataSourceHolder.get());

		String dbType = DbUtils.getDbType(dataSource);
		System.out.println(dbType);
		Assertions.assertEquals(this.getDbType(), dbType.toLowerCase());
	}

	@Test
	void testGetVersion() {
		String version = DbUtils.getDbVersion(dataSource);
		System.out.println(version);
		System.out.println(VersionUtils.toLong(version));
		Assertions.assertTrue(VersionUtils.toLong(version) > VersionUtils.toLong(getMinVersion()));
	}

	@Test
	void testNow() {
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
}
