package org.tdouillard.draughts.jpa;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;

import org.tdouillard.draughts.core.PawnColour;

/*@NamedQueries({
@NamedQuery(name = "ALL_TURNS", query = "FROM DraughtsGame")})*/
@Entity(name="Game")
public class Game {

//	public static final String ALL_TURNS = "ALL_TURNS";
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    private String token;
    
    @OneToMany(mappedBy="game", cascade=CascadeType.ALL, fetch=FetchType.EAGER)
    private List<Turn> turns = new ArrayList<>();

    private String currentTurn = PawnColour.BLACK.toString();

    public Game() {

    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;

    }

    public List<Turn> getTurns() {
        return turns;
    }
    
    public void setTurns(List<Turn> turns){
    	this.turns = turns;
    }
    public PawnColour getCurrentTurn() {
        return  PawnColour.valueOf(currentTurn);
    }

    public void setCurrentTurn (PawnColour colour) {
        currentTurn = colour.toString();
    }

}