package com.furniture.factory;

import com.furniture.model.Chair;
import com.furniture.model.Sofa;
import com.furniture.model.Table;

/**
 * Abstract Factory interface for creating a family of related furniture products
 */
public interface FurnitureFactory {
    Chair createChair();
    Sofa createSofa();
    Table createTable();
    String getStyleName();
} 