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

import static se.skltp.agp.test.producer.TestProducerDb.TEST_RR_ID_ONE_HIT;

import javax.xml.ws.Holder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import riv.clinicalprocess.healthcond.description._2.PersonIdType;
import riv.clinicalprocess.healthcond.description.getdiagnosis.v2.GetDiagnosisResponderInterface;
import riv.clinicalprocess.healthcond.description.getdiagnosisresponder.v2.GetDiagnosisResponseType;
import riv.clinicalprocess.healthcond.description.getdiagnosisresponder.v2.GetDiagnosisType;
import se.skltp.aggregatingservices.riv.clinicalprocess.healthcond.description.getaggregateddiagnosis.GetAggregatedDiagnosisMuleServer;
import se.skltp.agp.test.consumer.AbstractTestConsumer;
import se.skltp.agp.test.consumer.SoapHeaderCxfInterceptor;
import se.skltp.agp.riv.interoperability.headers.v1.ProcessingStatusType;

public class GetAggregatedDiagnosisTestConsumer extends AbstractTestConsumer<GetDiagnosisResponderInterface> {

    private static final Logger log = LoggerFactory.getLogger(GetAggregatedDiagnosisTestConsumer.class);

    public static void main(String[] args) {
        String serviceAddress = GetAggregatedDiagnosisMuleServer.getAddress("SERVICE_INBOUND_URL");
        String personnummer = TEST_RR_ID_ONE_HIT;

        GetAggregatedDiagnosisTestConsumer consumer = new GetAggregatedDiagnosisTestConsumer(
                serviceAddress, SAMPLE_SENDER_ID, SAMPLE_ORIGINAL_CONSUMER_HSAID, SAMPLE_CORRELATION_ID);
        Holder<GetDiagnosisResponseType> responseHolder = new Holder<GetDiagnosisResponseType>();
        Holder<ProcessingStatusType> processingStatusHolder = new Holder<ProcessingStatusType>();

        consumer.callService("logical-adress", personnummer, processingStatusHolder, responseHolder);
        log.info("Returned #timeslots = " + responseHolder.value.getDiagnosis().size());
    }

    public GetAggregatedDiagnosisTestConsumer(String serviceAddress, String senderId, String originalConsumerHsaId, String correlationId) {
        // Setup a web service proxy for communication using HTTPS with Mutual Authentication
        super(GetDiagnosisResponderInterface.class, serviceAddress, senderId, originalConsumerHsaId, correlationId);
    }

    public void callService(String logicalAddress, String registeredResidentId, Holder<ProcessingStatusType> processingStatusHolder,
            Holder<GetDiagnosisResponseType> responseHolder) {

        log.debug("Calling GetDiagnosis-soap-service with Registered Resident Id = {}", registeredResidentId);

        GetDiagnosisType request = new GetDiagnosisType();
        PersonIdType personIdType = new PersonIdType();
        personIdType.setType("1.2.752.129.2.1.3.1");
        personIdType.setId(registeredResidentId);
        request.setPatientId(personIdType);

        GetDiagnosisResponseType response = _service.getDiagnosis(logicalAddress, request);
        responseHolder.value = response;
        processingStatusHolder.value = SoapHeaderCxfInterceptor.getLastFoundProcessingStatus();
    }
}