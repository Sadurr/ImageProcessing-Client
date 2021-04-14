package com.company;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class Client extends Thread {

    int height, width;
    ImageProcessing imageProcessing;
    BufferedImage bufferedImage;
    JFrame frame;
    ImageIcon icon;
    JLabel label;
    JButton button;
    Socket socket;

    public Client() throws IOException {
        imageProcessing = new ImageProcessing("van.jpg");
        bufferedImage = null;
        height = imageProcessing.getHeight();
        width = imageProcessing.getWidth();
        makeFrame();
        connection();
    }

    public void makeFrame() {
        frame = new JFrame("CLIENT");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(width, height);
        icon = new ImageIcon("van.jpg");
        label = new JLabel(icon);
        button = new JButton("Send");
        frame.add(label, BorderLayout.CENTER);
        frame.add(button, BorderLayout.SOUTH);
        frame.setVisible(true);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    OutputStream outputStream = socket.getOutputStream();
                    BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream);

                    Image image = imageProcessing.image;
                    // Image image = icon.getImage();
                    BufferedImage bufferedImage = imageProcessing.image;
                    Graphics graphics = bufferedImage.createGraphics();
                    graphics.drawImage(image, 0, 0, null);
                    graphics.dispose();

                    ImageIO.write(bufferedImage, "jpg", bufferedOutputStream);
                    bufferedOutputStream.close();
                    socket.close();

                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });
    }

    public void connection() throws IOException {

            socket = new Socket("localhost", 1234);
            System.out.println("connected to server");
        }
    }