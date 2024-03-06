/*
 * Name: Donna Thakadipuram
 * Date: 3/23/2023
 * Description: Assignment 5. This class is the main class that runs the program. It
 * creates the model, controller, and view and then runs the program.
 */
import javax.swing.JFrame;
import java.awt.Toolkit;

public class Game extends JFrame
{
	Model model = new Model();
	Controller controller = new Controller(model);
	View view = new View(controller, model);
	

	
	
	public Game()
	{
			

		view.addMouseListener(controller);
		this.addKeyListener(controller);

		this.setTitle("A5 Link is Doing Pot");
		this.setSize(700, 500);
		this.setFocusable(true);
		this.getContentPane().add(view);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);


	}

	public void run()
{
	Json loadFile = Json.load("map.json");
	model.unmarshal(loadFile);

	while(true)
	{
		controller.update();
		model.update();
		view.repaint(); // This will indirectly call View.paintComponent
		Toolkit.getDefaultToolkit().sync(); // Updates screen

		// Go to sleep for 50 milliseconds
		//changed milliseconds to 40

		//loads map saved in json file
		try
		{
			Thread.sleep(40);
		} catch(Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

}

	public static void main(String[] args)
	{
		Game g = new Game();
		g.run();
	}
}
