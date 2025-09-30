package custompit.ui.data.repository;

import custompit.ui.ActivityType;
import custompit.ui.dto.ActivityDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository("camundaRepository")
public class CamundaRepository {

    private final NamedParameterJdbcTemplate camundaTemplate;

    @Autowired
    public CamundaRepository(@Qualifier("camundaJdbcTemplate") NamedParameterJdbcTemplate camundaTemplate){
        this.camundaTemplate = camundaTemplate;
    }

    public List<ActivityDto> getAllActivitiesWithBusinessKey(String businessKey){
        List<ActivityDto> activeExecution = getActiveProcessInstanceWithBusinessKey(businessKey);
        List<ActivityDto> historicExecutions = getHistoricActivitiesWithBusinessKey(businessKey);
        activeExecution.addAll(historicExecutions);

        return activeExecution;
    }

    private List<ActivityDto> getActiveProcessInstanceWithBusinessKey(String businessKey){
        String query = "SELECT " +
                "exe.proc_inst_id_, " +
                "def.key_, " +
                "hist.start_time_ " +
                "FROM act_ru_execution exe " +
                "LEFT JOIN act_hi_procinst hist on exe.proc_inst_id_ = hist.proc_inst_id_ " +
                "join act_re_procdef def on exe.proc_def_id_ = def.id_ " +
                "WHERE exe.business_key_ = :businessKey";

        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("businessKey", businessKey);
        return camundaTemplate.query(query, parameters, new CamundaActivityMapper());
    }

    private List<ActivityDto> getHistoricActivitiesWithBusinessKey(String businessKey){
        String query = "select hist.id_ as proc_inst_id_, " +
                "hist.proc_def_key_ as key_, " +
                "hist.start_time_ " +
                "from act_hi_procinst hist " +
                "where business_key_ = :businessKey";

        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("businessKey", businessKey);
        return camundaTemplate.query(query, parameters, new CamundaActivityMapper());
    }

    private static class CamundaActivityMapper implements RowMapper<ActivityDto> {
        @Override
        public ActivityDto mapRow(ResultSet resultSet, int i) throws SQLException {
            return new ActivityDto(
                    ActivityType.CAMUNDA,
                    resultSet.getString("proc_inst_id_"),
                    resultSet.getTimestamp("start_time_"),
                    resultSet.getString("key_")
            );
        }
    }
}
