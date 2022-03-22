import java.util.Arrays;

public class Game {
	
    public static void main(String[] args) {
 
        PennDraw.enableAnimation(60);
        Board board = new Board();
		//board.createBlock();
		//board.createBlock();
		MovingBlock b1 = new MovingBlock(3, 2, 4);
		MovingBlock b2 = new MovingBlock(3, 3, 4);
		MovingBlock b3 = new MovingBlock(3, 0, 8);
		board.placeBlock(b1);
		board.placeBlock(b2);
		board.placeBlock(b3);
        
        while (true) {
                PennDraw.clear();
                board.update();
                board.draw();
                PennDraw.advance();
            }
    }
           
           
		
//				MovingBlock b = new MovingBlock(0, 3, 16);
//				MovingBlock b2 = new MovingBlock(0, 2, 16);
//				MovingBlock b3 = new MovingBlock(0, 1, 8);
//				board.placeBlock(b);
//				board.placeBlock(b2);
//				board.placeBlock(b3);
//				board.createBlock();
//				System.out.println("Initial state : ");
//				printBoard(board);
//				board.sumBlocksLeft(b2);
//				board.moveAllBlocksUp();
//				board.moveAllBlocksLeft();
//				board.moveAllBlocksRight();
//				board.moveAllBlocksDown();
//				System.out.println();
//				System.out.println();
//				
//				System.out.println("Up: ");
//				printBoard(board);

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
	
    /**
     * Inputs: none
     * Outputs: none
     * Description: Draws the instructions and controls on the start screen
    */
    public static void drawControls() {
        PennDraw.setPenColor();
        
        PennDraw.setFontBold();
        PennDraw.text(0.5, 0.65, "Press any key to start");
        PennDraw.setFontPlain();
        
        PennDraw.text(0.5, 0.6, "Left: L");
        PennDraw.text(0.5, 0.55, "Right: R");
        PennDraw.text(0.5, 0.5, "Down: D");
        PennDraw.text(0.5, 0.45, "Up: U");
    }
}
