package mx.dev.blank;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ScopedProxyMode;

@Configuration
@ComponentScan(basePackages = "mx.dev.blank.dao", scopedProxy = ScopedProxyMode.INTERFACES)
public class DAOTestConfig {
}
