package se.skltp.aggregatingservices.riv.clinicalprocess.healthcond.description.getaggregateddiagnosis.v2;

import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import riv.clinicalprocess.healthcond.description.getdiagnosisresponder.v2.GetDiagnosisResponseType;
import se.skltp.aggregatingservices.api.AgpServiceFactory;
import se.skltp.aggregatingservices.tests.CreateFindContentTest;
import se.skltp.aggregatingservices.data.TestDataGenerator;

@RunWith(SpringJUnit4ClassRunner.class)
public class GDCreateRequestListTest extends CreateFindContentTest {

  private static GDAgpServiceConfiguration configuration = new GDAgpServiceConfiguration();
  private static AgpServiceFactory<GetDiagnosisResponseType> agpServiceFactory = new GDAgpServiceFactoryImpl();
  private static ServiceTestDataGenerator testDataGenerator = new ServiceTestDataGenerator();


  public GDCreateRequestListTest() {
    super(testDataGenerator, agpServiceFactory, configuration);
  }

  @BeforeClass
  public static void before() {
    configuration = new GDAgpServiceConfiguration();
    agpServiceFactory = new GDAgpServiceFactoryImpl();
    agpServiceFactory.setAgpServiceConfiguration(configuration);
  }
}