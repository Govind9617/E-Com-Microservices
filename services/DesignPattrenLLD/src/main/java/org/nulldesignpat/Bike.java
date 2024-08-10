package org.nulldesignpat;

public class Bike implements Vehical{
    @Override
    public int tankCapacity() {
        return 20;
    }

    @Override
    public int wheelSize() {
        return 20;
    }
}
