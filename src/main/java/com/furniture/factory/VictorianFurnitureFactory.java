package com.furniture.factory;

import com.furniture.model.Chair;
import com.furniture.model.Sofa;
import com.furniture.model.Table;
import com.furniture.model.VictorianChair;
import com.furniture.model.VictorianSofa;
import com.furniture.model.VictorianTable;

/**
 * Concrete factory that creates Victorian style furniture
 */
public class VictorianFurnitureFactory implements FurnitureFactory {
    @Override
    public Chair createChair() {
        return new VictorianChair();
    }

    @Override
    public Sofa createSofa() {
        return new VictorianSofa();
    }

    @Override
    public Table createTable() {
        return new VictorianTable();
    }
    
    @Override
    public String getStyleName() {
        return "Victorian";
    }
} 