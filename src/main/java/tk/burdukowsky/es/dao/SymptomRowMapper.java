package tk.burdukowsky.es.dao;

import org.springframework.jdbc.core.RowMapper;
import tk.burdukowsky.es.models.Symptom;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by stanislav
 * On 15.04.17.
 */
public class SymptomRowMapper implements RowMapper<Symptom> {
    @Override
    public Symptom mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Symptom(rs.getLong("id"),
                rs.getString("description"));
    }
}
