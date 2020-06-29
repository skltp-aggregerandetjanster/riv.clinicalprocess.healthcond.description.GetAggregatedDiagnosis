package se.skltp.aggregatingservices.riv.clinicalprocess.healthcond.description.getaggregateddiagnosis.v2;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"se.skltp.aggregatingservices"})
public class ServiceTestStubApplication {

  public static void main(String[] args) {
    new SpringApplicationBuilder(ServiceTestStubApplication.class).run(args);
  }
}
