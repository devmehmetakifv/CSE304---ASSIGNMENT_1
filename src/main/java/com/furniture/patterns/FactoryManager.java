package com.furniture.patterns;

import com.furniture.factory.ArtDecoFurnitureFactory;
import com.furniture.factory.FurnitureFactory;
import com.furniture.factory.ModernFurnitureFactory;
import com.furniture.factory.VictorianFurnitureFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Singleton class that manages the furniture factories
 */
public class FactoryManager {
    // Singleton instance
    private static FactoryManager instance;
    
    // Map to store the available furniture factories
    private final Map<String, FurnitureFactory> factories;
    
    // Private constructor to prevent instantiation
    private FactoryManager() {
        factories = new HashMap<>();
        
        // Register the available furniture factories
        FurnitureFactory modernFactory = new ModernFurnitureFactory();
        FurnitureFactory victorianFactory = new VictorianFurnitureFactory();
        FurnitureFactory artDecoFactory = new ArtDecoFurnitureFactory();
        
        factories.put(modernFactory.getStyleName().toLowerCase(), modernFactory);
        factories.put(victorianFactory.getStyleName().toLowerCase(), victorianFactory);
        factories.put(artDecoFactory.getStyleName().toLowerCase(), artDecoFactory);
    }
    
    /**
     * Get the singleton instance of FactoryManager
     * @return The singleton instance
     */
    public static synchronized FactoryManager getInstance() {
        if (instance == null) {
            instance = new FactoryManager();
        }
        return instance;
    }
    
    /**
     * Get a furniture factory by style name
     * @param styleName The style name to look for
     * @return The corresponding furniture factory
     */
    public FurnitureFactory getFactory(String styleName) {
        return factories.get(styleName.toLowerCase());
    }
    
    /**
     * Get all available furniture factories
     * @return Map of style names to factories
     */
    public Map<String, FurnitureFactory> getAllFactories() {
        return new HashMap<>(factories);
    }
} 