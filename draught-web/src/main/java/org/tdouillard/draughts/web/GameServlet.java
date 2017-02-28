package org.tdouillard.draughts.web;

import java.awt.Point;
import java.io.IOException;

import javax.inject.Inject;
import javax.persistence.Column;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@WebServlet(urlPatterns = "/g/*")
public class GameServlet extends HttpServlet {

    private static final long serialVersionUID = 4590295895653754427L;

    @Inject
    DraughtsBean game;

    private static final Log LOG = LogFactory.getLog(GameServlet.class);

    @Override
    public void destroy() {
        // TODO Auto-generated method stub

    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        String token = getTokenFromRequest(request);

        if (StringUtils.isEmpty(token) || request.getParameter("reset") != null) {
            LOG.debug("Empty token, creating a new game");
            game.createNewGame();
            redirectToGameRoot(response, request);
        } else {
            if (LOG.isDebugEnabled()) {
                LOG.debug("Found token " + game.getToken() + " in request");
            }
            game.loadFromToken(token);
            String oldColPosition = request.getParameter("oldColumnPosition");
            String oldRowPosition = request.getParameter("oldRowPosition");
            String newColPosition = request.getParameter("newColumnPosition");
            String newRowPosition = request.getParameter("newRowPosition");
 
            if (oldColPosition != null && oldRowPosition != null && newColPosition != null && newRowPosition != null) {
                game.play(Integer.parseInt(oldColPosition),Integer.parseInt(oldRowPosition),Integer.parseInt(newColPosition),Integer.parseInt(newRowPosition));
                redirectToGameRoot(response, request);
            } else {
                request.getRequestDispatcher("/game.jsp").include(request,
                        response);
            }

        }

    }

    private void redirectToGameRoot(HttpServletResponse response,
            HttpServletRequest request) throws IOException {
        response.sendRedirect(request.getContextPath()
                + request.getServletPath() + "/" + game.getToken());
    }

    private String getTokenFromRequest(HttpServletRequest request) {
        if (request == null) {
            return "";
        }

        String token = request.getRequestURI().substring(
                request.getContextPath().length()
                        + request.getServletPath().length() + 1);

        return token;

    }

}