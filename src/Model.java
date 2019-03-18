/**
 * Model: Contains all the state and logic
 * Does not contain anything about images or graphics, must ask view for that
 *
 * has methods to
 *  detect collision with boundaries
 * decide next direction
 * provide direction
 * provide location
 **/
class Model{
	
	private int frameWidth;
    private int frameHeight;
    private int imgWidth;
    private int imgHeight;
    private int x = 0;
    private int y = 0;
    private final int xIncr = 8;
    private final int yIncr = 2;
    private Direction currentDir = Direction.SOUTHEAST;
	
	public Model(int frameWidth,int frameHeight,int imgWidth,int imgHeight) {
		this.frameWidth = frameWidth;
		this.frameHeight = frameHeight;
		this.imgWidth = imgWidth;
		this.imgHeight = imgHeight;
	}
	
	Direction direction;
	public void updateLocationAndDirection() {
		if (checkOutOfBounds()) {
			changeDirection();
		}
		move();
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public Direction getDirect() {
		return currentDir;
	}
	
	private boolean checkOutOfBounds() {
		return (x > (frameWidth - imgWidth)) || (y > (frameHeight - imgHeight)) || (y < 0) || (x < 0);
	}
	
	private void changeDirection() {
		currentDir = Direction.values()[(currentDir.ordinal() + 1) % 8];
	}
	
	private void move() {
		if(currentDir.getName().contains("north")) {
			y -= yIncr;
		}
		if(currentDir.getName().contains("south")) {
			y += yIncr;
		}
		if(currentDir.getName().contains("east")) {
			x += xIncr;
		}
		if(currentDir.getName().contains("west")) {
			x -= xIncr;
		}
	}
	
	
}
