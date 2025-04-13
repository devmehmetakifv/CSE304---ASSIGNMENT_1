# Furniture Production System Report

## Introduction

This document provides a detailed report on the implementation of the Furniture Production system, focusing on the application of design patterns as required by the assignment. The project demonstrates the use of the Abstract Factory, Singleton, and Prototype patterns in a GUI-based Java application.

## Design Patterns Implementation

### 1. Abstract Factory Pattern

The Abstract Factory pattern provides an interface for creating families of related objects without specifying their concrete classes. It promotes consistency among product families and helps with the creation of complex object structures.

#### Code Implementation

The Abstract Factory pattern is implemented with the following key components:

1. **Abstract Product Interfaces**:

```java
// Base product interface
public interface Furniture extends Cloneable {
    String getName();
    String getDescription();
    double getPrice();
    Image getImage();
    Furniture clone();
}

// Specific product interfaces
public interface Chair extends Furniture {
    int getNumberOfLegs();
    boolean hasArmrests();
}

public interface Sofa extends Furniture {
    int getNumberOfSeats();
    boolean isConvertible();
}

public interface Table extends Furniture {
    int getNumberOfLegs();
    double getSurfaceArea();
}
```

2. **Abstract Factory Interface**:

```java
public interface FurnitureFactory {
    Chair createChair();
    Sofa createSofa();
    Table createTable();
    String getStyleName();
}
```

3. **Concrete Factories**:

```java
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

// Similar implementations for VictorianFurnitureFactory and ArtDecoFurnitureFactory
```

4. **Concrete Products**:

```java
public class ModernChair implements Chair {
    private String name;
    private String description;
    private double price;
    private Image image;
    private int numberOfLegs;
    private boolean hasArmrests;
    
    public ModernChair() {
        this.name = "Modern Chair";
        this.description = "Minimalist chair with clean lines and metal frame";
        this.price = 399.99;
        this.numberOfLegs = 4;
        this.hasArmrests = false;
        
        // Initialize image...
    }
    
    // Implement interface methods...
}

// Similar implementations for other concrete products
```

5. **Client Code Using the Factory**:

```java
private void updateFurnitureDisplay() {
    if (currentFactory == null || currentFurnitureType == null) {
        furniturePanel.clear();
        return;
    }
    
    Furniture furniture = null;
    
    switch (currentFurnitureType) {
        case "chair":
            furniture = currentFactory.createChair();
            break;
        case "sofa":
            furniture = currentFactory.createSofa();
            break;
        case "table":
            furniture = currentFactory.createTable();
            break;
    }
    
    furniturePanel.displayFurniture(furniture);
}
```

#### UML Class Diagram for the Abstract Factory Pattern

```
+-------------------+      +-------------------+      +-------------------+
|  FurnitureFactory |<---- | ModernFurniture   |      |     Furniture     |
|-------------------|      | Factory           |      |-------------------|
| createChair()     |      |-------------------|      | getName()         |
| createSofa()      |      | createChair()     |      | getDescription()  |
| createTable()     |      | createSofa()      |      | getPrice()        |
| getStyleName()    |      | createTable()     |      | getImage()        |
+-------------------+      | getStyleName()    |      | clone()           |
        ^                  +-------------------+      +-------------------+
        |                                                      ^
        |                                                      |
+-------+-------+      +-------------------+     +------------+------------+
| VictorianFurni|      | ArtDecoFurniture  |     |                         |
| tureFactory   |      | Factory           |     |                         |
|---------------|      |-------------------|     |                         |
| createChair() |      | createChair()     |  +------+  +------+  +------+
| createSofa()  |      | createSofa()      |  | Chair |  | Sofa |  | Table |
| createTable() |      | createTable()     |  +------+  +------+  +------+
| getStyleName()|      | getStyleName()    |     ^         ^         ^
+---------------+      +-------------------+     |         |         |
                                                 |         |         |
                                            Implemented by concrete classes
                                            for each style (Modern, Victorian, ArtDeco)
```

#### Analysis of the Abstract Factory Implementation

The Abstract Factory pattern in this application provides several benefits:

