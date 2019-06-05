/*
 * Copyright 2002-2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.samples.petclinic.repository.jdbc;

import org.springframework.data.jdbc.core.OneToManyResultSetExtractor;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.samples.petclinic.model.Visit;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * {@link ResultSetExtractor} implementation by using the
 * {@link OneToManyResultSetExtractor} of Spring Data Core JDBC Extensions.
 */
public class JdbcVetVisitExtractor extends
    OneToManyResultSetExtractor<JdbcVet, Visit, Integer> {

    public JdbcVetVisitExtractor() {
        super(new JdbcVetRowMapper(), new JdbcVisitRowMapper());
    }

    @Override
    protected Integer mapPrimaryKey(ResultSet rs) throws SQLException {
        return rs.getInt("vets_id");
    }

    @Override
    protected Integer mapForeignKey(ResultSet rs) throws SQLException {
        if (rs.getObject("visits_vet_id") == null) {
            return null;
        } else {
            return rs.getInt("visits_vet_id");
        }
    }

    @Override
    protected void addChild(JdbcVet root, Visit child) {
        root.addVisit(child);
    }
}
