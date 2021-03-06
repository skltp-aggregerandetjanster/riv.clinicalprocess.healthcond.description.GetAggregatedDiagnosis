package se.skltp.aggregatingservices.riv.clinicalprocess.healthcond.description.getaggregateddiagnosis

trait CommonParameters {
  val serviceName:String     = "Diagnosis"
  val urn:String             = "urn:riv:clinicalprocess:healthcond:description:GetDiagnosisResponder:2"
  val responseElement:String = "GetDiagnosisResponse"
  val responseItem:String    = "diagnosis"
  var baseUrl:String         = if (System.getProperty("baseUrl") != null && !System.getProperty("baseUrl").isEmpty()) {
                                   System.getProperty("baseUrl")
                               } else {
                                   "http://33.33.33.33:8081/GetAggregatedDiagnosis/service/v2"
                               }
}
