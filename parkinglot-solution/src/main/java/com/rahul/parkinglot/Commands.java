package com.rahul.parkinglot;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.HashMap;

public class Commands {
    public Map<String, Method> commandsMap;

    public Commands() {
        commandsMap = new HashMap<String, Method>();
        try {
            populateCommandsHashMap();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
    private void populateCommandsHashMap() throws NoSuchMethodException {
        commandsMap.put("create_parking_lot", ParkingLot.class.getMethod("createParkingLot", String.class));
        commandsMap.put("park", ParkingLot.class.getMethod("park", String.class));
        commandsMap.put("leave", ParkingLot.class.getMethod("leave", String.class,String.class));
        commandsMap.put("status", ParkingLot.class.getMethod("status"));
        //commandsMap.put("status", ParkingLot.class.getMethod("status")); 
    }
}
