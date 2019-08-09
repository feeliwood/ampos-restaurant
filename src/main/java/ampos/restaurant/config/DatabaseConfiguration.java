package ampos.restaurant.config;

import ampos.restaurant.repository.impl.NaturalRepositoryImpl;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories("ampos.restaurant.repository")
@EnableTransactionManagement
public class DatabaseConfiguration {
}
