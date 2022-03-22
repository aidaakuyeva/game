enum Dir {
	UP,
	DOWN,
	LEFT,
	RIGHT
}

public class Board {
	
	MovingBlock[][] arr = null;
	
	private int nullCells = 0;
	private boolean lost = false;
	private int score = 0;
	//todo: keep track of score, start end screen
	//check sum function not always moving up and doing correctly 
	
	
	public Board() {
		this.arr = new MovingBlock[4][4];
	    for (int i = 0; i < 4; i++) {
	    	for (int j = 0; j < 4; j++) {
	    		if (arr[i][j] == null) {
	    		nullCells += 1;
	    		}
	    	}
	    }
	    
	    if (nullCells == 16) {
	    	lost = true;
	    }
	}
	
	public MovingBlock[][] getArr() {
		return arr;
	}
	
	public boolean loseCondition() {
		return lost;
	}
	
	public int getScore() {
		return score;
	}
	
	public static void printBoard(Board board) {
		for (int i = 0; i < 4; i++) {
			//System.out.println();
		for (int j = 0; j < 4; j++) {
			if (board.getArr()[j][i] == null) {
				//System.out.print(0 + " ");
			} else {
				
		//System.out.print(board.getArr()[j][i].getNumber() + " ");
			}
		}
		}
	}
	
	public boolean checkArray(MovingBlock[][] board) {
		for  (int i = 0; i <=3; i++) {
			for (int j = 0; j<=3; j++) {
				MovingBlock curr = board[i][j];
				MovingBlock current =  arr[i][j];
				//System.out.println("ORIGINAL" + curr);
				//System.out.println();
				//System.out.println(current);
				
				if (((curr == null) && (current != null)) || ((current == null) && 
				(curr != null))){
					//System.out.println("return false");
					return false;
					
				}
				if (curr != null && current !=null) {
					if (curr.getNumber() != current.getNumber()) {
						//System.out.println("return false case 2");
						return false;
					}
				}
			}
		}
		//System.out.println("return true");
		return true;
	}
	public void update() {
    	//System.out.println("UPDATE!!");
		
		if (PennDraw.hasNextKeyTyped()) {
            char c = PennDraw.nextKeyTyped();
            if (c == 'A') {
            	moveAllBlocksLeft();
            	//System.out.println("UPDATE!!");
            	//PennDraw.advance();
            } else if (c == 'D') {
              	moveAllBlocksRight();
            } else if (c == 'S') {
              	moveAllBlocksUp();
            } else if (c == 'W') {
              	moveAllBlocksDown();
            } 
		}
            
	}
	
	public void draw() {
        //draw background
        PennDraw.clear(186, 186, 186);
        
        //draw main box
        PennDraw.setPenColor(34, 34, 34);
        PennDraw.filledRectangle(0.5, 0.5, 0.5, 0.5);
        for (int i = 0; i < 4; i++) {
        	for (int j = 0; j < 4; j++) {
        		if (arr[i][j] != null) {
        	        PennDraw.setPenColor(64, 4, 124);
        	        PennDraw.filledRectangle(0.125 + 0.25 * i, 0.125 + 0.25 * j, 0.125, 0.125);
        	        PennDraw.setPenColor(PennDraw.WHITE);
        	        PennDraw.text(0.125 + 0.25 * i, 0.125 + 0.25 * j, " " + arr[i][j].getNumber());
        	        
        		}
        	}
        }
        
		if (lost) {
	        PennDraw.setPenColor(134, 4, 134);
	        PennDraw.text(0.75, 0.25, "You lost the game");
		}
		
        PennDraw.setPenColor(134, 4, 134);
        PennDraw.text(0.5, 0.5, "Score:" + score);
	}
	
	public int randomGenerate() {
		int cell = 0;
		double num = Math.random();
		if (num <= 0.5) {
			cell = 2;
		} else {
			cell = 4;
		}
		return cell;
	}
	
