/*
 * Name: Donna Thakadipuram
 * Date: 3/23/2023
 * Description: Assignment 5 Controller class. This class allows for movement between the different rooms and
 * also allows for the user to add and remove tiles. Anything that requires the mouse or keyboard to be used
 * is done in this class.
 */

//importing everything needed to set up the mouse and keyboard controls
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

class Controller implements ActionListener, MouseListener, KeyListener
{
	View view;
	Model model;
	boolean keyLeft;
	boolean keyRight;
	boolean keyUp;
	boolean keyDown;

	boolean editMode = false;

	boolean potMode = false;


	public void mouseReleased(MouseEvent e) {  }
	public void mouseEntered(MouseEvent e) {    }
	public void mouseExited(MouseEvent e) {    }
	public void mouseClicked(MouseEvent e) {    }

	void setView(View v)
	{
		view = v;
	}

	Controller(Model m)
	{
		model = m;
	}
	

	public void actionPerformed(ActionEvent e)
	{
		//view.removeButton();
	}


	public void keyPressed(KeyEvent e)
	{
		switch(e.getKeyCode())
		{
			case KeyEvent.VK_RIGHT:{
				model.link.updateDirection(Link.RIGHT);
				keyRight = true;
				if(view.scroll_x != 700 && model.link.x >= 700 && model.link.x <= 1400){
					view.scroll_x = view.scroll_x + 700;
				}
				break;
			}

			case KeyEvent.VK_LEFT:{
				model.link.updateDirection(Link.LEFT);
				keyLeft = true;
				if(view.scroll_x != 0 && model.link.x >= 0 && model.link.x <= 700){
					view.scroll_x = view.scroll_x - 700;
				}
				break;
			}

			case KeyEvent.VK_UP:{
				model.link.updateDirection(Link.UP);
				keyUp = true;
				if(view.scroll_y != 0 && model.link.y >= 0 && model.link.y <= 500){
					view.scroll_y = view.scroll_y - 500;
				}
				break;
			}

			case KeyEvent.VK_DOWN:{
				model.link.updateDirection(Link.DOWN);
				keyDown = true;
				if(view.scroll_y != 500 && model.link.y >= 500 && model.link.y <= 1000){
					view.scroll_y = view.scroll_y + 500;
				}
				break;
			}

		}
	}

	public void keyReleased(KeyEvent e)
	{
		switch(e.getKeyCode())
		{/*
			//moveRoomRight
			case KeyEvent.VK_D:{
				keyRight = true;
				break;
			}
			//moveRoomLeft
			case KeyEvent.VK_A:{
				keyLeft = false;
				break;
			}
			case KeyEvent.VK_W:{


				keyUp = false;
				break;
			}
			case KeyEvent.VK_X:{
				keyDown = false;
				break;
			}
			*/
			
			case KeyEvent.VK_Q:{
				System.exit(0);
				break;
			}
			case KeyEvent.VK_ESCAPE:{
				System.exit(0);
				break;
			}

			case KeyEvent.VK_S:{
				Json saveFile = model.marshal();
				saveFile.save("map.json");
				break;
			}

			case KeyEvent.VK_L:{
				Json loadFile = Json.load("map.json");
				model.unmarshal(loadFile);
				break;
			}

			case KeyEvent.VK_E:{
				editMode = !editMode;
				view.printToScreen = !view.printToScreen;
				break;

			}

			case KeyEvent.VK_RIGHT:{
				model.link.direction = Link.RIGHT;
				keyRight = false;
				break;
			}

			case KeyEvent.VK_LEFT:{
				model.link.direction = Link.LEFT;
				keyLeft = false;
				break;
			}

			case KeyEvent.VK_UP:{
				model.link.direction = Link.UP;
				keyUp = false;
				break;
			}

			case KeyEvent.VK_DOWN:{
				model.link.direction = Link.DOWN;
				keyDown = false; 
				break;
			}

			case KeyEvent.VK_P:{
				if(editMode){
					potMode = true;
				}

				break;
			}

			case KeyEvent.VK_CONTROL:{
				//throw boomerang
				model.addBoomerang();
			}

		}
	}
	public void mousePressed(MouseEvent e)
	{
		if(editMode == true && potMode == false){

		int x = e.getX() + view.scroll_x - e.getX() % 50;
		int y = e.getY() + view.scroll_y - e.getY() % 50;

		Tile epic = new Tile(x, y);

		for(int i = 0; i < model.sprites.size(); i++){
			if(model.sprites.get(i).remove(x, y)){
				model.sprites.remove(i);
				return;
			}
		}
		model.sprites.add(epic);


	}

	else if(editMode == true && potMode == true){

		int x = e.getX() + view.scroll_x - e.getX() % 50;
		int y = e.getY() + view.scroll_y - e.getY() % 50;

		Pot epicPot = new Pot(x, y);
		model.sprites.add(epicPot);
	}

	}

	public void keyTyped(KeyEvent e)
	{
	}

	void update()
	{
		model.link.setPrevious(model.link.x, model.link.y);

		if(keyRight == true){
			model.link.x = model.link.x + (int)model.link.speed;
			if(view.scroll_x != 700 && model.link.x >= 700 && model.link.x <= 1400){
				view.scroll_x = view.scroll_x + 700;
			} 
		}
		if(keyLeft == true){
			model.link.x = model.link.x - (int)model.link.speed;
			if(view.scroll_x != 0 && model.link.x >= 0 && model.link.x <= 700){
				view.scroll_x = view.scroll_x - 700;
			}
		}
		if(keyUp == true){
			model.link.y = model.link.y - (int)model.link.speed;
			if(view.scroll_y != 0 && model.link.y >= 0 && model.link.y <= 500){
				view.scroll_y = view.scroll_y - 500;
			}
		}
		if(keyDown == true){
			model.link.y = model.link.y + (int)model.link.speed;
			if(view.scroll_y != 500 && model.link.y >= 500 && model.link.y <= 1000){
				view.scroll_y = view.scroll_y + 500;
			}
		}
	}

	

}
