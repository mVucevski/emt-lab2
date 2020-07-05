package mk.ukim.finki.emt.lab.ordermanagement;

import mk.ukim.finki.emt.lab.sharedkernel.SharedConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@SpringBootApplication
@EnableJpaRepositories
@EntityScan
@Import(SharedConfiguration.class)
public class OrderManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderManagementApplication.class, args);
    }

    @Bean
    public javax.validation.Validator localValidatorFactoryBean() {
        return new LocalValidatorFactoryBean();
    }
}
