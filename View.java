/*
 * Name: Donna Thakadipuram
 * Date: 3/23/2023
 * Description: Assignment 5 View class. This class holds the view of the game. It draws the tiles and the link.
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

class View extends JPanel {

	BufferedImage tile_image;
	BufferedImage[] link_images;
	Model model;
	//Link link;

	int scroll_x = 0;
	int scroll_y = 0;
	boolean printToScreen = false;


	View(Controller c, Model m) {
		model = m;
		link_images = new BufferedImage[52];
		c.setView(this);
		

		try {
			this.tile_image = ImageIO.read(new File("tile.jpg"));
			
		}

		catch (Exception e) {
			e.printStackTrace(System.err);
			System.exit(1);
		}

	}

	public static BufferedImage loadImage(String filename){
		BufferedImage img = null;
		try{
			img = ImageIO.read(new File(filename));
		}
		catch(IOException e){
			System.out.println("Internal Error:" + e.getMessage());
		}
		return img;
	}

	public void paintComponent(Graphics g) {
		g.setColor(new Color(128, 255, 244));
		g.fillRect(0, 0, this.getWidth(), this.getHeight());

		for (int i = 0; i < model.sprites.size(); i++) {
			model.sprites.get(i).draw(g, scroll_x, scroll_y);
			//g.drawImage(tile_image, t.x - scroll_x, t.y - scroll_y, null);
		}
		//model.link.draw(g,scroll_x, scroll_y);
		

		if (printToScreen) {
			g.setColor(new Color(255, 255, 0));
			g.setFont(new Font("Default", Font.BOLD, 20));
			g.drawString("Edit mode", this.getWidth() - 100, this.getHeight() - 25);
		}
		//g.drawimage(link_images[0], 50, 50, null);

		
	}
}
