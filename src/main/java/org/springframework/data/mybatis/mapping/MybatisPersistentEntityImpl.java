/*
 *
 *   Copyright 2016 the original author or authors.
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 *
 */

package org.springframework.data.mybatis.mapping;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.data.mapping.model.BasicPersistentEntity;
import org.springframework.data.util.ParsingUtils;
import org.springframework.data.util.TypeInformation;
import org.springframework.util.StringUtils;

/**
 * @author Jarvis Song
 */
public class MybatisPersistentEntityImpl<T> extends BasicPersistentEntity<T, MybatisPersistentProperty>
		implements MybatisPersistentEntity<T> {

	private final MybatisMappingContext context;

	public MybatisPersistentEntityImpl(MybatisMappingContext context, TypeInformation<T> information) {
		super(information);
		this.context = context;
	}

	@Override
	public MybatisMappingContext getContext() {
		return this.context;
	}

	@Override
	public String getEntityName() {
		Entity entity = getType().getAnnotation(Entity.class);
		if (null != entity && StringUtils.hasText(entity.name())) {
			return entity.name();
		}
		return StringUtils.uncapitalize(getType().getSimpleName());
	}

	@Override
	public String getTableName() {
		String tableName;
		Table table = getType().getAnnotation(Table.class);
		if (null != table && StringUtils.hasText(table.name())) {
			tableName = table.name();
		}
		else {
			tableName = ParsingUtils.reconcatenateCamelCase(getType().getSimpleName(), "_");
		}
		if (null != table && StringUtils.hasText(table.schema())) {
			tableName = table.schema() + "." + tableName;
		}
		return tableName;
	}

}
