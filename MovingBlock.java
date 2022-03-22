public class MovingBlock {
	
	private int x = 0;
	private int y = 0;
	private int number = 0;
	private boolean combined = false;
	
	public MovingBlock(int x, int y, int number) {
		this.x = x;
		this.y = y;
		this.number  = number;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public boolean getCombine() {
		return combined;
	}
	
	public void setCombine(boolean bool) {
		combined = bool;
	}
	
	public int getNumber() {
		return number;
	}
	
	
	public void left(MovingBlock[][] board) {
		if (x > 0) {
		while (board[x-1][y] == null) {
			this.x -=1;
			if (this.x == 0) {
				break;
			}
			//System.out.println(x);
		}
	}
	}
	
	public void right(MovingBlock[][] board) {
		if (x < board.length - 1) {
		while (board[x+1][y] == null) {
			this.x +=1;
			if (this.x == board.length - 1) {
				break;
			}
			//System.out.println(x);
		}
		}
	}
	
	
	public void down(MovingBlock[][] board) {
		if (y < board.length - 1) {
		while (board[x][y+1] == null) {
			this.y +=1;
			if (this.y == board.length - 1) {
				break;
			}
			//System.out.println(y);
		}
		}
	}
	
	public void up(MovingBlock[][] board) {
		if (y > 0) {
		while (board[x][y-1] == null) {
			this.y -=1;
			if (this.y == 0) {
				break;
			}
			//System.out.println(x);
		}
	}
	}
	
	public void changeNumber(int newNumber) {
		number = newNumber;
	}
	
	public void setX(int newX) {
		x = newX;
	}
	
	public void setY(int newY) {
		y = newY;
	}
	
	@Override public String toString() {
		return "Number:" + number + " X: " + x + " Y: " + y;
		
	}

}
