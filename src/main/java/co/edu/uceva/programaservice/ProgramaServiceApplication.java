package co.edu.uceva.programaservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = "co.edu.uceva.programaservice.domain.services")
public class ProgramaServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProgramaServiceApplication.class, args);
    }

}
