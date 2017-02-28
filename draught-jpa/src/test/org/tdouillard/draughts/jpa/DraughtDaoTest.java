package org.tdouillard.draughts.jpa;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.validation.constraints.AssertTrue;
import static org.junit.Assert.*;
import org.tdouillard.draughts.core.DraughtsGame;
import org.tdouillard.draughts.core.DraughtsGameImpl;
import org.tdouillard.draughts.core.GameException;
import org.tdouillard.draughts.core.PawnColour;
import org.tdouillard.draughts.jpa.guice.GuiceRunner;
import org.tdouillard.draughts.jpa.guice.H2DBModule;
import org.tdouillard.draughts.jpa.guice.Modules;
import org.tdouillard.draughts.jpa.DraughtDao;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(GuiceRunner.class)
@Modules({ H2DBModule.class, JPAModule.class })
public class DraughtDaoTest {
	@Inject
	EntityManager em;

	@Inject
	DraughtDao dao;
	// private DraughtsGameImpl game;
	//
	//
	// @Before
	// public void doBefore() throws Exception {
	// game = new DraughtsGameImpl();
	// }
	//

	@Test
	public void daoIsInjected() throws Exception {
		assertTrue(dao != null);

	}

	@Test
	public void itCanCreateAGame() throws Exception {
		DraughtAdapter game = dao.createNewGame();
		assertTrue(game != null);

		String token = game.getToken();
		assertTrue(game.getToken() != null);
		em.clear();
		game = dao.loadFromToken(token);
		assertTrue(game != null);

		assertTrue(game.getBoardPawnNumber(null) == DraughtsGame.PAWN_NUMBER);
		assertTrue(game.getBoardPawnNumber(PawnColour.BLACK) == 20);
		assertTrue(game.getBoardPawnNumber(PawnColour.WHITE) == 20);

	}

	@Test
	public void itCanPlayWithAJPAGame() throws Exception {
		DraughtAdapter game = dao.createNewGame();
		assertTrue(game.getCell(0, 3) != null);
		assertTrue(game.getCell(1, 4) == null);
		assertTrue(game.play(0, 3, 1, 4));
		assertTrue(game.play(1, 6, 0, 5));
		try {
			assertTrue(game.play(3, 6, 2, 5));
			fail("SAME PLAYER : It should not be possible to play two times in a row");
		} catch (GameException e) {

		}
		assertTrue(game.play(4,3,5,4));
		assertTrue(game.play(3, 6, 2, 5));
		assertTrue(game.play(6,3,7,4));
		assertTrue(game.play(2, 5, 1, 4));
		assertTrue(game.getCell(1, 6) == null);
		assertTrue(game.getCell(3, 6) == null);
		assertTrue(game.getCellColour(0, 3) == PawnColour.WHITE);

		String token = game.getToken();

		em.clear();
		game = dao.loadFromToken(token);
		assertTrue(game.getCell(1, 6) == null);
		assertTrue(game.getCell(3, 6) == null);
		assertTrue(game.getCellColour(0, 3) == PawnColour.WHITE);
	}

	@Test
	public void itCanHandleTurns() throws Exception {
		DraughtAdapter game = dao.createNewGame();
		assertTrue(game.getCurrentTurn() != null);
		assertTrue(game.getCurrentTurn() == PawnColour.BLACK);
		assertTrue(game.play(0, 3, 1, 4));
		game = dao.loadFromToken(game.getToken());
		assertTrue(game.getCurrentTurn() == PawnColour.WHITE);
		assertTrue(game.play(1, 6, 0, 5));
		game = dao.loadFromToken(game.getToken());
		assertTrue(game.getCurrentTurn() == PawnColour.BLACK);
	}
}
