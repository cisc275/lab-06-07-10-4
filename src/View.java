import java.awt.CardLayout;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.Timer;


/**
 * View: Contains everything about graphics and images
 * Know size of world, which images to load etc
 *
 * has methods to
 * provide boundaries
 * use proper images for direction
 * load images for all direction (an image should only be loaded once!!! why?)
 **/


class View extends JFrame {
	private JFrame frame;
	private JButton stopButton;
	private final String[] orcMoveFiles = {"orc-images/orc_forward_north.png",
			"orc-images/orc_forward_northeast.png",
			"orc-images/orc_forward_east.png",
			"orc-images/orc_forward_southeast.png",
			"orc-images/orc_forward_south.png",
			"orc-images/orc_forward_southwest.png",
			"orc-images/orc_forward_west.png",
	"orc-images/orc_forward_northwest.png"};
	BufferedImage[][] pics;
	final int frameCount = 10; 
	private int picNum; 
	private final int frameWidth = 800;
	private final int frameHeight = 800;
	private final int imgWidth = 165;
	private final int imgHeight = 165;
	private Direction direction;
	private int x;
	private int y;
	final int frameStartSize = 800;
	final int drawDelay = 30; //msec
	private Model model;

	DrawPanel drawPanel = new DrawPanel();
	Action drawAction;

	public View() {}

	public View(Model newModel) {
		/*	frame = new JFrame();
    	frame.getContentPane().add(this);
    	frame.setBackground(Color.gray);
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	frame.setSize(frameWidth, frameHeight);
    	frame.setVisible(true);
		 */
		model = newModel;	

		drawAction = new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				drawPanel.repaint();
				model.updateLocationAndDirection();
				update(model.getX(), model.getY(), model.getDirect());

			}
		};
		

		stopButton = new JButton("Start/Stop");
		stopButton.setBounds(100,100,140,40);
		add(stopButton);
		add(drawPanel);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBackground(Color.gray);
		setSize(frameStartSize, frameStartSize);
		setLayout(new FlowLayout());
		setVisible(true);
		pack();

		direction = Direction.SOUTHEAST;
		x=0;
		y=0;

		pics = new BufferedImage[8][10];

		for(int j = 0; j < 8; j++)
		{
			BufferedImage img = createImage(orcMoveFiles[j]);
			for(int i = 0; i < frameCount; i++)
				pics[j][i] = img.getSubimage(imgWidth*i, 0, imgWidth, imgHeight);
		}
	}

	public int getWidth(){
		return frameWidth;
	}

	public int getHeight() {
		return frameHeight;
	}

	public int getImageWidth(){
		return imgWidth;
	}

	public int getImageHeight(){
		return imgHeight;
	}

	public void update(int x, int y, Direction d) {
		direction = d;
		this.x = x;
		this.y = y;
		/*	frame.repaint();
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
			} */
		/*EventQueue.invokeLater(new Runnable(){
			public void run(){
				View a = new View();
				Timer t = new Timer(a.drawDelay, a.drawAction);
				t.start();
			}
		});*/



	}

	public void paint(Graphics g) {
		picNum = (picNum + 1) % frameCount;
		//System.out.println(x);
		g.drawImage(pics[direction.ordinal()][picNum], x, y, Color.gray, this);
	}

	@SuppressWarnings("serial")
	private class DrawPanel extends JPanel {
		int picNum = 0;

		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.setColor(Color.gray);
			picNum = (picNum + 1) % frameCount;
			g.drawImage(pics[direction.ordinal()][picNum], x, y, Color.gray, this);
		}

		public Dimension getPreferredSize() {
			return new Dimension(frameStartSize, frameStartSize);
		}
	}





	private BufferedImage createImage(String file){
		BufferedImage bufferedImage;
		try {
			bufferedImage = ImageIO.read(new File(file));
			return bufferedImage;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;

	}


}
