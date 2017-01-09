package Main;


import java.util.ArrayList;
import java.util.List;
public class DraughtsGameImpl extends DraughtsGame {
	public static final int MAX_COLS = 10 ;
	public static final int  MAX_ROWS = 10;
	List<List<PawnColour>> board = new ArrayList<>(MAX_COLS);
	
	public DraughtsGameImpl(){
		initGame();
	}
	
	/**
	 * Create and fill the board with pawnColour
	 */
	public void initGame(){
		this.board =  new ArrayList<>(MAX_COLS);
		for (int index = 0; index <=MAX_COLS;index++){
			board.add(new ArrayList<PawnColour>(MAX_ROWS));
		}
	}
		
}