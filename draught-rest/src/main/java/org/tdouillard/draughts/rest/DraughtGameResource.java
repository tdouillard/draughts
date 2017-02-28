package org.tdouillard.draughts.rest;

import java.io.IOException;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.tdouillard.draughts.jpa.DraughtAdapter;

public class DraughtGameResource {

	@Context
	UriInfo info;

	private DraughtAdapter game;

	public DraughtGameResource(DraughtAdapter game) {
		this.game = game;
	}

	@GET
	public DraughtAdapter doGet() throws IOException {
		return game;
	}

	@POST
	@Path("{oldColumnPosition}/{oldRowPosition}/{newColumnPosition}/{newRowPosition}")
	public Response playColumn(@PathParam("oldColumnPosition") int oldColumnPosition,@PathParam("oldRowPosition") int oldRowPosition,@PathParam("newColumnPosition") int newColumnPosition,@PathParam("newRowPosition") int newRowPosition) throws IOException {
		game.play(oldColumnPosition,oldRowPosition,newColumnPosition,newRowPosition);
		return Response.status(Response.Status.SEE_OTHER)
				.header(HttpHeaders.LOCATION, info.getBaseUri() + game.getToken()).build();

	}
}
