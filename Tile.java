/*
Name: Donna Thakadipuram
Date: 3/23/2023
Description: Assignment 5 Tile class. This class holds the x and y coordinates of the tiles.
Also has a remove method that checks if the tile is clicked on and if it is, it removes it.
 * 
 */
import java.awt.image.BufferedImage;
import java.awt.Graphics;


class Tile extends Sprite{
    //int width, height;
    static BufferedImage tile_image = null;

    Tile(int x, int y){
        this.x = x;
        this.y = y;

        width = 75;
        height = 75;
  
        if(tile_image == null){
            tile_image = View.loadImage("tile.jpg"); //????/
        }
    }
    @Override

    public String toString()
    {
	    return "Tile (x,y) = (" + x + ", " + y + "), w = " + width + ", h = " + height;
    }
    public boolean remove(int x_clicked, int y_clicked){
        if(this.x == x_clicked && this.y == y_clicked ){
            return true;
        }
        return false;




    }

    boolean update(){
        return true;

    }
    
    void draw(Graphics g, int scroll_x, int scroll_y){
        //
        g.drawImage(tile_image, x - scroll_x, y - scroll_y, null);
    }


    Json marshal(){
        Json ob = Json.newObject();
        ob.add("x", x);
        ob.add("y", y);
        return ob;
    }
    
    Tile(Json ob){
        x = (int)ob.getLong("x");
        y = (int)ob.getLong("y");
        width = 75;
        height = 75;

        if(tile_image == null){
            tile_image = View.loadImage("tile.jpg"); //????/
        }
    }

    boolean isTile(){
        return true;
    }


    

}