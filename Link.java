//---------------------------------------------------------------------------------------------------------
//Name: Donna Thakadipuram
//Date: 3/23/2023
//Description: Assignment 5 Link class. This class is the class that creates the link object and
//allows for the link object to move
//---------------------------------------------------------------------------------------------------------

import javax.swing.*;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.File;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;



public class Link extends Sprite{
    int prev_x;
    int prev_y;

    double speed = 5;

    static final int DOWN = 0;
    static final int UP = 3;
    static final int LEFT = 1;
    static final int RIGHT = 2;

    int direction = 0; //down
    int current_image_index = 0;

    static BufferedImage[] link_images;

    public static int width = 60;
    public static int height = 60;
    int previous_x;
    int previous_y;

    @Override 
    public String toString()
    {
	return "Link (x,y) = (" + x + ", " + y + "), x = " + x + ", y = " + y;
    }

    Link(int x, int y){
        this.x = x;
        this.y = y;
        if(link_images == null){
            link_images = new BufferedImage[52];
            for (int i = 1; i < 52; i++) {
                if (i < 10) {
                    link_images[i] = View.loadImage("linkImages/link0" + i + ".png");
                } else {
                    link_images[i] = View.loadImage("linkImages/link" + i + ".png");
                }
            }
        }

    }



    public void draw(Graphics g, int scroll_x, int scroll_y){


            //g.drawImage(link_images[current_image_index + direction * 11], x-scroll_x, y-scroll_y, null);

            int a = current_image_index + 1 + direction * 13;
            g.drawImage(link_images[a], x-scroll_x, y-scroll_y, null);
            //System.out.println(a);
            //System.out.println("loaded link "+(current_image_index + direction * 13)+".png\n");
            //System.out.println(current_image_index);
    
        
    }

    public boolean update(){
        return true;
    }

    public int getDirection(){
        return direction;
    }

    @Override
    boolean isLink(){
        return true;
    }
    
    Json marshal(){
        Json ob = Json.newObject();
        ob.add("x", x);
        ob.add("y", y);
        ob.add("width", width);
        ob.add("height", height);
        
        
        return ob;

    }

    Link(Json ob){
        x = (int)ob.getLong("x");
        y = (int)ob.getLong("y");
        width = 60;
        height = 60;
    }

    public void setPrevious(int x, int y){
        prev_x = x;
        prev_y = y;
    }

    public void getOutOfTile(Sprite t){
            if((x + width >= t.x) && prev_x + width <= t.x){
                x = t.x - Link.width;
            }
            if(x <= t.x + t.width && prev_x >= t.x + t.width){
                x = t.x + t.width;
            }
            if(y <= t.y + t.height && prev_y >= t.y + t.height){
                y = t.y + t.height;
            }
            if(y + height >= t.y && prev_y + height <= t.y){
                y = t.y - Link.height;
            }
        }


    public void updateDirection(int direction){
        this.direction = direction;

        //goes to next image in the array
        current_image_index++;

        if (direction == UP){
            if(current_image_index >= 11){
                current_image_index = 0;
            }
        }
        else if (current_image_index >= 13) {
            current_image_index = 0;
        }
    }


}