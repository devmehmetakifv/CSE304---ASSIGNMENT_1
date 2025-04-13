package com.furniture.ui;

import com.furniture.model.Chair;
import com.furniture.model.Furniture;
import com.furniture.model.Sofa;
import com.furniture.model.Table;

import javax.swing.*;
import java.awt.*;

/**
 * Panel for displaying furniture details
 */
public class FurniturePanel extends JPanel {
    private final JLabel imageLabel;
    private final JLabel nameLabel;
    private final JLabel descriptionLabel;
    private final JLabel priceLabel;
    private final JPanel detailsPanel;
    
    /**
     * Constructor to create a furniture panel
     */
    public FurniturePanel() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Create image label
        imageLabel = new JLabel();
        imageLabel.setHorizontalAlignment(JLabel.CENTER);
        imageLabel.setPreferredSize(new Dimension(200, 200));
        imageLabel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        
        // Create details panel
        detailsPanel = new JPanel();
        detailsPanel.setLayout(new GridLayout(0, 1, 5, 5));
        
        // Create labels for furniture details
        nameLabel = new JLabel();
        nameLabel.setFont(new Font("Arial", Font.BOLD, 16));
        
        descriptionLabel = new JLabel();
        descriptionLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        
        priceLabel = new JLabel();
        priceLabel.setFont(new Font("Arial", Font.BOLD, 14));
        
        // Add labels to details panel
        detailsPanel.add(nameLabel);
        detailsPanel.add(descriptionLabel);
        detailsPanel.add(priceLabel);
        
        // Add components to panel
        add(imageLabel, BorderLayout.NORTH);
        add(detailsPanel, BorderLayout.CENTER);
    }
    
    /**
     * Display furniture details
     * @param furniture The furniture to display
     */
    public void displayFurniture(Furniture furniture) {
        if (furniture == null) {
            clear();
            return;
        }
        
        // Set furniture details
        nameLabel.setText(furniture.getName());
        descriptionLabel.setText(furniture.getDescription());
        priceLabel.setText(String.format("Price: $%.2f", furniture.getPrice()));
        
        // Set furniture image
        ImageIcon icon = new ImageIcon(furniture.getImage());
        Image image = icon.getImage().getScaledInstance(180, 180, Image.SCALE_SMOOTH);
        imageLabel.setIcon(new ImageIcon(image));
        
        // Remove any additional details
        for (int i = detailsPanel.getComponentCount() - 1; i >= 3; i--) {
            detailsPanel.remove(i);
        }
        
        // Add type-specific details
        if (furniture instanceof Chair) {
            Chair chair = (Chair) furniture;
            JLabel legsLabel = new JLabel(String.format("Legs: %d", chair.getNumberOfLegs()));
            JLabel armrestsLabel = new JLabel(String.format("Armrests: %s", chair.hasArmrests() ? "Yes" : "No"));
            
            detailsPanel.add(legsLabel);
            detailsPanel.add(armrestsLabel);
        } else if (furniture instanceof Table) {
            Table table = (Table) furniture;
            JLabel legsLabel = new JLabel(String.format("Legs: %d", table.getNumberOfLegs()));
            JLabel areaLabel = new JLabel(String.format("Surface Area: %.1f m²", table.getSurfaceArea()));
            
            detailsPanel.add(legsLabel);
            detailsPanel.add(areaLabel);
        } else if (furniture instanceof Sofa) {
            Sofa sofa = (Sofa) furniture;
            JLabel seatsLabel = new JLabel(String.format("Seats: %d", sofa.getNumberOfSeats()));
            JLabel convertibleLabel = new JLabel(String.format("Convertible: %s", sofa.isConvertible() ? "Yes" : "No"));
            
            detailsPanel.add(seatsLabel);
            detailsPanel.add(convertibleLabel);
        }
        
        revalidate();
        repaint();
    }
    
    /**
     * Clear the displayed furniture
     */
    public void clear() {
        nameLabel.setText("");
        descriptionLabel.setText("");
        priceLabel.setText("");
        imageLabel.setIcon(null);
        
        // Remove any additional details
        for (int i = detailsPanel.getComponentCount() - 1; i >= 3; i--) {
            detailsPanel.remove(i);
        }
        
        revalidate();
        repaint();
    }
} 