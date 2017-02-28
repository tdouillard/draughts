package org.tdouillard.draughts.core;



import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import java.lang.Math;

public class DraughtsGameImpl extends DraughtsGame {
	public static final int MAX_COLS = 10;
	public static final int MAX_ROWS = 10;
	public static final int MIN_PAWNS = 0;
	ArrayList<ArrayList<Pawn>> board = new ArrayList<>(MAX_COLS);

	public DraughtsGameImpl() {
		//init player colour
		this.pawnNumbersByColor = new HashMap<PawnColour, Integer>();
		for (PawnColour colour : PawnColour.values()) {
			pawnNumbersByColor.put(colour, MIN_PAWNS);
		}
	
		initGame();
	}

	/**
	 * Create and fill the board with pawnColour
	 */
	public void initGame() {
		for (int index = 0; index <= MAX_COLS - 1; index++) {
			board.add(createRowArray(index));
		}
	}
	
	
	public void printBoard(){
		ArrayList<String> strings = new ArrayList<>();
		for(ArrayList<Pawn> pawnList:board){
			String rowString = "|";
			for(Pawn pawn : pawnList){
				if(pawn != null){
					if(pawn.getColour() == PawnColour.BLACK){
						rowString += "X";
					}else{
						rowString += "O";
					}
				}else{
						rowString += " ";
				}
				rowString += "|";
			}
			strings.add(rowString);
		}
		for(int index= board.size() - 1;index >=0;index--){
			System.out.println(strings.get(index));
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
	public ArrayList<Pawn> createRowArray(int colIndex) {
		ArrayList<Pawn> rowArray = new ArrayList<>();
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
				rowArray.add(new Pawn(currentPawnColour));
				int count = this.pawnNumbersByColor.containsKey(currentPawnColour)
						? this.pawnNumbersByColor.get(currentPawnColour) : 0;
				this.pawnNumbersByColor.put(currentPawnColour, count + 1);
//				 System.out.println("" + evenRowIndex + evenColIndex +
//				// colIndex + rowIndex + currentPawnColour + "");
			} else {
				rowArray.add(null);
			}
		}
		return rowArray;
	}

	/**
	 * check if the game is playable and the validity of the positions before
	 * moving the pawn
	 *
	 */
	@Override
	public boolean play(int startColIndex, int startRowIndex, int endColIndex, int endRowIndex) {
		try {
			if (getWinner() == null) {
				Pawn startCell = (Pawn) this.getCell(startColIndex, startRowIndex);
				Pawn endCell = this.getCell(endColIndex, endRowIndex);
				// check if pawn start object not null by accessing to one of
				// his attribute
				PawnStatus status = startCell.getStatus();
				return movePawn(startColIndex, startRowIndex, endColIndex, endRowIndex, startCell, endCell);
			} else {
				return false;
			}
		} catch (IndexOutOfBoundsException e) {
			throw new GameException("one of the positon is out of Board : " + e.getMessage());
		} catch (NullPointerException e) {
			throw new GameException("start Square is empty, you can´t move something empty : " + e.getMessage());
		}
	}

