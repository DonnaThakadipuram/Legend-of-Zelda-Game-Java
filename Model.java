/*
 * Name: Donna Thakadipuram
 * Date: 3/23/2023
 * Description: Assignment 5 Model class.This class holds the array list of tiles and collision detection
 */

 import java.util.ArrayList;
 import java.util.Iterator;
 import java.util.concurrent.TimeUnit;

class Model
{
	ArrayList<Sprite> sprites;
	Link link;
	Boomerang boomerang;
	Pot pot;
	

	Model()
	{
		sprites = new ArrayList<Sprite>();
		link = new Link(150,150);
		sprites.add(link);

	}

	public void update()
	{
		//link.update();
		for(int i = 0; i < sprites.size(); i++){
			if(collision(sprites.get(i))){
				link.getOutOfTile(sprites.get(i));
				if(sprites.get(i).isPot()){
					((Pot) sprites.get(i)).move(link.getDirection());
				}
			}
			sprites.get(i).update();	

		}
		for(int i = 0; i < sprites.size(); i++){
			for(int j = 0; j < sprites.size(); j++){
				if(collision(sprites.get(i), sprites.get(j))){
					//boomerang and tile collisions
					if((sprites.get(i).isBoomerang() && sprites.get(j).isTile())){
						sprites.remove(i);
					}
					else if(sprites.get(i).isTile() && sprites.get(j).isBoomerang()){
						sprites.remove(j);
					}


					//boomerang and pot collisions
					else if((sprites.get(i).isBoomerang() && sprites.get(j).isPot())){
						//change the pot image to the broken pot image and remove the boomerang
						sprites.remove(i);
						sprites.get(j).broken = true;
						//wait 2 seconds and remove the pot image
						sprites.remove(j);
						sprites.get(j).update();
						//System.out.println("removed pot");



					}
					//pot and boomerang collisions
					else if(sprites.get(i).isPot() && sprites.get(j).isBoomerang()){
						//change the pot image to the broken pot image and remove the boomerang
						sprites.remove(j);
						sprites.get(i).broken = true;

					}

					//link and pot collisions
					else if((sprites.get(i).isTile() && sprites.get(j).isPot())){
						sprites.get(j).broken = true;
						
					}
					// else if(sprites.get(i).isPot() && sprites.get(j).isTile()){
					// 	sprites.get(i).broken = true;

					// }

				}
				//sprites.get(j).update();
			}
			if(!sprites.get(i).update()){
				sprites.remove(i);
				continue;
			}
			
		}
		
	}


	// collison detection
	public boolean collision(Sprite s){
		int buffer = 5; // Set a buffer size of 5 pixels around both sprites
		if (link.x + link.width - buffer < s.x || link.x + buffer > s.x + s.width) {
			// No collision in the x-axis
			return false;
		}
		if (link.y + link.height - buffer < s.y || link.y + buffer > s.y + s.height) {
			// No collision in the y-axis
			return false;
		}
		// There is a collision in both x and y axes
		return true;
	}
	
	

	//when boomerang collides with tiles or pots, the boomerang disappears
	public boolean collision(Sprite s, Sprite d){
		if(s.x + s.width <= d.x)
			return false;
		if(s.x >= d.x + d.width) // + s.width
			return false;
		if(s.y + s.height <= d.y) // assumes bigger is downward
			return false;
		if(s.y >= d.y + d.height) // + s.height
			return false;
		return true;
	}

	public void addBoomerang(){
		Sprite boomer = new Boomerang(link.x, link.y);
		sprites.add(boomer);

		int direction = link.getDirection();
		
		((Boomerang)boomer).move(direction);
		boomer.update();

	}


	Json marshal(){
		Json ob = Json.newObject();
		Json tilesArray = Json.newList();
		Json potsArray = Json.newList();
		ob.add("tiles", tilesArray);
		ob.add("pots", potsArray);

		// Iterator<Sprite> t = sprites.iterator();
		// while(t.hasNext()){

		// 	if(t.next().isPot())
		// 		potsArray.add(t.next().marshal());
		// 		//System.out.println("adding pot");
		// 	if(t.next().isTile()){
		// 		tilesArray.add(t.next().marshal());
		// 	}
		// 	//tilesArray.add(t.next().marshal());
		// 	//potsArray.add(t.next().marshal());
		// }
		for(int i = 0; i <sprites.size(); i++){
			if(sprites.get(i).isPot()){
				potsArray.add(sprites.get(i).marshal());
			}
			if(sprites.get(i).isTile()){
				tilesArray.add(sprites.get(i).marshal());
			}
		}

		//for(int i = 0; i < tiles.size(); i++){
			//tilesArray.add(tiles.get(i).marshal());
		//}
		return ob;
	}

	void unmarshal(Json ob){
		sprites = new ArrayList<Sprite>();

		Json tilesArray = ob.get("tiles");
		Json potsArray = ob.get("pots");

		for(int i = 0; i < tilesArray.size(); i++){
			sprites.add(new Tile(tilesArray.get(i)));
		}
		for(int i = 0; i < potsArray.size(); i++){
			sprites.add(new Pot(potsArray.get(i)));
		}
		sprites.add(link);

	}
	
}