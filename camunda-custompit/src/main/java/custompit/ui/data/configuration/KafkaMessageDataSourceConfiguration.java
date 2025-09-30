package custompit.ui.data.configuration;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
public class KafkaMessageDataSourceConfiguration {

    @Bean(name = "kafkaMessageDataSource")
    @ConfigurationProperties("spring.datasource.kafka")
    public DataSource kafkaMessageDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "kafkaMessageJdbcTemplate")
    public NamedParameterJdbcTemplate kafkaJdbcTemplate(
            @Qualifier("kafkaMessageDataSource") DataSource dataSource) {
        return new NamedParameterJdbcTemplate(dataSource);
    }

    @Bean(name = "kafkaTransactionManager")
    public PlatformTransactionManager kafkaTransactionManager(
            @Qualifier("kafkaMessageDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

}
