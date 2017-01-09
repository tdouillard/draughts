import java.util.ArrayList;
import java.util.List;

public class DraughtsGameImpl extends DraughtsGame {
	public static final int MAX_COLS = 10 ;
	public static final int  MAX_ROWS = 10;
	List<List<pawnColour>> board = new ArrayList<>(MAX_COLS);
	
	public DraughtsGameImpl(){
		initGame();
	}
	
	public void initGame(){
		this.board =  new ArrayList<>(MAX_COLS);
		for (int index = 0; index <=MAX_COLS;index++){
			board.add(new ArrayList<pawnColour>(MAX_ROWS));
		}
	}
}