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

@Repository("kafkaRepository")
public class KafkaMessagesRepository {

    private final NamedParameterJdbcTemplate messageTemplate;

    @Autowired
    public KafkaMessagesRepository(@Qualifier("kafkaMessageJdbcTemplate") NamedParameterJdbcTemplate messageTemplate){
        this.messageTemplate = messageTemplate;
    }

    public List<ActivityDto> getMessagesWithBusinessKey(String businessKey){
        String sql = "select id, created, topic from kafka_messages where business_key = :businessKey";
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("businessKey", businessKey);
        return messageTemplate.query(sql, parameters, new KafkaMessageMapper());
    }

    private static class KafkaMessageMapper implements RowMapper<ActivityDto> {
        @Override
        public ActivityDto mapRow(ResultSet resultSet, int i) throws SQLException {
            return new ActivityDto(
                    ActivityType.KAFKA,
                    resultSet.getString("id"),
                    resultSet.getTimestamp("created"),
                    resultSet.getString("topic")
            );
        }
    }
}
