package com.furniture.model;

import java.awt.Image;
import javax.swing.ImageIcon;

/**
 * Modern style sofa implementation
 */
public class ModernSofa implements Sofa {
    private String name;
    private String description;
    private double price;
    private Image image;
    private int numberOfSeats;
    private boolean isConvertible;
    
    public ModernSofa() {
        this.name = "Modern Sofa";
        this.description = "Sleek sofa with clean lines and minimalist design";
        this.price = 1299.99;
        this.numberOfSeats = 3;
        this.isConvertible = true;
        
        try {
            // Load image from resources
            ImageIcon icon = new ImageIcon(getClass().getResource("/images/modern/sofa.png"));
            this.image = icon.getImage();
        } catch (Exception e) {
            // Fallback image if resource not found
            this.image = new ImageIcon().getImage();
            System.err.println("Failed to load Modern Sofa image: " + e.getMessage());
        }
    }
    
    @Override
    public String getName() {
        return name;
    }
    
    @Override
    public String getDescription() {
        return description;
    }
    
    @Override
    public double getPrice() {
        return price;
    }
    
    @Override
    public Image getImage() {
        return image;
    }
    
    @Override
    public int getNumberOfSeats() {
        return numberOfSeats;
    }
    
    @Override
    public boolean isConvertible() {
        return isConvertible;
    }
    
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