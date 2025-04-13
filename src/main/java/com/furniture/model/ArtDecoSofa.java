package com.furniture.model;

import java.awt.Image;
import javax.swing.ImageIcon;

/**
 * Art Deco style sofa implementation
 */
public class ArtDecoSofa implements Sofa {
    private String name;
    private String description;
    private double price;
    private Image image;
    private int numberOfSeats;
    private boolean isConvertible;
    
    public ArtDecoSofa() {
        this.name = "Art Deco Sofa";
        this.description = "Glamorous sofa with geometric patterns and bold colors";
        this.price = 2199.99;
        this.numberOfSeats = 4;
        this.isConvertible = false;
        
        try {
            // Load image from resources
            ImageIcon icon = new ImageIcon(getClass().getResource("/images/artdeco/sofa.png"));
            this.image = icon.getImage();
        } catch (Exception e) {
            // Fallback image if resource not found
            this.image = new ImageIcon().getImage();
            System.err.println("Failed to load Art Deco Sofa image: " + e.getMessage());
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