package org.tdouillard.draughts.jpa;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import org.apache.commons.lang.RandomStringUtils;

public class DraughtDao {
	@Inject
	EntityManager em;

	@Inject
	UserTransaction ut;

	public DraughtAdapter createNewGame() {

		Game game = new Game();
		game.setToken(RandomStringUtils.randomAlphanumeric(10).toLowerCase());
		try {
			ut.begin();
			em.persist(game);
			ut.commit();

		} catch (NotSupportedException | SystemException | SecurityException | IllegalStateException | RollbackException
				| HeuristicMixedException | HeuristicRollbackException e) {
			return null;
		}
		return new DraughtAdapter(this, game);
	}

	public DraughtAdapter loadFromToken(String token) {
		Game game = (Game) em.createQuery("SELECT g FROM Game g WHERE g.token = :token").setParameter("token", token)
				.getSingleResult();

		return new DraughtAdapter(this, game);
	}

	public void save(Game game) {
		try {

			ut.begin();
			em.merge(game);
			ut.commit();
		} catch (SecurityException | IllegalStateException | RollbackException | HeuristicMixedException
				| HeuristicRollbackException | SystemException | NotSupportedException e) {
			e.printStackTrace();
		}
		// EntityTransaction transaction = em.getTransaction();
		// transaction.begin();
		// em.persist(game);
		// transaction.commit();

	}
}
