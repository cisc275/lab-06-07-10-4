import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.Timer;

/**
 * Do not modify this file without permission from your TA.
 **/
public class Controller {

	private Model model;
	private View view;
	
	Action drawAction;
	final int drawDelay = 30;
	
	public Controller(){
		view = new View();
		model = new Model(view.getWidth(), view.getHeight(), view.getImageWidth(), view.getImageHeight());
		
		drawAction = new AbstractAction(){
    		public void actionPerformed(ActionEvent e){
    			model.updateLocationAndDirection();
				view.update(model.getX(), model.getY(), model.getDirect());

    		}
    	};
	}
	
        //run the simulation
	public void start(){
		
		EventQueue.invokeLater(new Runnable(){
			public void run(){
				Main a = new Main();
				Timer t = new Timer(drawDelay, drawAction);
				t.start();
			}
		});

	}
}
