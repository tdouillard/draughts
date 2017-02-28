package org.tdouillard.draughts.core;



import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
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
	 * TODO: game won tests
	 * @throws Exception
	 */
	public void isGameInitialized() throws Exception {
		assertFalse(game.equals(null));
		System.out.println(game.board.size());
		assertTrue(game.getBoardPawnNumber(null) == DraughtsGame.PAWN_NUMBER);
		assertTrue(game.getBoardPawnNumber(PawnColour.BLACK) == 20);
		assertTrue(game.getBoardPawnNumber(PawnColour.WHITE) == 20);
	}

	@Test
	/**
	 * 
	 */
	public void canPawnMove() {
		try {
			game.play(0, 4, -1, 5);
			fail("It should not be possible to play outside of the board");
		} catch (GameException e) {
		}

		try {
			game.play(0, 4, 0, 5);
			fail("No pawn on the starter cell");

		} catch (GameException e) {
		}

		assertFalse(game.play(3, 6, 5, 4));
		assertTrue(game.play(0, 3, 1, 4));
		assertFalse(game.play(1,4,0,3));
	}

	@Test
	public void canPawnBeEaten() {
		assertTrue(game.play(0, 3, 1, 4));
		assertTrue(game.play(1, 6, 0, 5));
		try {
			game.play(0, 5, 1, 4);
			fail("Pawn cannot eat if there is a pawn behind his target");

		} catch (GameException e) {
		}
		assertTrue(game.play(3, 6, 2, 5));
		assertTrue(game.play(2,5,1,4));
		assertTrue(game.getCell(2, 5) == null);
		assertTrue(game.getCell(1, 4) == null);
		
	}

	@Test
	public void canPawnBecomeQueen() {
		
		//black pawns plays
		assertTrue(game.play(0, 3, 1, 4));
		assertTrue(game.play(4, 3, 5, 4));
		assertTrue(game.play(2,3,3,4));
		assertTrue(game.play(1,2,2,3));
		assertTrue(game.play(3,2,4,3));
		assertTrue(game.play(2,1,3,2));
		assertTrue(game.play(3,0,2,1));
		
		//white pawn plays
		assertTrue(game.play(3, 6, 2, 5));		
		assertTrue(game.play(2,5,1,4));
		assertTrue(game.play(0,3,1,2));
		assertTrue(game.play(1,2,2,1));
		
		
		assertTrue(game.getCell(3,0).getStatus()==PawnStatus.QUEEN);
		
		}
	
	@Test
	public void canQueenMove(){
		canPawnBecomeQueen();
		assertTrue(game.play(3,0,0,3));
		assertTrue(game.play(0,3,2,5));
		try {
			game.play(2,5,4,3);
			fail("Queen cannot eat if there is a pawn behind his target");

		} catch (GameException e) {
		}
		
	}
	
	@Test
	public void canGameBeWon(){
		assertTrue(game.getWinner() == null);
	}
}
