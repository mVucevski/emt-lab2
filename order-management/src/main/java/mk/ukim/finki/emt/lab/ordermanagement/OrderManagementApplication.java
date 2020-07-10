package mk.ukim.finki.emt.lab.ordermanagement;

import mk.ukim.finki.emt.lab.sharedkernel.SharedConfiguration;
import mk.ukim.finki.emt.lab.sharedkernel.infra.eventlog.RemoteEventLogService;
import mk.ukim.finki.emt.lab.sharedkernel.port.client.RemoteEventLogServiceClient;
import org.springframework.beans.factory.annotation.Value;
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

    @Bean
    public RemoteEventLogService orderEvents(@Value("http://localhost:8081") String serverUrl,
                                             @Value("5000") int connectTimeout,
                                             @Value("5000") int readTimeout){

        return new RemoteEventLogServiceClient(serverUrl, connectTimeout, readTimeout);
    }
}
