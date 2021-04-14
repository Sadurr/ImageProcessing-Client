/**
 *
 * @author Filip Sadurski
 *
 */
package com.company;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {

        try {
            Client client = new Client();
            Client client2 = new Client();
            ImageProcessing images = new ImageProcessing("van.jpg");
            images.save("JPG", "newFile.jpg");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}