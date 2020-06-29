package se.skltp.aggregatingservices.riv.clinicalprocess.healthcond.description.getaggregateddiagnosis.v2;

import java.util.List;
import lombok.extern.log4j.Log4j2;
import riv.clinicalprocess.healthcond.description.getdiagnosisresponder.v2.GetDiagnosisResponseType;
import riv.clinicalprocess.healthcond.description.getdiagnosisresponder.v2.GetDiagnosisType;
import se.skltp.aggregatingservices.AgServiceFactoryBase;

@Log4j2
public class GDAgpServiceFactoryImpl extends
    AgServiceFactoryBase<GetDiagnosisType, GetDiagnosisResponseType> {

  @Override
  public String getPatientId(GetDiagnosisType queryObject) {
    return queryObject.getPatientId().getId();
  }

  @Override
  public String getSourceSystemHsaId(GetDiagnosisType queryObject) {
    return queryObject.getSourceSystemHSAId();
  }

  @Override
  public GetDiagnosisResponseType aggregateResponse(List<GetDiagnosisResponseType> aggregatedResponseList) {

    GetDiagnosisResponseType aggregatedResponse = new GetDiagnosisResponseType();

    for (Object object : aggregatedResponseList) {
      GetDiagnosisResponseType response = (GetDiagnosisResponseType) object;
      aggregatedResponse.getDiagnosis().addAll(response.getDiagnosis());
    }

    log.info("Returning {} aggregated diagnosis v2", aggregatedResponse.getDiagnosis().size());

    return aggregatedResponse;
  }
}

