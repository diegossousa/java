package one.digitalinnovation.personapi.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;

@Configuration
public class DataConfigurator {

    @Value("${env.db.url}")
    private String urlDB;
    @Value("${env.db.username}")
    private String username;
    @Value("${env.db.password}")
    private String password;
    @Value("${env.db.driver}")
    private String driver;

//    @Bean
//    public DataSource dataPostgreSource() throws URISyntaxException {
//        URI dbUri = new URI(System.getenv("DATABASE_URL"));
//
//        String username = dbUri.getUserInfo().split(":")[0];
//        String password = dbUri.getUserInfo().split(":")[1];
//        String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath() + "?sslmode=require";
//
//        return DataSourceBuilder.create()
//                .url(dbUrl)
//                .username(username)
//                .password(password)
//                .build();
//    }
//
//    @Bean
//    public JpaVendorAdapter jpaPostgreAdapter() {
//        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
//        adapter.setDatabase(Database.POSTGRESQL);
//
//        adapter.setShowSql(true);
//        adapter.setGenerateDdl(true);
//        adapter.setDatabasePlatform("org.hibernate.dialect.PostgreSQLDialect");
//        adapter.setPrepareConnection(true);
//        return adapter;
//    }

    @Bean
    @Primary
    public DataSource getDataSource() {
        return DataSourceBuilder.create()
                .driverClassName(driver)
                .password(password)
                .url(urlDB)
                .username(username)
                .build();
    }

    @Bean
    public JpaVendorAdapter getVendorAdapter() {
        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
//        adapter.setShowSql(true);
        adapter.setPrepareConnection(true);
        adapter.setGenerateDdl(true);
        adapter.setDatabase(Database.MYSQL);
        return adapter;
    }
}
