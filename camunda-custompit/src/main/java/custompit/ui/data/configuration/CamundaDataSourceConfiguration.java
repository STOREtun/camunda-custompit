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
public class CamundaDataSourceConfiguration {

    @Primary
    @Bean(name = "camundaDataSource")
    @ConfigurationProperties("spring.datasource.camunda")
    public DataSource camundaDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "camundaJdbcTemplate")
    public NamedParameterJdbcTemplate camundaJdbcTemplate(
            @Qualifier("camundaDataSource") DataSource dataSource) {
        return new NamedParameterJdbcTemplate(dataSource);
    }

    @Primary
    @Bean(name = "camundaTransactionManager")
    public PlatformTransactionManager camundaTransactionManager(
            @Qualifier("camundaDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
}
