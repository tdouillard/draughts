package org.tdouillard.draughts.rest;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.container.ResourceContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

import org.tdouillard.draughts.jpa.DraughtAdapter;
import org.tdouillard.draughts.jpa.DraughtDao;


@Path("/")
@Produces({ "application/json", "*/*" })
@RequestScoped
public class DraughtAPI {

    @Inject
    DraughtDao dao;

    @Context
    HttpServletRequest request;

    @Context
    ResourceContext rc;

    @GET
    public Response doGet() {
        DraughtAdapter game = dao.createNewGame();
        return Response
                .status(Response.Status.SEE_OTHER)
                .header(HttpHeaders.LOCATION,
                        request.getContextPath() + "/api/" + game.getToken())
                .build();
    }

    @Path("{gameToken}")
    public Object getGame(@PathParam("gameToken") String token) {
    	DraughtAdapter game = dao.loadFromToken(token);
        return rc.initResource(new DraughtGameResource(game));
    }
}
