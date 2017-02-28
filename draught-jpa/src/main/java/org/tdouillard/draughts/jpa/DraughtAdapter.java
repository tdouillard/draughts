package org.tdouillard.draughts.jpa;
import org.tdouillard.draughts.core.DraughtsGameImpl;
import org.tdouillard.draughts.core.GameException;
import org.tdouillard.draughts.core.PawnColour;
import java.awt.Point;
import java.nio.channels.GatheringByteChannel;

import org.tdouillard.draughts.core.DraughtsGame;
public class DraughtAdapter extends DraughtsGame{

    private Game game;

    private DraughtsGame coreGame;

    private DraughtDao dao;

    public DraughtAdapter(DraughtDao dao, Game game) {
        this.dao = dao;
        this.game = game;
        this.coreGame = new DraughtsGameImpl();
        
        for (Turn turn : game.getTurns()) {
        	Point oldPosition = turn.getOldPosition();
        	Point newPosition = turn.getNewPosition();
            this.coreGame.play(oldPosition.x,oldPosition.y,newPosition.x,newPosition.y);
        }

    }
    @Override
    /**
     * switch turn and save game in addition of the original play function
     * 
     * TODO: refactor play function with colour parameter
     * @param startColIndex
     * @param startRowIndex
     * @param endColIndex
     * @param endRowIndex
     * @return
     * @throws GameException
     */
    public boolean play(int startColIndex, int startRowIndex, int endColIndex, int endRowIndex) throws GameException {
    	PawnColour playerColour = coreGame.getCellColour(startColIndex, startRowIndex);
    	if(playerColour != game.getCurrentTurn()){
    		throw new GameException("This is not the " + playerColour + " Player turn");
    	}
    	coreGame.play(startColIndex, startRowIndex, endColIndex,endRowIndex);
        this.game.getTurns().add(new Turn(this.game,playerColour, startColIndex, startRowIndex, endColIndex,endRowIndex));
        switchTurn();
        dao.save(game);
        return true;

    }

    private void switchTurn() {
        game.setCurrentTurn(game.getCurrentTurn() == PawnColour.BLACK ? PawnColour.WHITE
                : PawnColour.BLACK);
    }
	@Override
	public Object getCell(int posX, int posY) {
		return this.coreGame.getCell(posX, posY);
	}
	@Override
	public PawnColour getWinner() {
		return this.coreGame.getWinner();
	}
	public String getToken() {
		 return game.getToken();
	}
	public Object getCurrentTurn() {
		// TODO Auto-generated method stub
		return this.game.getCurrentTurn();
	}
	@Override
	public PawnColour getCellColour(int posX, int posY) {
		// TODO Auto-generated method stub
		return coreGame.getCellColour(posX, posY);
	}
	public int getBoardPawnNumber(PawnColour colour) {
		// TODO Auto-generated method stub
		return coreGame.getBoardPawnNumber(colour);
	}

	public int getColumnsNumber() {
		return coreGame.getColumnsNumber();
	}


}
