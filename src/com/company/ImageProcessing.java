package com.company;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;

public class ImageProcessing {

    BufferedImage image;
    WritableRaster raster;

    private int height;
    private int width;

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public ImageProcessing(String file) throws IOException {
        image = ImageIO.read(new File(file));
        raster = image.getRaster();
        height = image.getHeight();
        width = image.getWidth();
    }

    public void save(String type, String filename) throws IOException {
        ImageIO.write(image, type, new File(filename));
    }
}