package Main;

/**
 * Created by isen on 23/01/2017.
 */
public class Pawn {

    public PawnColour colour;
    public PawnStatus status;

    public void setColour( PawnColour colour){
        this.colour = colour;
    }

    public PawnColour getColour(){
        return colour;
    }

    public void setStatuts(PawnStatus statuts){
        this.status = statuts;
    }

    public PawnStatus getStatus(){
        return status;
    }

    public void Pawn(PawnColour colour){
        this.colour = colour;
        this.status = PawnStatus.PAWN;
    }


}
