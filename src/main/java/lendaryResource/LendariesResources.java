package lendaryResource;

import com.prowidesoftware.swift.model.mt.mt9xx.MT940;
import lendaryDAO.LendaryDAO;
import lendaryModel.LendaryModel;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;
import java.io.IOException;
import java.io.PrintWriter;
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


    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces({MediaType.TEXT_HTML})
    public void newLendaryModel(@FormParam("id") String id,
                                @FormParam("name") String name,
                                @Context HttpServletResponse response)
            throws ServletException, IOException {
        LendaryModel lendaryModel = new LendaryModel(id, name);
        if (lendaryModel != null) {
            lendaryModel.setName(name);
            lendaryModel.setName("esssa");
        }
        LendaryDAO.getModel().put(id, lendaryModel);
        response.sendRedirect("../index.html");
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<LendaryModel> getLendaryBrowser() {
        List<LendaryModel> files = new ArrayList<LendaryModel>();
        files.addAll(LendaryDAO.getModel().values());
        System.out.println(files.toString());
        return files;

    }
    @Path("{fileid}")
    public  LendaryResource getLendary(@PathParam("fileid") String id){
        return new LendaryResource(uriInfo, request, id, dao);
    }

}

