package com.rahul.parkinglot;

import java.util.*;
import java.util.logging.Logger;

public class ParkingLot {
    int MAX_SIZE = 0;
    private class Car {
        String regNo;
      //  String color;
        public Car(String regNo/*, String color*/) {
            this.regNo = regNo;
            //this.color = color;
        }
    }
    // Available slots list
    ArrayList<Integer> availableSlotList;
    // Map of Slot, Car
    Map<String, Car> map1;
    // Map of RegNo, Slot
    Map<String, String> map2;
    // Map of Color, List of RegNo
    Map<String, ArrayList<String>> map3;


    public void createParkingLot(String lotCount) {
        try {
            this.MAX_SIZE = Integer.parseInt(lotCount);
        } catch (Exception e) {
            System.out.println("Invalid lot count");
            System.out.println();
        }
        this.availableSlotList = new ArrayList<Integer>() {};
        for (int i=1; i<= this.MAX_SIZE; i++) {
            availableSlotList.add(i);
        }
        this.map1 = new HashMap<String, Car>();
        this.map2 = new HashMap<String, String>();
        this.map3 = new HashMap<String, ArrayList<String>>();
        System.out.println("Created parking lot with " + lotCount + " slots");
        System.out.println();
    }
    public void park(String regNo/*, String color*/) {
    	//System.out.println("Parking requested for car regno :"+regNo);
        if (this.MAX_SIZE == 0) {
            System.out.println("Sorry, parking lot is not created");
            System.out.println();
        } else if (this.map1.size() == this.MAX_SIZE) {
            System.out.println("Sorry, parking lot is full");
            System.out.println();
        } else {
            Collections.sort(availableSlotList);
            String slot = availableSlotList.get(0).toString();
            Car car = new Car(regNo/*, color*/);
            this.map1.put(slot, car);
            this.map2.put(regNo, slot);
         /**   if (this.map3.containsKey(color)) {
                ArrayList<String> regNoList = this.map3.get(color);
                this.map3.remove(color);
                regNoList.add(regNo);
                this.map3.put(color, regNoList);
            } else {**/
                ArrayList<String> regNoList = new ArrayList<String>();
                regNoList.add(regNo);
                //this.map3.put(color, regNoList);
           /* }*/
            System.out.println("Allocated slot number: " + slot);
            System.out.println();
            availableSlotList.remove(0);
        }
    }
    public int calculate(int hours) {
    	int sum=10;
    	if(hours<=2) {
    	  		return sum;
    	}
    	else {
    		hours=hours-2;
    		sum=(sum+(hours*10));
    		return sum;
    	}
    	
    }
    public void leave(String regNo,String hours) {
        if (this.MAX_SIZE == 0) {
            System.out.println("Sorry, parking lot is not created");
            System.out.println();
        } else if (this.map1.size() > 0) {
          //  Car slotNoAllocatedTocar = this.map1.get(regNo);
            String slotNoAllocatedTocar = this.map2.get(regNo);
            if (slotNoAllocatedTocar != null) {
                this.map1.remove(slotNoAllocatedTocar);
                this.map2.remove(regNo);
               
                // Add the Lot No. back to available slot list.
                this.availableSlotList.add(Integer.parseInt(slotNoAllocatedTocar));
                System.out.println("Registration number " + regNo);
                System.out.println("with Slot number " + slotNoAllocatedTocar + " is free"+" with Charge "+calculate(Integer.parseInt(hours)));
                System.out.println();
            } else {
                System.out.println("Slot number " + slotNoAllocatedTocar + " is already empty");
                System.out.println();
            }
        } else {
            System.out.println("Parking lot is empty");
            System.out.println();
        }
    }
    public void status() {
        if (this.MAX_SIZE == 0) {
            System.out.println("Sorry, parking lot is not created");
            System.out.println();
        } else if (this.map1.size() > 0) {
            // Print the current status.
            System.out.println("Slot No.\tRegistration No.");
            Car car;
            for (int i = 1; i <= this.MAX_SIZE; i++) {
                String key = Integer.toString(i);
                if (this.map1.containsKey(key)) {
                    car = this.map1.get(key);
                    System.out.println(i + "\t" + car.regNo );
                }
            }
            System.out.println();
        } else {
            System.out.println("Parking lot is empty");
            System.out.println();
        }
    }
   
}
