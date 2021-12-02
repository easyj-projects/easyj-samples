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
package icu.easyj.spring.boot.sample.db.oracle;

import icu.easyj.db.constant.DbTypeConstants;
import icu.easyj.db.util.DbClockUtils;
import icu.easyj.db.util.DbUtils;
import icu.easyj.spring.boot.sample.db.AbstractDbUtilsAndDbClockUtilsTest;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Oracle的 {@link DbUtils} 和 {@link DbClockUtils} 测试类
 *
 * @author wangliang181230
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("oracle")
@Ignore("需要连接对应类型的数据库")
public class OracleDbUtilsAndDbClockUtilsTest extends AbstractDbUtilsAndDbClockUtilsTest {

	@Override
	protected String getDbType() {
		return DbTypeConstants.ORACLE;
	}

	@Override
	protected String getMinVersion() {
		return "8.0.0";
	}
}
