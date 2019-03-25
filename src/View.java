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


class View extends JPanel{
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
	private final String[] orcJumpFiles = {"orc-images/orc_jump_north.png",
			"orc-images/orc_jump_northeast.png",
			"orc-images/orc_jump_east.png",
			"orc-images/orc_jump_southeast.png",
			"orc-images/orc_jump_south.png",
			"orc-images/orc_jump_southwest.png",
			"orc-images/orc_jump_west.png",
	"orc-images/orc_jump_northwest.png"};
	BufferedImage[][] pics;
	BufferedImage[][] jumpPics;
	final int frameCount = 10;
	final int jumpFrameCount = 8; 
	private int picNum; 
	private final int frameWidth = 500;
	private final int frameHeight = 300;
	private final int imgWidth = 165;
	private final int imgHeight = 165;
	private Direction direction;
	private int x;
	private int y;
	private int state;
	final int drawDelay = 30; //msec

	DrawPanel drawPanel = new DrawPanel();

	public View() {
		frame = new JFrame();
		frame.setFocusable(true);
		//frame.getContentPane().add(this);
		this.stopButton = new JButton("Start/Stop"); 
		drawPanel.add(stopButton);
		frame.add(drawPanel);
		frame.setBackground(Color.gray);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(frameWidth, frameHeight);
		frame.setVisible(true);
		frame.pack();


		direction = Direction.SOUTHEAST;
		x=0;
		y=0;

		pics = new BufferedImage[8][10];

		for(int j = 0; j < 8; j++){
			BufferedImage img = createImage(orcMoveFiles[j]);
			for(int i = 0; i < frameCount; i++)
				pics[j][i] = img.getSubimage(imgWidth*i, 0, imgWidth, imgHeight);
		}
		
		jumpPics = new BufferedImage[8][8];
		
		for(int j = 0; j < 8; j++){
			BufferedImage img = createImage(orcJumpFiles[j]);
			for(int i = 0; i < jumpFrameCount; i++)
				jumpPics[j][i] = img.getSubimage(imgWidth*i, 0, imgWidth, imgHeight);
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
	public JButton getButton(){
		return this.stopButton; 
	}
	public void update(int x, int y, Direction d, int s) {
		direction = d;
		this.x = x;
		this.y = y;
		state = s;
		frame.repaint();


	}

	/*public void paint(Graphics g) {
		picNum = (picNum + 1) % frameCount;
		g.drawImage(pics[direction.ordinal()][picNum], x, y, Color.gray, this);
	}*/

	@SuppressWarnings("serial")
	private class DrawPanel extends JPanel {
		int picNum = 0;

		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.setColor(Color.gray);
			if(state==0) {
				picNum = (picNum + 1) % frameCount;
				g.drawImage(pics[direction.ordinal()][picNum], x, y, Color.gray, this);
			}else if(state==1) {
				picNum = (picNum + 1) % jumpFrameCount;
				g.drawImage(jumpPics[direction.ordinal()][picNum], x, y, Color.gray, this);
				if(picNum==jumpFrameCount-1) {
					state = 0;
				}
			}else if(state==2) {
				picNum = (picNum + 1) % jumpFrameCount;
				g.drawImage(jumpPics[direction.ordinal()][picNum], x, y, Color.gray, this);
				if(picNum==jumpFrameCount-1) {
					state = 0;
				}
			}
			
			
		}

		public Dimension getPreferredSize() {
			return new Dimension(frameWidth, frameHeight);
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
