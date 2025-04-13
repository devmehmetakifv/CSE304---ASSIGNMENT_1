package com.furniture.ui;

import com.furniture.factory.FurnitureFactory;
import com.furniture.model.Chair;
import com.furniture.model.Furniture;
import com.furniture.model.Sofa;
import com.furniture.model.Table;
import com.furniture.patterns.FactoryManager;
import com.furniture.patterns.FurniturePrototypeRegistry;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

/**
 * Main application class for the Furniture Designer Application
 */
public class FurnitureDesignerApp extends JFrame {
    private final FactoryManager factoryManager;
    private final FurniturePrototypeRegistry prototypeRegistry;
    private final FurniturePanel furniturePanel;
    private FurnitureFactory currentFactory;
    private String currentFurnitureType;
    
    /**
     * Constructor to create the main application frame
     */
    public FurnitureDesignerApp() {
        // Set frame properties
        super("Furniture Designer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        
        // Initialize singleton instances
        factoryManager = FactoryManager.getInstance();
        prototypeRegistry = FurniturePrototypeRegistry.getInstance();
        
        // Create furniture panel
        furniturePanel = new FurniturePanel();
        
        // Create control panel
        JPanel controlPanel = createControlPanel();
        
        // Add panels to frame
        add(controlPanel, BorderLayout.WEST);
        add(furniturePanel, BorderLayout.CENTER);
        
        // Set default factory and furniture type
        Map<String, FurnitureFactory> factories = factoryManager.getAllFactories();
        if (!factories.isEmpty()) {
            currentFactory = factories.values().iterator().next();
            currentFurnitureType = "chair";
            updateFurnitureDisplay();
        }
        
        // Display design patterns information
        JOptionPane.showMessageDialog(this,
            "This application demonstrates:\n" +
            "- Abstract Factory Pattern (Furniture factories)\n" +
            "- Singleton Pattern (Factory Manager and Prototype Registry)\n" +
            "- Prototype Pattern (Clone button functionality)\n\n" +
            "Select different styles and furniture types to see them in action.",
            "Design Patterns Demonstration",
            JOptionPane.INFORMATION_MESSAGE);
    }
    
    /**
     * Create the control panel with style and furniture type selection
     * @return The created control panel
     */
    private JPanel createControlPanel() {
        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new BorderLayout());
        controlPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        controlPanel.setPreferredSize(new Dimension(250, getHeight()));
        
        // Create style selection panel
        JPanel stylePanel = new JPanel(new BorderLayout());
        stylePanel.setBorder(BorderFactory.createTitledBorder("Style"));
        
        // Create style radio buttons
        ButtonGroup styleGroup = new ButtonGroup();
        JPanel radioPanel = new JPanel(new GridLayout(0, 1));
        
        Map<String, FurnitureFactory> factories = factoryManager.getAllFactories();
        for (Map.Entry<String, FurnitureFactory> entry : factories.entrySet()) {
            String style = entry.getKey();
            FurnitureFactory factory = entry.getValue();
            
            JRadioButton radioButton = new JRadioButton(factory.getStyleName());
            radioButton.setActionCommand(style);
            
            radioButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    currentFactory = factory;
                    updateFurnitureDisplay();
                }
            });
            
            styleGroup.add(radioButton);
            radioPanel.add(radioButton);
            
            // Select the first style by default
            if (currentFactory == null) {
                radioButton.setSelected(true);
                currentFactory = factory;
            }
        }
        
        stylePanel.add(radioPanel, BorderLayout.CENTER);
        
        // Create furniture type selection panel
        JPanel typePanel = new JPanel(new BorderLayout());
        typePanel.setBorder(BorderFactory.createTitledBorder("Furniture Type"));
        
        // Create furniture type radio buttons
        ButtonGroup typeGroup = new ButtonGroup();
        JPanel typeRadioPanel = new JPanel(new GridLayout(0, 1));
        
        JRadioButton chairButton = new JRadioButton("Chair");
        chairButton.setActionCommand("chair");
        chairButton.setSelected(true);
        currentFurnitureType = "chair";
        
        chairButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentFurnitureType = "chair";
                updateFurnitureDisplay();
            }
        });
        
        JRadioButton sofaButton = new JRadioButton("Sofa");
        sofaButton.setActionCommand("sofa");
        
        sofaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentFurnitureType = "sofa";
                updateFurnitureDisplay();
            }
        });
        
        JRadioButton tableButton = new JRadioButton("Table");
        tableButton.setActionCommand("table");
        
        tableButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentFurnitureType = "table";
                updateFurnitureDisplay();
            }
        });
        
        typeGroup.add(chairButton);
        typeGroup.add(sofaButton);
        typeGroup.add(tableButton);
        
        typeRadioPanel.add(chairButton);
        typeRadioPanel.add(sofaButton);
        typeRadioPanel.add(tableButton);
        
        typePanel.add(typeRadioPanel, BorderLayout.CENTER);
        
        // Create prototype section
        JPanel prototypePanel = new JPanel(new BorderLayout());
        prototypePanel.setBorder(BorderFactory.createTitledBorder("Prototype"));
        
        JButton cloneButton = new JButton("Clone Current Furniture");
        cloneButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cloneCurrentFurniture();
            }
        });
        
        prototypePanel.add(cloneButton, BorderLayout.CENTER);
        
        // Add all sections to control panel
        controlPanel.add(stylePanel, BorderLayout.NORTH);
        controlPanel.add(typePanel, BorderLayout.CENTER);
        controlPanel.add(prototypePanel, BorderLayout.SOUTH);
        
        return controlPanel;
    }
    
    /**
     * Update the furniture display based on current selections
     */
    private void updateFurnitureDisplay() {
        if (currentFactory == null || currentFurnitureType == null) {
            furniturePanel.clear();
            return;
        }
        
        Furniture furniture = null;
        String patternUsed = "Abstract Factory Pattern";
        
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
        
        // Show pattern information in the title
        setTitle("Furniture Designer - " + 
                 currentFactory.getStyleName() + " " + 
                 capitalizeFirstLetter(currentFurnitureType) + 
                 " (" + patternUsed + ")");
    }
    
    /**
     * Clone the current furniture using the prototype pattern
     */
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
                
                // Update title to show prototype pattern
                setTitle("Furniture Designer - " + 
                         currentFactory.getStyleName() + " " + 
                         capitalizeFirstLetter(currentFurnitureType) + 
                         " (Prototype Pattern)");
                
                JOptionPane.showMessageDialog(this, 
                    "Cloned " + clonedFurniture.getName() + " using the Prototype pattern!", 
                    "Prototype Cloned", 
                    JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }
    
    /**
     * Capitalize the first letter of a string
     * @param str The string to capitalize
     * @return The capitalized string
     */
    private String capitalizeFirstLetter(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }
    
    /**
     * Main method to start the application
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        // Set the look and feel to the system look and feel
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        // Create and show the application frame
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                FurnitureDesignerApp app = new FurnitureDesignerApp();
                app.setVisible(true);
            }
        });
    }
} 