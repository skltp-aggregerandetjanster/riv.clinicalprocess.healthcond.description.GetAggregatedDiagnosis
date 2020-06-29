package se.skltp.aggregatingservices.riv.clinicalprocess.healthcond.description.getaggregateddiagnosis.v2;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import riv.clinicalprocess.healthcond.description.getdiagnosis.v2.GetDiagnosisResponderInterface;
import riv.clinicalprocess.healthcond.description.getdiagnosis.v2.GetDiagnosisResponderService;
import se.skltp.aggregatingservices.config.TestProducerConfiguration;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix="getaggregateddiagnosis.v2.teststub")
public class ServiceConfiguration extends TestProducerConfiguration {

  public static final String SCHEMA_PATH = "/schemas/TD_CLINICALPROCESS_HEALTHCOND_DESCRIPTION_2.1/interactions/GetDiagnosisInteraction/GetDiagnosisInteraction_2.0_RIVTABP21.wsdl";

  public ServiceConfiguration() {
    setProducerAddress("http://localhost:8083/vp");
    setServiceClass(GetDiagnosisResponderInterface.class.getName());
    setServiceNamespace("urn:riv:clinicalprocess:healthcond:description:GetDiagnosisResponder:2");
    setPortName(GetDiagnosisResponderService.GetDiagnosisResponderPort.toString());
    setWsdlPath(SCHEMA_PATH);
    setTestDataGeneratorClass(ServiceTestDataGenerator.class.getName());
    setServiceTimeout(27000);
  }

}
