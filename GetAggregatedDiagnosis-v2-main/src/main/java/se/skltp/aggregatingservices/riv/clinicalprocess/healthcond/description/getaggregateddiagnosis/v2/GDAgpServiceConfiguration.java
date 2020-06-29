package se.skltp.aggregatingservices.riv.clinicalprocess.healthcond.description.getaggregateddiagnosis.v2;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import riv.clinicalprocess.healthcond.description.getdiagnosis.v2.GetDiagnosisResponderInterface;
import riv.clinicalprocess.healthcond.description.getdiagnosis.v2.GetDiagnosisResponderService;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "getaggregateddiagnosis.v2")
public class GDAgpServiceConfiguration extends se.skltp.aggregatingservices.configuration.AgpServiceConfiguration {

  public static final String SCHEMA_PATH = "/schemas/TD_CLINICALPROCESS_HEALTHCOND_DESCRIPTION_2.1/interactions/GetDiagnosisInteraction/GetDiagnosisInteraction_2.0_RIVTABP21.wsdl";

  public GDAgpServiceConfiguration() {

    setServiceName("GetAggregatedDiagnosis-v2");
    setTargetNamespace("urn:riv:clinicalprocess:healthcond:description:GetDiagnosis:2:rivtabp21");

    // Set inbound defaults
    setInboundServiceURL("http://0.0.0.0:9008/GetAggregatedDiagnosis/service/v2");
    setInboundServiceWsdl(SCHEMA_PATH);
    setInboundServiceClass(GetDiagnosisResponderInterface.class.getName());
    setInboundPortName(GetDiagnosisResponderService.GetDiagnosisResponderPort.toString());

    // Set outbound defaults
    setOutboundServiceWsdl(SCHEMA_PATH);
    setOutboundServiceClass(getInboundServiceClass());
    setOutboundPortName(getInboundPortName());

    // FindContent
    setEiServiceDomain("riv:clinicalprocess:healthcond:description");
    setEiCategorization("dia");

    // TAK
    setTakContract("urn:riv:clinicalprocess:healthcond:description:GetDiagnosisResponder:2");

    // Set service factory
    setServiceFactoryClass(GDAgpServiceFactoryImpl.class.getName());
  }


}
