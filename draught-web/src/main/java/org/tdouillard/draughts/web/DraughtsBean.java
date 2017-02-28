package org.tdouillard.draughts.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.tdouillard.draughts.core.DraughtsGame;
import org.tdouillard.draughts.jpa.DraughtAdapter;
import org.tdouillard.draughts.jpa.DraughtDao;

@Named("game")
@RequestScoped
public class DraughtsBean implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	DraughtAdapter game ;

    @Inject
    DraughtDao dao;


    public List<Draughtcolumn> getColumns() {

        List<Draughtcolumn> cols = new ArrayList<>();
        for (int i = 0; i < game.getColumnsNumber(); i++) {
            cols.add(new Draughtcolumn(i, game));
        }
        return cols;

    }

    public void play(int oldCol,int oldRow,int newCol,int newRow) {
        game.play(oldCol,oldRow,newCol,newRow);
    }



    public void reset() {
    }

    public ChipColourWrapper getWinner() {
        if (game.getWinner() != null) {
            return new ChipColourWrapper(game.getWinner());
        } else {
            return null;
        }
    }

    public DraughtsGame getGame() {
        return game;
    }

    public void createNewGame() {
        game = dao.createNewGame();

    }

    public String getToken() {
        return game.getToken();
    }

    public void loadFromToken(String token) {
        game = dao.loadFromToken(token);

    }


}