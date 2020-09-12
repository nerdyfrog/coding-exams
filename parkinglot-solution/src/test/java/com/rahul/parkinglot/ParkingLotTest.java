package com.rahul.parkinglot;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;




import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;


public class ParkingLotTest {
    ParkingLot parkingLot = new ParkingLot();
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void cleanUpStreams() {
        System.setOut(null);
    }
    @Test
    public void createParkingLot() throws Exception {
        parkingLot.createParkingLot("6");
        assertEquals(6, parkingLot.MAX_SIZE);
        assertEquals(6, parkingLot.availableSlotList.size());
        assertTrue("createdparkinglotwith6slots".equalsIgnoreCase(outContent.toString().trim().replace(" ", "")));
    }

    @Test
    public void park() throws Exception {
    	
        parkingLot.park("KA-01-HH-1234");
        parkingLot.park("KA-01-HH-9999");
        assertEquals(("Sorry,parkinglotisnotcreated\n"+"\nSorry,parkinglotisnotcreated").replaceAll("\\n|\\r\\n", System.getProperty("line.separator")), outContent.toString().trim().replace(" ", ""));
        
    	parkingLot.createParkingLot("6");
        parkingLot.park("KA-01-HH-1234");
        parkingLot.park("KA-01-HH-9999");
        assertEquals(4, parkingLot.availableSlotList.size());
    }

    @Test
    public void leave() throws Exception {
        parkingLot.leave("2","5");
        assertEquals("Sorry,parkinglotisnotcreated", outContent.toString().trim().replace(" ", ""));
        parkingLot.createParkingLot("6");
        parkingLot.park("KA-01-HH-1234");
        parkingLot.park("KA-01-HH-9999");
        parkingLot.leave("KA-01-HH-9999","5");
        assertEquals(("Sorry,parkinglotisnotcreated\n" +
                "\n" +
                "Createdparkinglotwith6slots\n" +
                "\n" +
                "Allocatedslotnumber:1\n" +
                "\n" +
                "Allocatedslotnumber:2\n" +
                "\n" +
                "RegistrationnumberKA-01-HH-9999\r\n" + 
                "withSlotnumber2isfreewithCharge40").replaceAll("\\n|\\r\\n", System.getProperty("line.separator")), outContent.toString().trim().replace(" ", ""));
    }

    @Test
    public void status() throws Exception {
        parkingLot.status();
        assertEquals("Sorry,parkinglotisnotcreated", outContent.toString().trim().replace(" ", ""));
        parkingLot.createParkingLot("6");
        parkingLot.park("KA-01-HH-1234");
        parkingLot.park("KA-01-HH-9999");
        parkingLot.status();
        assertEquals(("Sorry,parkinglotisnotcreated\n" +
                "\n" +
                "Createdparkinglotwith6slots\n" +
                "\n" +
                "Allocatedslotnumber:1\n" +
                "\n" +
                "Allocatedslotnumber:2\n" +
                "\n" +
                "SlotNo.\tRegistrationNo.\n" +
                "1\tKA-01-HH-1234\n" +
                "2\tKA-01-HH-9999").replaceAll("\\n|\\r\\n", System.getProperty("line.separator")), outContent.toString().trim().replace(" ", ""));
    }

    

   @Test
   public void calculate() throws Exception{
	   int calFuncReturn;
	   calFuncReturn=parkingLot.calculate(2);
	   assertEquals(10, calFuncReturn);
	  calFuncReturn=parkingLot.calculate(4);
	   assertEquals(30, calFuncReturn);
	   calFuncReturn=parkingLot.calculate(5);
	   assertEquals(40, calFuncReturn);
   }

    
}