import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
/**
 * Do not modify this file without permission from your TA.
 **/
public class Controller implements KeyListener{

	private Model model;
	private View view;
	private JButton button; 
	private int stopFlag; 
	Action drawAction;
	final int drawDelay = 30;
	
	@SuppressWarnings("serial")
	public Controller(){
		view = new View(this);
		model = new Model(view.getWidth(), view.getHeight(), view.getImageWidth(), view.getImageHeight());
		button = view.getButton(); 
		stopFlag = 0; 
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				stopFlag = (stopFlag + 1) % 2; 
			}
		}); 
		drawAction = new AbstractAction(){
    		public void actionPerformed(ActionEvent e){
    			if (stopFlag == 0) {
    				model.updateLocationAndDirection();
    				view.update(model.getX(), model.getY(), model.getDirect(),0);
    			}

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

	@Override
	public void keyPressed(KeyEvent e) {
		System.out.println("A key has been pressed.");
		if(e.getKeyCode()==KeyEvent.VK_J) {
			view.update(model.getX(), model.getY(), model.getDirect(),1);			
		}else if(e.getKeyCode()==KeyEvent.VK_D) {
			view.update(model.getX(), model.getY(), model.getDirect(),2);
		}
		
		
	}

	@Override
	public void keyReleased(KeyEvent k) {
	}

	@Override
	public void keyTyped(KeyEvent k) {
	}
}
