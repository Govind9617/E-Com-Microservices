package org.nulldesignpat;

public class VehicalFactory {
    static Vehical getVehical(String veh){
        if("Car".equals(veh)){
            return new Car();
        }
        return new Nullvehical();
    }
}
