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
import icu.easyj.db.util.DbClockUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * {@link DbClockUtils} 测试类
 *
 * @author wangliang181230
 */
public abstract class AbstractDbClockUtilsTest {

	protected final DataSource dataSource;


	protected AbstractDbClockUtilsTest(DataSource dataSource) {
		this.dataSource = dataSource;
	}


	@Test
	void test() {
		long time = DbClockUtils.currentTimeMillis(dataSource);
		System.out.println(time);
		System.out.println(DateUtils.toMilliseconds(new Date(time)));
		Assertions.assertTrue(time > 0);

		Date now = DbClockUtils.now(dataSource);
		System.out.println(now.getTime());
		System.out.println(DateUtils.toMilliseconds(now));
		Assertions.assertTrue(now.getTime() > new Date().getTime() - 60 * 1000);
	}
}
