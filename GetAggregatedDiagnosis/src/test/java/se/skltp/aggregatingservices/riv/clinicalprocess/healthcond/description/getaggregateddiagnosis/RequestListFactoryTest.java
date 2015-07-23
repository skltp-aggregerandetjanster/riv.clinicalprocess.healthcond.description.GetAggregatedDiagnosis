package se.skltp.aggregatingservices.riv.clinicalprocess.healthcond.description.getaggregateddiagnosis;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class RequestListFactoryTest {
	
	private RequestListFactoryImpl testObject = new RequestListFactoryImpl();
	
	@Test
	public void isPartOf(){
		assertTrue(testObject.isPartOf("UNIT2", "UNIT2"));
		assertTrue(testObject.isPartOf(null, "UNIT2"));
		assertTrue(testObject.isPartOf("", "UNIT2"));
	}
	
	@Test
	public void isNotPartOf(){
		assertFalse(testObject.isPartOf("UNIT2", "UNIT3"));
		assertFalse(testObject.isPartOf("UNIT2", null));
	}
	
}
