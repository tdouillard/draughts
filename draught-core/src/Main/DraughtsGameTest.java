package Main;


import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

public class DraughtsGameTest {
	private DraughtsGameImpl game;
	
	@Before
	public void doBefore() throws Exception {
        game = new DraughtsGameImpl();
    }
	
	@Test
	/**
	 * todo:match board game with a complete board game to check pawn position 
	 * @throws Exception
	 */
	public void isGameInitialized() throws Exception{
		assertFalse(game.equals(null));
		System.out.println(game.board.size());
		assertTrue(game.getBoardPawnNumber(null) == DraughtsGame.PAWN_NUMBER);
		assertTrue(game.getBlackPawnNumber() == 20);
		assertTrue(game.getWhitePawnNumber() == 20);
	}
	@Test
	/**
	 * 
	 */
	public void canPawnMove(){
//		assertFalse(game.movePawn(0,4,0,5));	
//		assertFalse(game.movePawn(0,4,-1,5));
//		assertTrue(game.movePawn(0,4,1,5));
	}
	@Test
	public void canPawnBeEaten(){
		
	}
	@Test
	public void canPawnBecomeQueen(){
		
	}
}
