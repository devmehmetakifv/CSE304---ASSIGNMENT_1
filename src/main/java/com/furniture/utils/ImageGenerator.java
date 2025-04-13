package com.furniture.utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

/**
 * Utility class to generate sample furniture images
 */
public class ImageGenerator {
    
    /**
     * Generate a chair image
     * @param style The furniture style
     * @param outputPath The path to save the image
     */
    public static void generateChairImage(String style, String outputPath) {
        BufferedImage image = new BufferedImage(200, 200, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = image.createGraphics();
        setupGraphics(g2d);
        
        // Background
        g2d.setColor(getBackgroundColor(style));
        g2d.fillRect(0, 0, 200, 200);
        
        // Chair seat
        g2d.setColor(getFurnitureColor(style));
        g2d.fillRect(50, 120, 100, 20);
        
        // Chair back
        g2d.fillRect(50, 60, 100, 60);
        
        // Chair legs
        g2d.setColor(Color.BLACK);
        g2d.fillRect(50, 140, 10, 40);
        g2d.fillRect(140, 140, 10, 40);
        
        // Style text
        g2d.setColor(Color.BLACK);
        g2d.setFont(new Font("Arial", Font.BOLD, 14));
        g2d.drawString(style + " Chair", 60, 30);
        
        g2d.dispose();
        saveImage(image, outputPath);
    }
    
    /**
     * Generate a sofa image
     * @param style The furniture style
     * @param outputPath The path to save the image
     */
    public static void generateSofaImage(String style, String outputPath) {
        BufferedImage image = new BufferedImage(200, 200, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = image.createGraphics();
        setupGraphics(g2d);
        
        // Background
        g2d.setColor(getBackgroundColor(style));
        g2d.fillRect(0, 0, 200, 200);
        
        // Sofa base
        g2d.setColor(getFurnitureColor(style));
        g2d.fillRect(20, 120, 160, 30);
        
        // Sofa back
        g2d.fillRect(20, 70, 160, 50);
        
        // Sofa arms
        g2d.fillRect(20, 70, 20, 80);
        g2d.fillRect(160, 70, 20, 80);
        
        // Sofa legs
        g2d.setColor(Color.BLACK);
        g2d.fillRect(30, 150, 10, 20);
        g2d.fillRect(160, 150, 10, 20);
        
        // Style text
        g2d.setColor(Color.BLACK);
        g2d.setFont(new Font("Arial", Font.BOLD, 14));
        g2d.drawString(style + " Sofa", 60, 30);
        
        g2d.dispose();
        saveImage(image, outputPath);
    }
    
    /**
     * Generate a table image
     * @param style The furniture style
     * @param outputPath The path to save the image
     */
    public static void generateTableImage(String style, String outputPath) {
        BufferedImage image = new BufferedImage(200, 200, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = image.createGraphics();
        setupGraphics(g2d);
        
        // Background
        g2d.setColor(getBackgroundColor(style));
        g2d.fillRect(0, 0, 200, 200);
        
        // Table top
        g2d.setColor(getFurnitureColor(style));
        g2d.fillRect(30, 80, 140, 20);
        
        // Table legs
        g2d.setColor(Color.BLACK);
        g2d.fillRect(40, 100, 10, 60);
        g2d.fillRect(150, 100, 10, 60);
        
        // Style text
        g2d.setColor(Color.BLACK);
        g2d.setFont(new Font("Arial", Font.BOLD, 14));
        g2d.drawString(style + " Table", 60, 30);
        
        g2d.dispose();
        saveImage(image, outputPath);
    }
    
    /**
     * Set up graphics for anti-aliasing
     * @param g2d The Graphics2D object
     */
    private static void setupGraphics(Graphics2D g2d) {
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
    }
    
    /**
     * Get background color based on style
     * @param style The furniture style
     * @return The background color
     */
    private static Color getBackgroundColor(String style) {
        switch (style.toLowerCase()) {
            case "modern":
                return new Color(240, 240, 240);
            case "victorian":
                return new Color(255, 240, 220);
            case "art deco":
                return new Color(220, 220, 255);
            default:
                return Color.WHITE;
        }
    }
    
    /**
     * Get furniture color based on style
     * @param style The furniture style
     * @return The furniture color
     */
    private static Color getFurnitureColor(String style) {
        switch (style.toLowerCase()) {
            case "modern":
                return new Color(80, 80, 80);
            case "victorian":
                return new Color(120, 60, 20);
            case "art deco":
                return new Color(200, 180, 60);
            default:
                return Color.GRAY;
        }
    }
    
    /**
     * Save image to file
     * @param image The image to save
     * @param outputPath The path to save the image
     */
    private static void saveImage(BufferedImage image, String outputPath) {
        try {
            File outputFile = new File(outputPath);
            outputFile.getParentFile().mkdirs();
            ImageIO.write(image, "png", outputFile);
            System.out.println("Image generated: " + outputPath);
        } catch (Exception e) {
            System.err.println("Failed to save image: " + e.getMessage());
        }
    }
    
    /**
     * Generate all furniture images
     */
    public static void generateAllImages() {
        // Modern furniture
        generateChairImage("Modern", "resources/images/modern/chair.png");
        generateSofaImage("Modern", "resources/images/modern/sofa.png");
        generateTableImage("Modern", "resources/images/modern/table.png");
        
        // Victorian furniture
        generateChairImage("Victorian", "resources/images/victorian/chair.png");
        generateSofaImage("Victorian", "resources/images/victorian/sofa.png");
        generateTableImage("Victorian", "resources/images/victorian/table.png");
        
        // Art Deco furniture
        generateChairImage("Art Deco", "resources/images/artdeco/chair.png");
        generateSofaImage("Art Deco", "resources/images/artdeco/sofa.png");
        generateTableImage("Art Deco", "resources/images/artdeco/table.png");
    }
    
    /**
     * Main method to run the image generator
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        generateAllImages();
    }
} 