1. **Style Consistency**: Each factory ensures that all furniture items it creates follow the same style (Modern, Victorian, or Art Deco).

2. **Encapsulation**: The concrete creation logic is hidden within the concrete factories, making it easy to change or extend without affecting client code.

3. **Product Family Cohesion**: Related products (chairs, sofas, tables) are grouped together, enforcing the creation of compatible products.

4. **Easy Addition of New Styles**: To add a new style, we simply create a new concrete factory and associated concrete products without changing existing code.

### 2. Singleton Pattern

The Singleton pattern ensures that a class has only one instance and provides a global point of access to it. This pattern is useful when exactly one object is needed to coordinate actions across the system.

#### Code Implementation

The Singleton pattern is implemented in two classes:

1. **FactoryManager**:

```java
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
    
    // Other methods...
}
```

2. **FurniturePrototypeRegistry**:

```java
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
     * Get the singleton instance
     * @return The singleton instance
     */
    public static synchronized FurniturePrototypeRegistry getInstance() {
        if (instance == null) {
            instance = new FurniturePrototypeRegistry();
        }
        return instance;
    }
    
    // Other methods...
}
```

#### UML Class Diagram for the Singleton Pattern

```
+----------------------+
|   FactoryManager     |
|----------------------|
| -instance: FactoryManager [static]
| -factories: Map<String, FurnitureFactory>
|----------------------|
| -FactoryManager()    |
| +getInstance(): FactoryManager [static]
| +getFactory(String): FurnitureFactory
| +getAllFactories(): Map<String, FurnitureFactory>
+----------------------+

+-------------------------------+
|   FurniturePrototypeRegistry  |
|-------------------------------|
| -instance: FurniturePrototypeRegistry [static]
| -prototypes: Map<String, Furniture>
|-------------------------------|
| -FurniturePrototypeRegistry() |
| +getInstance(): FurniturePrototypeRegistry [static]
| -initializePrototypes(): void
| +register(String, Furniture): void
| +getClone(String): Furniture
| +containsKey(String): boolean
+-------------------------------+
```

#### Analysis of the Singleton Implementation

The Singleton pattern in this application provides several benefits:

1. **Global Access**: Both the `FactoryManager` and the `FurniturePrototypeRegistry` provide a global access point to their respective functionalities.

2. **Single Instance**: Ensures that there is only one instance of each manager, preventing inconsistencies in the system.

3. **Lazy Initialization**: The `getInstance()` method creates the instance only when it's first needed, improving performance.

4. **Thread Safety**: The `synchronized` keyword in the `getInstance()` method ensures thread safety.

### 3. Prototype Pattern

The Prototype pattern allows creating new objects by copying existing objects (prototypes) instead of creating them from scratch. This can improve performance and reduce the complexity of object creation.

#### Code Implementation

The Prototype pattern is implemented with the following key components:

1. **Cloneable Interface Extension**:

```java
public interface Furniture extends Cloneable {
    // Other methods...
    Furniture clone();
}
```

2. **Clone Implementation in Concrete Products**:

```java
public class ModernChair implements Chair {
    // Other fields and methods...
    
    @Override
    public Furniture clone() {
        try {
            return (Furniture) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
    }
}

// Similar implementations for other concrete products
```

3. **Prototype Registry**:

```java
public class FurniturePrototypeRegistry {
    // Singleton implementation...
    
    private final Map<String, Furniture> prototypes;
    
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
    
    public void register(String key, Furniture prototype) {
        prototypes.put(key.toLowerCase(), prototype);
    }
    
    public Furniture getClone(String key) {
        Furniture prototype = prototypes.get(key.toLowerCase());
        if (prototype == null) {
            return null;
        }
        
        return prototype.clone();
    }
    
    // Other methods...
}
```

4. **Client Code Using the Prototype**:

