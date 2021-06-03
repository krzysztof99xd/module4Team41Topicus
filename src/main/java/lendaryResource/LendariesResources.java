package lendaryResource;

import lendaryDAO.LendaryDAO;
import lendaryModel.Transaction;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Path("/files")
//@WebServlet("/file")
public class LendariesResources extends HttpServlet {
    @Context
    UriInfo uriInfo;
    @Context
    Request request;

    LendaryDAO dao = new LendaryDAO();

    public LendariesResources() throws IOException {
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
//        response.sendRedirect("../index.html");
//    }

    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void newLendaryModel(@FormParam("id") String id,
                                @FormParam("name") String name,
                                @FormParam("field") String field,
                                @Context HttpServletResponse response)
            throws ServletException, IOException {
        Transaction transaction = new Transaction(id, name, field);
        System.out.println("Post");
        if (transaction != null) {
            transaction.setName(name);
        }
        LendaryDAO.getModel().put(id, transaction);
        response.sendRedirect("../index.html");
    }
//    @POST
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Transaction> getLendaryBrowser() {
        List<Transaction> files = new ArrayList<Transaction>();
        files.addAll(LendaryDAO.getModel().values());
        System.out.println("GET");
        return files;
    }

    @Path("{fileid}")
    public  LendaryResource getLendary(@PathParam("fileid") String id){
        return new LendaryResource(uriInfo, request, id, dao);
    }
}

