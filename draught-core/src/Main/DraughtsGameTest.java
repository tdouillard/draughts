package Main;

import static Main.PawnColour.*;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.fail;


import org.junit.jupiter.api.BeforeAll;
import org.testng.annotations.Test;



public class DraughtsGameTest {
	private DraughtsGame game;
	
	@BeforeAll
	public void doBefore() throws Exception {
        game = new DraughtsGameImpl();
    }
	
	@Test
	public void  doesGameExist() throws Exception{
		System.out.println(game.getClass().getPackage());
		assertFalse(game.equals(null));
	}

	@Test
	public void canPawnMove() throws Exception{
		game.play(WHITE, 3 ,3);
		assertThat(game.getCell(3, 3)).isEqualTo(WHITE);
		try {
			game.play(WHITE, 3 , 4);
		} catch (GameException e) {

		}


	}

	@Test
	public void canItWin() throws Exception{
		assertThat(game.numberBlack).isEqualTo(0);
		try {
			game.whoWon(game.numberBlack);
		} catch (GameException e) {

		}

	}


	@Test
	public void canPawnBeEaten() throws Exception{
		game.play(WHITE, 3 ,3);
		game.play(BLACK, 4 ,4);

		assertThat(game.getCell(5, 5)).isEqualTo(null);
		try {
			game.play(WHITE, 5 , 5);
		} catch (GameException e) {

		}

	}

	@Test
	public void canPawnBecomeQueen()throws Exception{
		game.play(WHITE, 3 , 9);
		assertThat(game.getCell(3,10)).isEqualTo(null);

		try {
			game.play(WHITE, 3 , 10);
		} catch (GameException e) {

		}

	}

	@Test
	public void ItCantPlayOutside() throws Exception{
		try {
			game.play(WHITE, 12 , 10);
			fail("It should not be possible to play outside of the board");
		} catch (GameException e) {

		}
	}


}
