package tk.burdukowsky.es.config;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.sql.DataSource;

/**
 * Created by stanislav
 * On 15.04.17.
 */
@Configuration
@EnableWebMvc
@ComponentScan("tk.burdukowsky.es")
public class ContextConfiguration extends WebMvcConfigurerAdapter {

    @Bean(name = "dataSource")
    public DataSource dataSource() {
        final BasicDataSource ds = new BasicDataSource();
        ds.setDriverClassName("com.mysql.jdbc.Driver");
        ds.setUrl("jdbc:mysql://localhost:3306/es?useSSL=false");
        ds.setUsername("es_user");
        ds.setPassword("es_user");
        ds.setTestWhileIdle(true);
        ds.setValidationQuery("SELECT 1");
        return ds;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        if (!registry.hasMappingForPattern("/static/**")) {
            registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        }
    }
}
