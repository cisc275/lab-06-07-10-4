import java.awt.EventQueue;

import javax.swing.Timer;

/**
 * Do not modify this file without permission from your TA.
 **/
public class Controller {

	private Model model;
	private View view;
	
	public Controller(){
		view = new View();
		model = new Model(view.getWidth(), view.getHeight(), view.getImageWidth(), view.getImageHeight());
	}
	
        //run the simulation
	public void start(){
		EventQueue.invokeLater(new Runnable(){
			public void run(){
				View a = new View();
				Timer t = new Timer(a.drawDelay, a.drawAction);
				model.updateLocationAndDirection();
				view.update(model.getX(), model.getY(), model.getDirect());
				t.start();
			}
		});
		/*for(int i = 0; i < 5000; i++)
		{
			//increment the x and y coordinates, alter direction if necessary
			model.updateLocationAndDirection();
			//update the view
			view.update(model.getX(), model.getY(), model.getDirect());
			
			
		}*/
	}
}
