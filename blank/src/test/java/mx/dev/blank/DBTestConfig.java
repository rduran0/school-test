package mx.dev.blank;

import com.github.springtestdbunit.bean.DatabaseConfigBean;
import com.github.springtestdbunit.bean.DatabaseDataSourceConnectionFactoryBean;
import java.util.Properties;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import org.dbunit.ext.hsqldb.HsqldbDataTypeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.TransactionAwareDataSourceProxy;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@Import({DBTestConfig.HSQLDBConfig.class})
public class DBTestConfig {

  @Autowired Environment environment;

  @Bean
  public LocalContainerEntityManagerFactoryBean entityManagerFactory(
      final JpaVendorAdapter adapter, final DataSource dataSource) {
    final Properties props = new Properties();
    props.setProperty("hibernate.id.new_generator_mappings", "false");
    props.setProperty("hibernate.hbm2ddl.auto", "create-drop");
    props.setProperty("hibernate.format_sql", "true");
    props.setProperty("hibernate.connection.useUnicode", "true");
    props.setProperty("hibernate.connection.charSet", "utf8");
    props.setProperty("hibernate.show_sql", environment.getProperty("debug.sql", "false"));

    final LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
    em.setDataSource(dataSource);
    em.setJpaProperties(props);
    em.setJpaVendorAdapter(adapter);
    em.setPackagesToScan("mx.dev.blank");
    return em;
  }

  @Bean
  public JpaTransactionManager transactionManager(final EntityManagerFactory entityManagerFactory) {
    return new JpaTransactionManager(entityManagerFactory);
  }

  @Profile("default")
  @Configuration
  public static class HSQLDBConfig {

    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
      final HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
      adapter.setDatabase(Database.HSQL);
      adapter.setDatabasePlatform("org.hibernate.dialect.HSQLDialect");
      return adapter;
    }

    @Bean
    public DataSource dataSource() {
      return new TransactionAwareDataSourceProxy(
          new EmbeddedDatabaseBuilder()
              .setType(EmbeddedDatabaseType.HSQL)
              .addScript("classpath:hsqldb-mysql-compatibility.sql")
              .build());
    }

    @Bean
    public DatabaseDataSourceConnectionFactoryBean dbUnitDatabaseConnection(
        final DataSource dataSource) {
      final DatabaseConfigBean bean = new DatabaseConfigBean();
      bean.setDatatypeFactory(new HsqldbDataTypeFactory());

      final DatabaseDataSourceConnectionFactoryBean dbConnectionFactory =
          new DatabaseDataSourceConnectionFactoryBean(dataSource);
      dbConnectionFactory.setDatabaseConfig(bean);
      return dbConnectionFactory;
    }
  }
}
