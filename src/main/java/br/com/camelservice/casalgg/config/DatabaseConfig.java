package br.com.camelservice.casalgg.config;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import javax.sql.DataSource;
import org.springframework.boot.jdbc.DataSourceBuilder;

@Configuration
public class DatabaseConfig {

    @Autowired
    private CamelContext camelContext;

    @Bean
    public DataSource dataSource() {
        return DataSourceBuilder.create()
                .url("jdbc:mysql://localhost:3306/financeschema")
                .username("root")
                .password("root")
                .driverClassName("com.mysql.cj.jdbc.Driver")
                .build();
    }

    @Bean
    public RouteBuilder databaseRoute(DataSource dataSource) {
        return new RouteBuilder() {
            @Override
            public void configure() throws Exception {
                camelContext.getRegistry().bind("myDataSource", dataSource);
            }
        };
    }
}