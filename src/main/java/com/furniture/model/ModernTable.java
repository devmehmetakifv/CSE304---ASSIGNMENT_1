package com.furniture.model;

import java.awt.Image;
import javax.swing.ImageIcon;

/**
 * Modern style table implementation
 */
public class ModernTable implements Table {
    private String name;
    private String description;
    private double price;
    private Image image;
    private int numberOfLegs;
    private double surfaceArea;
    
    public ModernTable() {
        this.name = "Modern Table";
        this.description = "Sleek table with glass top and metal frame";
        this.price = 799.99;
        this.numberOfLegs = 4;
        this.surfaceArea = 2.4; // in square meters
        
        try {
            // Load image from resources
            ImageIcon icon = new ImageIcon(getClass().getResource("/images/modern/table.png"));
            this.image = icon.getImage();
        } catch (Exception e) {
            // Fallback image if resource not found
            this.image = new ImageIcon().getImage();
            System.err.println("Failed to load Modern Table image: " + e.getMessage());
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
    public int getNumberOfLegs() {
        return numberOfLegs;
    }
    
    @Override
    public double getSurfaceArea() {
        return surfaceArea;
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