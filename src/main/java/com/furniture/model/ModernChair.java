package com.furniture.model;

import java.awt.Image;
import javax.swing.ImageIcon;

/**
 * Modern style chair implementation
 */
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
        
        try {
            // Load image from resources
            ImageIcon icon = new ImageIcon(getClass().getResource("/images/modern/chair.png"));
            this.image = icon.getImage();
        } catch (Exception e) {
            // Fallback image if resource not found
            this.image = new ImageIcon().getImage();
            System.err.println("Failed to load Modern Chair image: " + e.getMessage());
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
    public boolean hasArmrests() {
        return hasArmrests;
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