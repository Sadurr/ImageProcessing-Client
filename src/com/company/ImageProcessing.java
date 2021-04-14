package com.company;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;

public class ImageProcessing {


    private int pixels[] = new int [3];

     BufferedImage image = null;
     WritableRaster raster = null;

    private int height = 0;
    private int width = 0;

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public ImageProcessing(String file) throws IOException {
      //  File myFile = new File("van.jpg");
        image = ImageIO.read(new File(file));
        raster = image.getRaster();
        height = image.getHeight();
        width = image.getWidth();
    }

    public void save(String type, String filename) throws IOException {
        ImageIO.write(image, type, new File(filename));
    }


    public void redSaturation() {
        for(int i = 0; i < height; i++) {
            for(int j=0; j < width; j++) {
                int rgb = image.getRGB(j,i); //int zapisywany na 32bitach
                image.setRGB(j,i,((rgb >> 16) << 16));
            }
        }
    }

    public void greenSaturation() {
        for(int i=0; i < height; i++) {
            for(int j=0; j < width; j++) {
                int rgb = image.getRGB(j,i);
                image.setRGB(j,i,(rgb >> 8) << 8);
            }
        }
    }

    public void blueSaturation() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                int rgb = image.getRGB(j, i);
                //0xff usuwa(gasi) wartosci na pozostalych bitach, zostawie tylko na ostatnim
                image.setRGB(j, i, (rgb & 0xff));
            }
        }
    }

//    public void toGrey() {
//        double arr[] = new double[3];
//        for(int i=0; i < width; i++) {
//            for(int j=0; j < height; j++) {
//                raster.getPixel(i, j, pixels);
//                arr[0] = 0.299*pixels[0]+0.587*pixels[1]+0.114*pixels[2];
//                arr[1] = 0.299*pixels[0]+0.587*pixels[1]+0.114*pixels[2];
//                arr[2] = 0.299*pixels[0]+0.587*pixels[1]+0.114*pixels[2];
//
//                raster.setPixel(i, j, arr);
//            }
//        }
//    }


}
