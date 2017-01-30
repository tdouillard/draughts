package Main;

/**
 * Created by isen on 23/01/2017.
 */
public class Pawn {
	public static final int MAX_PAWN_MOVEMENT= 1;
	public static final int MAX_QUEEEN_MOVEMENT= -1;
	
    public PawnColour colour;
    public PawnStatus status;
    public int movement_direction; 

	public Pawn(PawnColour colour){
        this.colour = colour;
        this.status = PawnStatus.PAWN;
        setMovementDirection();
    }
    
	/*
	 * TODO: case colour not in it
	 */
    public void setMovementDirection() {
    	switch (this.colour) {
		case WHITE:
			this.movement_direction = -1;
			break;
		case BLACK:
			this.movement_direction = 1;
			break;
			}
    }

	public int getMovementDirection() {
		return this.movement_direction;
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
    /*
     * really necessary?
     */
    public void becomeQueen(){
		setStatus(PawnStatus.QUEEN);
    }


}
