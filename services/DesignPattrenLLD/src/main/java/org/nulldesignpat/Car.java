package org.nulldesignpat;

public class Car implements Vehical{
    @Override
    public int tankCapacity() {
        return 10;
    }

    @Override
    public int wheelSize() {
        return 10;
    }
}
