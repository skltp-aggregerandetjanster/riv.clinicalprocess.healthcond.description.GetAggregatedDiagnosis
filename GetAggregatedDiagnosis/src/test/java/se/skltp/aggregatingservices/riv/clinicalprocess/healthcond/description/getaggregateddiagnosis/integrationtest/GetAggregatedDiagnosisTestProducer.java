/**
 * Copyright (c) 2014 Inera AB, <http://inera.se/>
 *
 * This file is part of SKLTP.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301  USA
 */
package se.skltp.aggregatingservices.riv.clinicalprocess.healthcond.description.getaggregateddiagnosis.integrationtest;

import javax.jws.WebService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import riv.clinicalprocess.healthcond.description.getdiagnosis.v2.GetDiagnosisResponderInterface;
import riv.clinicalprocess.healthcond.description.getdiagnosisresponder.v2.GetDiagnosisResponseType;
import riv.clinicalprocess.healthcond.description.getdiagnosisresponder.v2.GetDiagnosisType;
import se.skltp.agp.test.producer.TestProducerDb;

@WebService(serviceName = "GetDiagnosisResponderService", 
               portName = "GetDiagnosisResponderPort", 
        targetNamespace = "urn:riv:clinicalprocess:healthcond:description:GetDiagnosis:2:rivtabp21", 
                   name = "GetDiagnosisInteraction")
public class GetAggregatedDiagnosisTestProducer implements GetDiagnosisResponderInterface {

    private static final Logger log = LoggerFactory.getLogger(GetAggregatedDiagnosisTestProducer.class);

    private TestProducerDb testDb;

    public void setTestDb(TestProducerDb testDb) {
        this.testDb = testDb;
    }

    @Override
    public GetDiagnosisResponseType getDiagnosis(String logicalAddress, GetDiagnosisType request) {
        log.info("### Virtual service for GetDiagnosis call the source system with logical address: {} and patientId: {}", logicalAddress, request.getPatientId().getId());

        GetDiagnosisResponseType response = (GetDiagnosisResponseType) testDb.processRequest(logicalAddress, request.getPatientId().getId());
        if (response == null) {
            // Return an empty response object instead of null if nothing is found
            response = new GetDiagnosisResponseType();
        }

        log.info("### Virtual service got {} bookings in the reply from the source system with logical address: {} and patientId: {}", new Object[] {
                response.getDiagnosis().size(), logicalAddress, request.getPatientId().getId() });
        return response;
    }
}