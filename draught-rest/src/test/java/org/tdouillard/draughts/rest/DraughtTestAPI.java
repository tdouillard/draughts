package org.tdouillard.draughts.rest;

import static org.junit.Assert.*;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Ignore;
import org.junit.Test;
import org.tdouillard.draughts.jpa.DraughtAdapter;
import org.tdouillard.draughts.rest.DraughtApplication;

public class DraughtTestAPI extends JerseyTest {
	@Override
	protected Application configure() {
		return new DraughtApplication();
	}

	@Test
	@Ignore
	public void itCanCreateAGame() throws Exception {
		DraughtAdapter game = target("api").request(MediaType.APPLICATION_JSON).get(DraughtAdapter.class);
		assertTrue(game.getToken()!= null);

	}
}