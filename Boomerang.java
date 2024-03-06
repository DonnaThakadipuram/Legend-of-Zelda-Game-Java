/*Name: Donna Thakadipuram
//Date: 3/29/2023
//Description: Assignment 5 Boomerang class.
 */
 import javax.swing.*;
 import java.awt.Graphics;
 import java.awt.image.BufferedImage;
 import javax.imageio.ImageIO;
 import java.io.IOException;
 import java.io.File;
 import javax.swing.JButton;
 import java.awt.Color;
 import java.awt.Font;

  public class Boomerang extends Sprite{
//     static final int DOWN = 0;
//     static final int UP = 3;
//     static final int LEFT = 1;
//     static final int RIGHT = 2;

    int x_dir, y_dir;
    int speed = 10;


    static BufferedImage[] boomerang_images;
    static int current_image_index = 0;

    Boomerang(int x, int y){
        this.x = x;
        this.y = y;

        width = 25;
        height = 25;


        if(boomerang_images == null){

            boomerang_images = new BufferedImage[4];

            for (int i = 0; i <= 3; i++){
                boomerang_images[i] = View.loadImage("boomerangImages/boomerang" + (i + 1) + ".png");
                //System.out.println("loaded boomerang" + i + ".png");
            }


        }

    }
    
    public void draw(Graphics g, int scroll_x, int scroll_y){
        //int a = current_image_index + 1 + direction * 13;
        // int x = 0;
        // while(true){
        //     if(x > 3){
        //         x = 0;
        //     }

        //     g.drawImage(boomerang_images[x], x-scroll_x, y-scroll_y, width, height, null);
        //     x++;
        // }

        g.drawImage(boomerang_images[current_image_index % 4], x-scroll_x, y-scroll_y, width, height, null);
        //System.out.println("drawn boomerang");
        if (current_image_index == 3){
            current_image_index = 0;
        }
        current_image_index ++;
            //cycle through the boomerang images and draw them
        //for(int i = 0; i < 4; i++){
        //    g.drawImage(boomerang_images[i], x-scroll_x, y-scroll_y, width, height, null);
        //    System.out.println("drawn boomerang" + i);
        //}
        //update();

        //g.drawImage(boomerang_images[0], x-scroll_x, y-scroll_y, width, height, null);
        //System.out.println("drawn boomerang");
        




    }

    public boolean update(){
        x += x_dir * speed;
        y += y_dir * speed;


        return true;

    }

    public void move(int linkDirection){
        if (linkDirection == 0){
            y_dir = 1;
            x_dir = 0;

        }
        else if(linkDirection == 1){
            y_dir = 0;
            x_dir = -1;

        }
        else if(linkDirection == 2){
            y_dir = 0;
            x_dir = 1;

        }
        else if(linkDirection == 3){
            y_dir = -1;
            x_dir = 0;

        }

    }
    @Override
    boolean isBoomerang(){
        return true;
    }

    public Json marshal(){
        Json ob = Json.newObject();
        return ob;

    }

    //to string method for boomerang
    public String toString(){
        return "Boomerang: " + x + " " + y;
    }

 }