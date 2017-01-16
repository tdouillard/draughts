package Main;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class DraughtsGame {

	public int numberBlack;
	public int numberWhite;

	public DraughtsGame(){
	 	
	}

	public void play (PawnColour colour, int posX , int posY){

	}


	public void getCell(int posX , int posY){

	}

	public PawnColour whoWon(int numberPawn){
		if(numberBlack == 0){
			return PawnColour.WHITE;
		} else if(numberWhite == 0){
			return PawnColour.BLACK;
		}
		return null;
	}

}
