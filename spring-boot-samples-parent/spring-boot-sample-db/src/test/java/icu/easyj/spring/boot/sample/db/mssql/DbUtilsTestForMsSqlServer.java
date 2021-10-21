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
package icu.easyj.spring.boot.sample.db.mssql;

import javax.sql.DataSource;

import icu.easyj.core.util.VersionUtils;
import icu.easyj.db.constant.DbTypeConstants;
import icu.easyj.db.util.DbUtils;
import icu.easyj.db.util.PrimaryDataSourceHolder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

/**
 * {@link DbUtils} 测试类
 *
 * @author wangliang181230
 */
@SpringBootTest
@ActiveProfiles("mssqlserver")
@Disabled("请手动运行该测试")
class DbUtilsTestForMsSqlServer {

	@Autowired
	private DataSource dataSource;

	@Test
	void testGetDbType() {
		Assertions.assertEquals(dataSource, PrimaryDataSourceHolder.get());

		String dbType = DbUtils.getDbType(dataSource);
		System.out.println(dbType);
		Assertions.assertEquals(DbTypeConstants.MS_SQL_SERVER, dbType.toLowerCase());
	}

	@Test
	void testGetVersion() {
		String version = DbUtils.getDbVersion(dataSource);
		System.out.println(version);
		System.out.println(VersionUtils.toLong(version));
		Assertions.assertTrue(VersionUtils.toLong(version) > VersionUtils.toLong("5.0.0"));
	}
}
