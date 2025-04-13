package com.furniture.model;

import java.awt.Image;

/**
 * Base interface for all furniture items
 */
public interface Furniture extends Cloneable {
    String getName();
    String getDescription();
    double getPrice();
    Image getImage();
    Furniture clone();
} 