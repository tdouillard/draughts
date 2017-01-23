package Main;

/**
 * Created by isen on 23/01/2017.
 */
public class Pawn {

    public PawnColour colour;
    public PawnStatus status;

    public void Pawn(PawnColour colour){
        this.colour = colour;
        this.status = PawnStatus.PAWN;
    }
    
    public void setColour( PawnColour colour){
        this.colour = colour;
    }

    public PawnColour getColour(){
        return colour;
    }

    public void setStatus(PawnStatus status){
        this.status = status;
    }

    public PawnStatus getStatus(){
        return status;
    }
    public void becomeQueen(Pawn pawn){
		pawn.status = PawnStatus.QUEEN;
	}
  


}
