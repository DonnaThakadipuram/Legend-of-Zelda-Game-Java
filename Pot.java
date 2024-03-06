/*NAME: Donna Thakadipuram
 * DATE: 3/29/2023
 * DESCRIPTION: Assignment 5 Pot class.
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

 public class Pot extends Sprite{
    BufferedImage broken_pot;
    BufferedImage pot;

    int x_dir, y_dir;
    int speed = 10;
    //boolean broken = false;
    int countdown = 34;

    Pot(int x, int y){
        this.x = x;
        this.y = y;
    
        width = 50;
        height = 50;

        if (broken_pot == null){
            broken_pot = View.loadImage("pot_broken.png");
        }
        if (pot == null){
            pot = View.loadImage("pot.png");
            //System.out.println("loaded pot.png");
        }
        
    }


    public void draw(Graphics g, int scroll_x, int scroll_y){
        
        if(broken){
            g.drawImage(broken_pot, x - scroll_x, y - scroll_y, null);
        }
        else{
            g.drawImage(pot, x - scroll_x, y - scroll_y, null);

        }

    }

    public boolean update(){
        if (broken){

            x_dir = 0;
            y_dir = 0;
            speed = 0;

            countdown--;
            if (countdown <= 0){
                return false;
            }
        }
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
    boolean isPot(){
        return true;
    }

    boolean isBroken(){
        return false;
    }

    public Json marshal(){
        Json ob = Json.newObject();
        ob.add("x", x);
        ob.add("y", y);
        return ob;
    }

    Pot(Json ob){
        x = (int)ob.getLong("x");
        y = (int)ob.getLong("y");
        width = 50;
        height = 50;
        if (broken_pot == null){
            broken_pot = View.loadImage("pot_broken.png");
        }
        if (pot == null){
            pot = View.loadImage("pot.png");
            //System.out.println("loaded pot.png");
        }

    }

 }