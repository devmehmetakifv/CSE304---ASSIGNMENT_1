package com.furniture.model;

/**
 * Chair interface that extends Furniture
 */
public interface Chair extends Furniture {
    int getNumberOfLegs();
    boolean hasArmrests();
} 