package org.tdouillard.draughts.web;
import java.util.ArrayList;
import java.util.List;

import org.tdouillard.draughts.core.DraughtsGame;
import org.tdouillard.draughts.core.Pawn;

public class Draughtcolumn {

    private int index;
    private DraughtsGame game;

    public Draughtcolumn(int i, DraughtsGame game) {
        this.index = i;
        this.game = game;
    }

    public List<ChipColourWrapper> getCells() {
        List<ChipColourWrapper> cells = new ArrayList<>();
        for (int i = game.getColumnsNumber() - 1; i >= 0; i--) {
            cells.add(new ChipColourWrapper(game.getCellColour(index,i)));
        }
        return cells;
    }

    public int getIndex() {
        return index;
    }

}
