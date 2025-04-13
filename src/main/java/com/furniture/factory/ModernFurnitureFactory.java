package com.furniture.factory;

import com.furniture.model.Chair;
import com.furniture.model.ModernChair;
import com.furniture.model.ModernSofa;
import com.furniture.model.ModernTable;
import com.furniture.model.Sofa;
import com.furniture.model.Table;

/**
 * Concrete factory that creates Modern style furniture
 */
public class ModernFurnitureFactory implements FurnitureFactory {
    @Override
    public Chair createChair() {
        return new ModernChair();
    }

    @Override
    public Sofa createSofa() {
        return new ModernSofa();
    }

    @Override
    public Table createTable() {
        return new ModernTable();
    }
    
    @Override
    public String getStyleName() {
        return "Modern";
    }
} 