package tk.burdukowsky.es.dao;

import org.springframework.jdbc.core.RowMapper;
import tk.burdukowsky.es.models.Disease;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Created by stanislav
 * On 18.04.17.
 */
public class DiseaseRowMapper implements RowMapper<Disease> {
    @Override
    public Disease mapRow(ResultSet rs, int rowNum) throws SQLException {
        Pattern pattern = Pattern.compile(",");
        List<Long> symptomsIds = pattern.splitAsStream(rs.getString("symptoms_ids"))
                .map(Long::valueOf)
                .collect(Collectors.toList());
        return new Disease(rs.getLong("id"),
                rs.getString("name"),
                rs.getString("text"),
                symptomsIds);
    }
}
