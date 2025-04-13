# Design Pattern Implementation Summary

This document provides a quick reference for where each required design pattern is implemented in the codebase.

## Abstract Factory Pattern

The Abstract Factory pattern is used to create families of related furniture objects without specifying their concrete classes.

**Key Components:**
- **Abstract Factory Interface**: `FurnitureFactory` (src/main/java/com/furniture/factory/FurnitureFactory.java)
- **Concrete Factories**:
  - `ModernFurnitureFactory` (src/main/java/com/furniture/factory/ModernFurnitureFactory.java)
  - `VictorianFurnitureFactory` (src/main/java/com/furniture/factory/VictorianFurnitureFactory.java)
  - `ArtDecoFurnitureFactory` (src/main/java/com/furniture/factory/ArtDecoFurnitureFactory.java)
- **Abstract Products**:
  - `Furniture` (src/main/java/com/furniture/model/Furniture.java)
  - `Chair` (src/main/java/com/furniture/model/Chair.java)
  - `Sofa` (src/main/java/com/furniture/model/Sofa.java)
  - `Table` (src/main/java/com/furniture/model/Table.java)
- **Concrete Products**:
  - Modern style: `ModernChair`, `ModernSofa`, `ModernTable`
  - Victorian style: `VictorianChair`, `VictorianSofa`, `VictorianTable`
  - Art Deco style: `ArtDecoChair`, `ArtDecoSofa`, `ArtDecoTable`

**Usage in the Application:**
- In `FurnitureDesignerApp`, the `updateFurnitureDisplay()` method uses the selected factory to create appropriate furniture objects.

## Singleton Pattern

The Singleton pattern ensures a class has only one instance and provides a global point of access to it.

**Key Components:**
- **FactoryManager** (src/main/java/com/furniture/patterns/FactoryManager.java)
  - Private static instance
  - Private constructor
  - Public static getInstance() method
- **FurniturePrototypeRegistry** (src/main/java/com/furniture/patterns/FurniturePrototypeRegistry.java)
  - Private static instance
  - Private constructor
  - Public static getInstance() method

**Usage in the Application:**
- In `FurnitureDesignerApp` constructor, we access both singletons:
  ```java
  factoryManager = FactoryManager.getInstance();
  prototypeRegistry = FurniturePrototypeRegistry.getInstance();
  ```

## Prototype Pattern

The Prototype pattern allows creating new objects by copying existing objects.

**Key Components:**
- **Cloneable interface extension** in `Furniture` interface
- **Clone method implementations** in all concrete furniture classes
- **Prototype Registry** in `FurniturePrototypeRegistry`

**Usage in the Application:**
- In `FurnitureDesignerApp`, the `cloneCurrentFurniture()` method demonstrates the prototype pattern:
  ```java
  Furniture clonedFurniture = prototypeRegistry.getClone(prototypeName);
  ```

## SOLID Principles

The application follows SOLID principles:

1. **Single Responsibility Principle**:
   - Each class has a single responsibility (furniture models, factories, UI components)

2. **Open/Closed Principle**:
   - New furniture styles can be added without modifying existing code

3. **Liskov Substitution Principle**:
   - All concrete furniture types can be used where their interfaces are expected

4. **Interface Segregation Principle**:
   - Specific interfaces for each furniture type

5. **Dependency Inversion Principle**:
   - High-level modules depend on abstractions, not concrete implementations 