	/**
	 * TODO : optimize Queen detection by changing pawn direction movement
	 * mecanics
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
	private boolean movePawn(int startColIndex, int startRowIndex, int endColIndex, int endRowIndex, Pawn startCell,
			Pawn endCell) {
		int colMovement = endColIndex - startColIndex;
		int rowMovement = endRowIndex - startRowIndex;
		int colMovementSign = Integer.signum(colMovement);
		int rowMovementSign = Integer.signum(rowMovement);

		// movement is in diagonal and in the right direction
		if (Math.abs(rowMovement) == Math.abs(colMovement)) {
				// pawn try to move on a non adjacent cell
				if ((startCell.status == PawnStatus.PAWN) && ((rowMovement != Pawn.MAX_PAWN_MOVEMENT * rowMovementSign) || (startCell.getMovementDirection() != rowMovementSign))) {
					return false;
				}

				if (pawnEaten(startColIndex, startRowIndex, endColIndex, endRowIndex, colMovement, rowMovementSign,
						colMovementSign, startCell, endCell)) {
					setBoardSquare(endColIndex, endRowIndex, null);
					endColIndex = endColIndex + colMovementSign;
					endRowIndex = endRowIndex + rowMovementSign;
				}

				// pawn reach the board limit
				if (endRowIndex == MAX_ROWS || endRowIndex == 0) {
					startCell.becomeQueen();
				}
				setBoardSquare(endColIndex, endRowIndex, startCell);
				setBoardSquare(startColIndex, startRowIndex, null);
				printBoard();
				return true;
			}
		return false;

	}

	/*
	 * TODO : toStrng function for position ?
	 * 
	 */
	private boolean pawnEaten(int startColIndex, int startRowIndex, int endColIndex, int endRowIndex, int colMovement,
			int rowMovementSign, int colMovementSign, Pawn startCell, Pawn endCell) {
		boolean hasEaten = false;
		if (endCell != null) {
			PawnColour endCellColour = endCell.getColour();
			if (startCell.getColour() == endCellColour) {
				throw new GameException("starting pawn and ending pawns have the same color");
			} else {
				// find if there are pawns between and behind (in the same
				// diagonal)
				for (int index = 1; index <= colMovement + 1; index++) {
					int pawnColIndex = startColIndex + index * colMovementSign;
					int pawnRowIndex = startRowIndex + index * rowMovementSign;
					if (pawnColIndex != endColIndex && pawnRowIndex != endRowIndex) {
						Pawn pawn = getCell(pawnColIndex, pawnRowIndex);
						if (pawn != null) {
							throw new GameException(
									"There are pawns between : at position " + pawnColIndex + ":" + pawnRowIndex);
						}
					}
				}
				updateColorNumber(endCellColour, -1);
				hasEaten = true;
			}
		}
		return hasEaten;
	}

	public void updateColorNumber(PawnColour colour, int number) {
		int count = pawnNumbersByColor.containsKey(colour) ? pawnNumbersByColor.get(colour) : 0;
		pawnNumbersByColor.put(colour, count + number);
	}

	/**
	 * return the winner color or null if game is still not win
	 *
	 */
	@Override
	public PawnColour getWinner() {
		for (Map.Entry<PawnColour, Integer> entry : pawnNumbersByColor.entrySet()) {
			if (entry.getValue() == MIN_PAWNS) {
				return entry.getKey();
			}
		}
		return null;
	}

	/**
	 * get cell content : null or pawn
	 * 
	 * @return
	 */
	@Override
	public Pawn getCell(int colIndex, int rowIndex) {
		try {
			return this.board.get(colIndex).get(rowIndex);
		} catch (IndexOutOfBoundsException e) {
			throw new GameException("one of the positon is out of Board : " + e.getMessage());
		}
	}
	@Override
	public PawnColour getCellColour(int colIndex, int rowIndex){
		try {
			PawnColour colour = null;
			Pawn pawn = getCell(colIndex,rowIndex);
			if(pawn != null){
				colour = pawn.getColour();
			}
			return colour;
		} catch (IndexOutOfBoundsException e) {
			throw new GameException("one of the positon is out of Board : " + e.getMessage());
		}
	}

	/**
	 * set a pawn on the board
	 * 
	 * @param colIndex
	 * @param rowIndex
	 * @param pawn
	 * @throws Exception
	 */
	public void setBoardSquare(int colIndex, int rowIndex, Pawn pawn) {
		try {
			this.board.get(colIndex).set(rowIndex, pawn);
		} catch (IndexOutOfBoundsException e) {
			throw new GameException("one of the positon is out of Board : " + e.getMessage());
		}
	}

	/**
	 * get number of pawn on the board
	 * 
	 * TODO: change to get all number by color from hashmap
	 * 
	 * @return
	 */
	@Override
	public int getBoardPawnNumber(PawnColour colour) {
		int nbPawn = 0;
		boolean noColor = false;
		if (colour == null) {
			noColor = true;
		}
		for (ArrayList<Pawn> arrayList : board) {
			for (Pawn pawn : arrayList) {
				if (pawn != null) {
					if (noColor) {
						nbPawn++;
					} else {
						if (pawn.getColour() == colour) {
							nbPawn++;
						}
					}
				}
			}
		}
		return nbPawn;
	}

	@Override
	public int getColumnsNumber() {
		// TODO Auto-generated method stub
		return this.board.size();
	}

}