package tk.burdukowsky.es.dao;

import org.springframework.jdbc.core.RowMapper;
import tk.burdukowsky.es.models.DiseaseType;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by stanislav
 * On 16.04.17.
 */
public class DiseaseTypeRowMapper implements RowMapper<DiseaseType> {
    @Override
    public DiseaseType mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new DiseaseType(rs.getLong("id"),
                rs.getString("name"));
    }
}
