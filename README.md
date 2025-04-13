# Furniture Production System

This project demonstrates a GUI-based furniture production system implementing the Abstract Factory, Singleton, and Prototype design patterns.

## Design Patterns Used

### 1. Abstract Factory Pattern
The Abstract Factory pattern provides an interface for creating families of related objects without specifying their concrete classes. In this project:
- `FurnitureFactory` interface defines the factory for creating different types of furniture
- Concrete factories implement this interface: `ModernFurnitureFactory`, `VictorianFurnitureFactory`, and `ArtDecoFurnitureFactory`
- Each factory creates related furniture items (chairs, sofas, tables) that belong to the same design style

### 2. Singleton Pattern
The Singleton pattern ensures a class has only one instance and provides a global point of access to it. In this project:
- `FactoryManager` is implemented as a singleton to manage all furniture factories
- `FurniturePrototypeRegistry` is implemented as a singleton to manage furniture prototypes

### 3. Prototype Pattern
The Prototype pattern allows creating new objects by copying existing objects. In this project:
- All furniture items implement the `Furniture` interface which extends `Cloneable`
- Each furniture class implements the `clone()` method to create copies of itself
- The `FurniturePrototypeRegistry` maintains prototypes and provides a way to clone them

## SOLID Principles

### Single Responsibility Principle
Each class has a single responsibility:
- Model classes (`Chair`, `Table`, `Sofa`) represent furniture items
- Factory classes are responsible for creating furniture items
- UI classes are responsible for displaying furniture and handling user interactions

### Open/Closed Principle
The code is open for extension but closed for modification:
- New furniture styles can be added by creating new concrete factories without modifying existing code
- New furniture types can be added by extending the `Furniture` interface

### Liskov Substitution Principle
Subtypes can be substituted for their base types:
- All furniture implementations can be used where `Furniture` interface is expected
- All factory implementations can be used where `FurnitureFactory` interface is expected

### Interface Segregation Principle
Clients are not forced to depend on interfaces they don't use:
- Specific furniture interfaces (`Chair`, `Table`, `Sofa`) provide only methods relevant to each type

### Dependency Inversion Principle
High-level modules depend on abstractions, not concrete implementations:
- The application works with furniture factories and furniture items through their interfaces

## How to Run

1. Compile the Java classes:
```
javac -d bin src/main/java/com/furniture/**/*.java
```

2. Run the application:
```
java -cp bin com.furniture.ui.FurnitureDesignerApp
```

## Note

This project requires real furniture image files in the resources directory to display properly. Add appropriate images to:
- `resources/images/modern/` (chair.png, sofa.png, table.png)
- `resources/images/victorian/` (chair.png, sofa.png, table.png)
- `resources/images/artdeco/` (chair.png, sofa.png, table.png) 