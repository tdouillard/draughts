package Main;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public abstract class DraughtsGame {

	public static final int PAWN_NUMBER = 40;
	public int blackPawnNumber;
	public int whitePawnNumber;
	
	public DraughtsGame(){
		
	}

	public abstract boolean play(int startColIndex, int startRowIndex, int endColIndex, int endRowIndex); 
	
	public abstract Object getCell(int posX , int posY);
	
	public abstract PawnColour getWinner(int numberPawn);
	

}
