package org.tdouillard.draughts.jpa;

import java.awt.Point;
import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.tdouillard.draughts.core.PawnColour;
/*
 * TODO : remove Points
 */
@Entity
public class Turn {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    Long id;

    @ManyToOne
    Game game;
    
    private String colour;
    
    
    @Column(name="oldColumnPosition")
    int oldColumnPosition;
  
    @Column(name="oldRowPosition")
    int oldRowPosition;
    
    @Column(name="newColumnPosition")
    int newColumnPosition;
    
    @Column(name="newRowPosition")
    int newRowPosition;
   
    Point oldPosition;
    
    Point newPosition;
    
    public Turn() {

    }

    public Turn(Game game, PawnColour colour, int oldColPosition, int oldRowPosition,int newColPosition, int newRowPosition) {
        this.game = game;
        this.colour = colour.toString();
        this.oldPosition = new Point(oldColPosition,oldRowPosition);
        this.newPosition = new Point(newColPosition,newRowPosition);
        this.oldColumnPosition = oldColPosition;
        this.oldRowPosition = oldRowPosition;
        this.newColumnPosition = newColPosition;
        this.newRowPosition = newRowPosition;
    }

    public PawnColour getColour() {
        return PawnColour.valueOf(colour);
    }
    
    public Point getNewPosition(){
    	return this.newPosition;
    }
    
    public Point getOldPosition(){
    	return this.oldPosition;
    }

}
