package lendaryResource;
import lendaryDAO.LendaryDAO;
import lendaryModel.Balance;


import javax.servlet.http.HttpServlet;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;


public class LendaryResource extends HttpServlet {
    @Context
    UriInfo uriInfo;
    @Context
    Request request;
    String id;
    LendaryDAO dao;

    public LendaryResource(UriInfo uriInfo, Request request, String id, LendaryDAO dao){
        this.dao = dao;
        this.uriInfo = uriInfo;
        this.request = request;
        this.id = id;
    }
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_JSON})
    public Balance getLendaryModel(){
        Balance balance = LendaryDAO.getModel().get(id);
        if(balance == null){
            throw new RuntimeException("Get: File with " +id +" does not exist");
        }
        return balance;
    }

    @DELETE
    public void deletelendaryModel() {
        Balance balance = LendaryDAO.getModel().remove(id);
        if (balance == null) {
            throw  new RuntimeException("Delete: Bike with " + id + "not found");
        }
    }


}





