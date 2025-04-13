package com.furniture.factory;

import com.furniture.model.ArtDecoChair;
import com.furniture.model.ArtDecoSofa;
import com.furniture.model.ArtDecoTable;
import com.furniture.model.Chair;
import com.furniture.model.Sofa;
import com.furniture.model.Table;

/**
 * Concrete factory that creates Art Deco style furniture
 */
public class ArtDecoFurnitureFactory implements FurnitureFactory {
    @Override
    public Chair createChair() {
        return new ArtDecoChair();
    }

    @Override
    public Sofa createSofa() {
        return new ArtDecoSofa();
    }

    @Override
    public Table createTable() {
        return new ArtDecoTable();
    }
    
    @Override
    public String getStyleName() {
        return "Art Deco";
    }
} 