```java
private void cloneCurrentFurniture() {
    if (currentFactory == null || currentFurnitureType == null) {
        return;
    }
    
    // Create a prototype key
    String prototypeName = currentFactory.getStyleName().toLowerCase() + "_" + currentFurnitureType;
    
    // Check if prototype exists
    if (prototypeRegistry.containsKey(prototypeName)) {
        // Get a clone from the registry
        Furniture clonedFurniture = prototypeRegistry.getClone(prototypeName);
        
        // Display the cloned furniture
        if (clonedFurniture != null) {
            furniturePanel.displayFurniture(clonedFurniture);
            JOptionPane.showMessageDialog(this, 
                "Cloned " + clonedFurniture.getName() + " using the Prototype pattern!", 
                "Prototype Cloned", 
                JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
```

#### UML Class Diagram for the Prototype Pattern

```
+---------------------------+
| FurniturePrototypeRegistry|
|---------------------------|
| -instance: FurniturePrototypeRegistry [static]
| -prototypes: Map<String, Furniture>
|---------------------------|
| +getInstance(): FurniturePrototypeRegistry [static]
| +register(String, Furniture): void
| +getClone(String): Furniture
+---------------------------+
            |
            | uses
            v
+---------------------------+
|        Furniture          |
|---------------------------|
| +clone(): Furniture       |
+---------------------------+
            ^
            |
            |
+------+----+----+------+
|      |         |      |
v      v         v      v
+------+  +------+  +------+
| Chair |  | Sofa |  | Table |
+------+  +------+  +------+
```

#### Analysis of the Prototype Implementation

The Prototype pattern in this application provides several benefits:

1. **Efficient Object Creation**: Cloning existing objects can be more efficient than creating new ones, especially for complex objects.

2. **Reduced Class Proliferation**: The prototype pattern allows creating new objects without subclassing.

3. **Registry of Pre-configured Objects**: The `FurniturePrototypeRegistry` maintains a registry of pre-configured prototypes that can be cloned, simplifying object creation.

4. **Runtime Configuration**: Prototypes can be registered and modified at runtime, allowing for dynamic behavior.

## SOLID Principles Application

The application was designed with SOLID principles in mind:

### Single Responsibility Principle

Each class in the system has a single responsibility:
- Model classes (`Chair`, `Sofa`, `Table`) represent furniture items
- Factory classes are responsible for creating furniture items
- `FactoryManager` manages factories
- `FurniturePrototypeRegistry` manages prototypes
- UI classes handle user interaction and display

### Open/Closed Principle

The system is open for extension but closed for modification:
- New furniture styles can be added by creating new concrete factories without modifying existing code
- New furniture types can be added by extending the `Furniture` interface

### Liskov Substitution Principle

Subtypes can be substituted for their base types:
- All furniture implementations can be used where the `Furniture` interface is expected
- All factory implementations can be used where the `FurnitureFactory` interface is expected

### Interface Segregation Principle

Clients are not forced to depend on interfaces they don't use:
- Specific furniture interfaces (`Chair`, `Table`, `Sofa`) provide only methods relevant to each type
- The `FurnitureFactory` interface only includes methods that all concrete factories need

### Dependency Inversion Principle

High-level modules depend on abstractions, not concrete implementations:
- The application works with furniture factories and furniture items through their interfaces
- The UI components depend on the abstract `Furniture` interface, not concrete implementations

## Visual Implementation

The application includes a graphical user interface that visualizes the furniture items. Each furniture style (Modern, Victorian, Art Deco) and type (Chair, Sofa, Table) is represented with a distinct visual appearance.

![Application Screenshot](application_screenshot.png)

The GUI allows users to:
1. Select a furniture style (Modern, Victorian, Art Deco)
2. Select a furniture type (Chair, Sofa, Table)
3. View the selected furniture with its details
4. Clone the current furniture using the Prototype pattern

## Conclusion

This implementation successfully demonstrates the use of the Abstract Factory, Singleton, and Prototype design patterns in a GUI-based furniture production system. The application adheres to SOLID principles, making it maintainable and extensible.

The Abstract Factory pattern ensures that furniture items are created in consistent styles, the Singleton pattern provides global access to factory and prototype management, and the Prototype pattern allows efficient creation of furniture instances through cloning.

The visual representation of the furniture items enhances the user experience and clearly demonstrates the different styles and types of furniture in the system. 