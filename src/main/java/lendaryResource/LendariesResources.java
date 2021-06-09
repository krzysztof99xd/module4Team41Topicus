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
//@WebServlet("/file")
public class LendariesResources extends HttpServlet {
    @Context
    UriInfo uriInfo;
    @Context
    Request request;

    LendaryDAO dao = new LendaryDAO();
    ConnectionHandler con = new ConnectionHandler();

    public LendariesResources() throws IOException, SQLException {
    }

//
//    @POST
//    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
//    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
//    public void newLendaryModel(@Context HttpServletResponse response, HttpServletRequest request)
//            throws ServletException, IOException {
//        Part file = request.getPart("file");
//        System.out.println("Post with stuff from the internet");
//        LendaryDAO lendaryDAO = new LendaryDAO(file);
//        response.sendRedirect("../inde22222.html");
//    }

    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void newLendaryModel(@FormParam("id") String id,
                                @FormParam("name") String name,
                                @FormParam("field") String field,
                                @Context HttpServletResponse response)
            throws ServletException, IOException {
//        Balance balance = new Balance(id, name, field);
//        System.out.println("Post");
//        if (balance != null) {
//            balance.setAcounyID(name);
//        }
//        LendaryDAO.getModel().put(id, balance);
        response.sendRedirect("src/main/webapp/assets/inde22222.html");
    }
//    @POST
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Balance> getLendaryBrowser() {
        List<Balance> files = con.getBalances();
        return files;
    }

    @Path("{fileid}")
    public  LendaryResource getLendary(@PathParam("fileid") String id){
        return new LendaryResource(uriInfo, request, id, dao);
    }
}

