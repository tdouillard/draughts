package Main;

import java.util.ArrayList;
import java.util.List;

public class DraughtsGameImpl extends DraughtsGame {
	public static final int MAX_COLS = 10;
	public static final int MAX_ROWS = 10;
	ArrayList<ArrayList<PawnColour>> board = new ArrayList<>(MAX_COLS);

	public DraughtsGameImpl() {
		this.blackPawnNumber = 0;
		this.whitePawnNumber = 0;
		initGame();
	}

	/**
	 * Create and fill the board with pawnColour
	 */
	public void initGame() {
		for (int index = 0; index <= MAX_COLS - 1; index++) {
			board.add(coloredArray(index));
		}
	}

	/**
	 * create a row with pawnColour on right square set null on position that
	 * shouldn´t possess a pawn
	 * 
	 * test: check if 1 on 2 square possess the right pawn color except on the
	 * middle of the boardGame
	 * 
	 * @param colIndex
	 */
	public ArrayList<PawnColour> coloredArray(int colIndex) {
		ArrayList<PawnColour> rowArray = new ArrayList<>();
		PawnColour currentPawnColour = PawnColour.BLACK;
		boolean evenColIndex = false;
		boolean evenRowIndex = false;
		if (colIndex % 2 == 0) {
			evenColIndex = true;
		}
		for (int rowIndex = 0; rowIndex <= MAX_ROWS - 1; rowIndex++) {
			evenRowIndex = false;
			if (rowIndex % 2 == 0) {
				evenRowIndex = true;
			}
			if (((rowIndex < (((MAX_ROWS) / 2) - 1)) || (rowIndex > (((MAX_ROWS) / 2))))
					&& ((evenRowIndex && !evenColIndex) || (!evenRowIndex && evenColIndex))) {
				if (rowIndex > (MAX_ROWS / 2) - 1) {
					currentPawnColour = PawnColour.WHITE;
				}
				rowArray.add(currentPawnColour);
				if(currentPawnColour.equals(PawnColour.WHITE)){
					this.whitePawnNumber++;
				}else{
					this.blackPawnNumber++;
				}
				System.out.println("" + evenRowIndex + evenColIndex + colIndex + rowIndex + currentPawnColour + "");
			} else {
				rowArray.add(null);
			}
		}
		return rowArray;
	}
	
	

	@Override
	public boolean play(int startColIndex, int startRowIndex, int endColIndex, int endRowIndex) {
		try {
			Pawn pawn = (Pawn)this.getCell(startColIndex,startRowIndex);
			Object endCell = this.getCell(endColIndex, endRowIndex);
			PawnStatus status = pawn.getStatus();
			PawnColour color = pawn.getColour();
			return movePawn(startColIndex,startRowIndex,status,color,endColIndex,endRowIndex,endCell);
			
		}catch(IndexOutOfBoundsException e){
			System.out.println("one of the positon is out of Board : "+e.getMessage());
			return false;
		}
		catch (NullPointerException e) {
			System.out.println("start Square is empty, you couldn´t move something empty : "+e.getMessage()); 
			return false;
		}
	}

	/**
	 * 
	 * @param startColIndex
	 * @param startRowIndex
	 * @param status
	 * @param color
	 * @param endColIndex
	 * @param endRowIndex
	 * @param endCell
	 * @return
	 */
	private boolean movePawn(int startColIndex, int startRowIndex, PawnStatus status, PawnColour color, int endColIndex,
			int endRowIndex, Object endCell) {	
		return true;
	}

	@Override
	public PawnColour getWinner(int numberPawn) {
		if(this.blackPawnNumber == 0){
			return PawnColour.WHITE;
		} else if(this.whitePawnNumber == 0){
			return PawnColour.BLACK;
		} else{
			return null;
		}
	}
	
	/**
	 * get cell content : null or pawn
	 * @return
	 */
	@Override
	public Object getCell(int colIndex,int rowIndex){
		try{ 
			return this.board.get(colIndex).get(rowIndex);
		}catch(IndexOutOfBoundsException e){
			System.out.println("one of the positon is out of Board : "+e.getMessage());
			return false;
		}
	}
	
	/**
	 * number of black pawn on the board
	 * @return
	 */
	public int getBlackPawnNumber(){
		return this.blackPawnNumber;
	}
	
	/**
	 * Number of white pawn on the board
	 * @return
	 */
	public int getWhitePawnNumber(){
		return this.whitePawnNumber;
	}
	

	/**
	 * get number of pawn on the board
	 * 
	 * @return
	 */
	public int getBoardPawnNumber(PawnColour color) {
		int nbPawn = 0;
		for (ArrayList<PawnColour> arrayList : board) {
			for (Object pawnColor : arrayList) {
				if (color == null) {
					if (pawnColor != null) {
						nbPawn++;
					}
				} else {
					if (pawnColor == color) {
						nbPawn++;
					}
				}
			}
		}
		return nbPawn;
	}
	
}