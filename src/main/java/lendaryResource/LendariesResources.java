package lendaryResource;


import database.ConnectionHandler;
import lendaryDAO.LendaryDAO;
import lendaryModel.Balance;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@Path("/files")
public class LendariesResources extends HttpServlet {
    @Context
    UriInfo uriInfo;
    @Context
    Request request;

    ConnectionHandler con = new ConnectionHandler();

    public LendariesResources() throws IOException, SQLException {
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Balance> getLendaryBrowser() {
        List<Balance> files = con.getBalances();
        return files;
    }

}