	public void lost() {
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[i].length; j++) {
				if (arr[i][j] == null) {
					lost = false;
				}
				}
			}
		
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[i].length; j++) {
				//up, left
				if (j != 0) {	
				if (arr[i][j] == arr[i][j-1]) {
					lost = false;
				}
				}
				
				if (i != 0) {
					if (arr[i][j] == arr[i-1][j]) {
						lost = false;
				}
				}
			}
		}
		
		lost = true;
	}
	
	//create a 2 or 4 new block
	public void createBlock() {
		//does not overlap with other blocks + called when cursor moved 
		int x = randomPosition();
		int y = randomPosition();
		int num = randomGenerate();
		
		//not efficient , for later: keep track of empty spaces
		while (arr[x][y] != null) {
			x = randomPosition();
			y = randomPosition();
		}
		
		MovingBlock mb = new MovingBlock(x, y, num);
		arr[x][y] = mb;
	}
	
	public int randomPosition() {
		return (int) (4 * Math.random());
	}
	
	public void placeBlock(MovingBlock mb) {
		int x = mb.getX();
		int y = mb.getY();
		arr[x][y] = mb;
	}
	
	public void sumBlocksLeft(MovingBlock curr) {
		if (curr == null) {
			return;
		}
		int num = curr.getNumber();
		int x = curr.getX();
		int y = curr.getY();
		
		
		while (true) {
		if (x == 0) {
			return;
		}
		
		MovingBlock m = arr[x-1][y];

		if (m == null) {
			x-=1;
			curr.setX(x);
			arr[x+1][y] = null;
			arr[x][y] = curr;
		} else {
			int number = m.getNumber();
			if (number == num && !m.getCombine() && !curr.getCombine()) {
					x-=1;
					curr.setX(x);
					curr.changeNumber(2 * number);
					arr[x+1][y] = null;
					arr[x][y] = curr;
					curr.setCombine(true);
					m.setCombine(true);
					score += number;
			} else {
				return;
			}
					
		}
		}
		}
	
	public void moveAllBlocksLeft() {
		MovingBlock[][] newarr = new MovingBlock[4][4];
		
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j< 4; j++) {
				newarr[i][j] = arr[i][j];
			}
		}
		
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[i].length; j++) {
				sumBlocksLeft(arr[i][j]);
				}
			}
		
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[i].length; j++) {
				if (arr[i][j] != null) {
					arr[i][j].setCombine(false);
				}
				}
			}
		
	
		if (!checkArray(newarr)) {
			createBlock();
		}
		}
	
	
	public void sumBlocksRight(MovingBlock curr) {
		if (curr == null) {
			return;
		}
		int num = curr.getNumber();
		int x = curr.getX();
		int y = curr.getY();
		
		
		while (true) {
		if (x == 3) {
			return;
		}
		
		MovingBlock m = arr[x+1][y];

		if (m == null) {
			x+=1;
			curr.setX(x);
			arr[x-1][y] = null;
			arr[x][y] = curr;
		} else {
			int number = m.getNumber();
			if (number == num && !m.getCombine() && !curr.getCombine()) {
					x+=1;
					curr.setX(x);
					curr.changeNumber(2 * number);
					score += number;
					arr[x-1][y] = null;
					arr[x][y] = curr;
					m.setCombine(true);
					curr.setCombine(true);
			} else {
				return;
			}
					
		}
		}
		}
	
	public void moveAllBlocksRight() {
		MovingBlock[][] newarr = new MovingBlock[4][4];
		
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j< 4; j++) {
				newarr[i][j] = arr[i][j];
			}
		}
		
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[i].length; j++) {
				sumBlocksRight(arr[i][j]);
				}
			}
		
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[i].length; j++) {
				if (arr[i][j] != null) {
					arr[i][j].setCombine(false);
				}
				}
			}
	
	
		if (!checkArray(newarr)) {
			createBlock();
		}
		}
	
	public void sumBlocksDown(MovingBlock curr) {
		if (curr == null) {
			return;
		}
		int num = curr.getNumber();
		int x = curr.getX();
		int y = curr.getY();
		
		
		while (true) {
		if (y == 3) {
			return;
		}
		
		MovingBlock m = arr[x][y+1];

		if (m == null) {
			y+=1;
			curr.setY(y);
			arr[x][y-1] = null;
			arr[x][y] = curr;
		} else {
			int number = m.getNumber();
			if (number == num && !m.getCombine() && !curr.getCombine()) {
					y+=1;
					curr.setY(y);
					curr.changeNumber(2 * number);
					score += number;
					arr[x][y-1] = null;
					arr[x][y] = curr;
					m.setCombine(true);
					curr.setCombine(true);
			} else {
				return;
			}
					
		}
		}
		}
	
	public void moveAllBlocksDown() {
		MovingBlock[][] newarr = new MovingBlock[4][4];
		
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j< 4; j++) {
				newarr[i][j] = arr[i][j];
			}
		}
		
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				newarr[i][j] = arr[i][j];
			}
		}
		
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[i].length; j++) {
				sumBlocksDown(arr[i][j]);
				}
			}
		
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[i].length; j++) {
				if (arr[i][j] != null) {
					arr[i][j].setCombine(false);
				}
				}
			}
	
	
		if (!checkArray(newarr)) {
			createBlock();
		}
		}
	
	public void sumBlocksUp(MovingBlock curr) {
		if (curr == null) {
			return;
		}
		int num = curr.getNumber();
		int x = curr.getX();
		int y = curr.getY();
		
		
		while (true) {
		if (y == 0) {
			return;
		}
		
		MovingBlock m = arr[x][y-1];

		if (m == null) {
			moveUntilHit(curr, Dir.UP);
		} else {
			int number = m.getNumber();
			if (number == num && !m.getCombine() && !curr.getCombine()) {
					y-=1;
					curr.setY(y);
					curr.changeNumber(2 * number);
					score += number;
					arr[x][y+1] = null;
					arr[x][y] = curr;
					curr.setCombine(true);
					m.setCombine(true);
			} else {
				continue;
			}
					
		}
		}
		}
	
	public void moveUntilHit(MovingBlock curr, Dir dir) {
		switch(dir) {
		case UP:
			int num = curr.getNumber();
			int x = curr.getX();
			int y = curr.getY();
			if (y == 0) {
				return;
			}
			MovingBlock other = arr[x][y - 1];
			
			int var = 0;
	
			while (other != null) { 
				var+=1;
				curr.setY(y-1);
				other = arr[x][curr.getY()-1];
			}
			System.out.println("" + var);
			
			break;
		case DOWN:
			break;
		case LEFT:
			break;
		case RIGHT:
			break;
		}
	}
	
	public void moveAllBlocksUp() {
		MovingBlock[][] newarr = new MovingBlock[4][4];
		
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j< 4; j++) {
				newarr[i][j] = arr[i][j];
			}
		}
		
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[i].length; j++) {
				sumBlocksUp(arr[i][j]);
				}
			}
		
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[i].length; j++) {
				if (arr[i][j] != null) {
					arr[i][j].setCombine(false);
				}
				}
			}
	
	
		if (!checkArray(newarr)) {
			createBlock();
		}
	}
	

	
	

}
