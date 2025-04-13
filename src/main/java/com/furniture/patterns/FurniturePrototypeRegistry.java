package com.furniture.patterns;

import com.furniture.factory.FurnitureFactory;
import com.furniture.model.Chair;
import com.furniture.model.Furniture;
import com.furniture.model.Sofa;
import com.furniture.model.Table;

import java.util.HashMap;
import java.util.Map;

/**
 * Registry for furniture prototypes - implements the Prototype pattern
 */
public class FurniturePrototypeRegistry {
    // Singleton instance
    private static FurniturePrototypeRegistry instance;
    
    // Map to store the furniture prototypes
    private final Map<String, Furniture> prototypes;
    
    // Private constructor to prevent instantiation
    private FurniturePrototypeRegistry() {
        prototypes = new HashMap<>();
        initializePrototypes();
    }
    
    /**
     * Initialize the prototypes from all factories
     */
    private void initializePrototypes() {
        // Get all factories from the factory manager
        Map<String, FurnitureFactory> factories = FactoryManager.getInstance().getAllFactories();
        
        // Create prototypes for each style and furniture type
        for (Map.Entry<String, FurnitureFactory> entry : factories.entrySet()) {
            String style = entry.getKey();
            FurnitureFactory factory = entry.getValue();
            
            // Create and register chair prototype
            Chair chair = factory.createChair();
            register(style + "_chair", chair);
            
            // Create and register sofa prototype
            Sofa sofa = factory.createSofa();
            register(style + "_sofa", sofa);
            
            // Create and register table prototype
            Table table = factory.createTable();
            register(style + "_table", table);
        }
    }
    
    /**
     * Get the singleton instance
     * @return The singleton instance
     */
    public static synchronized FurniturePrototypeRegistry getInstance() {
        if (instance == null) {
            instance = new FurniturePrototypeRegistry();
        }
        return instance;
    }
    
    /**
     * Register a furniture prototype
     * @param key The key to register the prototype with
     * @param prototype The furniture prototype to register
     */
    public void register(String key, Furniture prototype) {
        prototypes.put(key.toLowerCase(), prototype);
    }
    
    /**
     * Get a clone of a registered prototype
     * @param key The key of the prototype to clone
     * @return A clone of the prototype
     */
    public Furniture getClone(String key) {
        Furniture prototype = prototypes.get(key.toLowerCase());
        if (prototype == null) {
            return null;
        }
        
        return prototype.clone();
    }
    
    /**
     * Check if a prototype exists
     * @param key The key to check
     * @return True if the prototype exists, false otherwise
     */
    public boolean containsKey(String key) {
        return prototypes.containsKey(key.toLowerCase());
    }
} 