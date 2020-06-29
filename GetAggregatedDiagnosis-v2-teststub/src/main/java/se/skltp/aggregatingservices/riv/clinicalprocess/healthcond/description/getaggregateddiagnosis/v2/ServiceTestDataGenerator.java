package se.skltp.aggregatingservices.riv.clinicalprocess.healthcond.description.getaggregateddiagnosis.v2;

import lombok.extern.log4j.Log4j2;
import org.apache.cxf.message.MessageContentsList;
import org.springframework.stereotype.Service;
import riv.clinicalprocess.healthcond.description._2.DiagnosisBodyType;
import riv.clinicalprocess.healthcond.description._2.DiagnosisType;
import riv.clinicalprocess.healthcond.description._2.PatientSummaryHeaderType;
import riv.clinicalprocess.healthcond.description._2.PersonIdType;
import riv.clinicalprocess.healthcond.description.getdiagnosisresponder.v2.GetDiagnosisResponseType;
import riv.clinicalprocess.healthcond.description.getdiagnosisresponder.v2.GetDiagnosisType;
import se.skltp.aggregatingservices.data.TestDataGenerator;

@Log4j2
@Service
public class ServiceTestDataGenerator extends TestDataGenerator {

  @Override
  public String getPatientId(MessageContentsList messageContentsList) {
    GetDiagnosisType request = (GetDiagnosisType) messageContentsList.get(1);
    return request.getPatientId().getId();
  }

  @Override
  public Object createResponse(Object... responseItems) {
    log.info("Creating a response with {} items", responseItems.length);
    GetDiagnosisResponseType response = new GetDiagnosisResponseType();
		for (int i = 0; i < responseItems.length; i++) {
			response.getDiagnosis().add((DiagnosisType) responseItems[i]);
		}

    log.info("response.toString:" + response.toString());

    return response;
  }

  @Override
  public Object createResponseItem(String logicalAddress, String registeredResidentId, String businessObjectId, String time) {
    log.debug("Created ResponseItem for logical-address {}, registeredResidentId {} and businessObjectId {}",
        new Object[]{logicalAddress, registeredResidentId, businessObjectId});

		DiagnosisType response = new DiagnosisType();

		DiagnosisBodyType bodyType = new DiagnosisBodyType();
		response.setDiagnosisBody(bodyType);

		PatientSummaryHeaderType headerType = new PatientSummaryHeaderType();

		PersonIdType personIdType = new PersonIdType();
		personIdType.setId(registeredResidentId);
		personIdType.setType("1.2.752.129.2.1.3.1");

		headerType.setSourceSystemHSAId(logicalAddress);

		headerType.setPatientId(personIdType);
		response.setDiagnosisHeader(headerType);

    return response;
  }

  public Object createRequest(String patientId, String sourceSystemHSAId) {
    GetDiagnosisType outcomeType = new GetDiagnosisType();

		outcomeType.setSourceSystemHSAId(sourceSystemHSAId);
		PersonIdType personIdType = new PersonIdType();
		personIdType.setId(patientId);
		personIdType.setType("1.2.752.129.2.1.3.1");
		outcomeType.setPatientId(personIdType);

    return outcomeType;
  }
}
