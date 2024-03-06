/*
 * Name: Donna Thakadipuram
 * Date: 3/23/2023
 * Description: Assignment 5 Sprite class.
 */

//import java.awt.image.BufferedImage;
//import graphics
import java.awt.Graphics;

abstract class Sprite{
    int x, y, width, height;
    boolean broken = false;


    abstract boolean update();
    abstract void draw(Graphics g, int scroll_x, int scroll_y);
    abstract Json marshal();

    boolean isLink(){
        return false;
    }

    boolean isTile(){
        return false;
    }

    boolean isPot(){
        return false;
    }

    boolean isBoomerang(){
        return false;
    }
    
     public boolean remove(int x, int y) {
        return false;

    }

    

}