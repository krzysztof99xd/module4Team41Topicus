package lendaryResource;

import com.fasterxml.jackson.databind.util.JSONPObject;
import database.ConnectionHandler;
import lendaryDAO.LendaryDAO;
import lendaryModel.Analytics;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.sql.SQLException;

import java.util.List;
import java.util.concurrent.TimeUnit;



@Path("/analytics")
public class AnalyticsResources {
    ConnectionHandler c = new ConnectionHandler();
    Analytics an;

    public AnalyticsResources() throws SQLException, IOException {
    }


    @POST
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_FORM_URLENCODED})
    @Produces(MediaType.APPLICATION_JSON)
    public  void createAnalytics(@Context HttpServletResponse response, @Context HttpServletRequest request, String json){
        String accountID = json.substring(1, json.length()-1);
        System.out.println("This is a new accountID "+ accountID);
        System.out.println(accountID);
        an = c.analysis(accountID);
        System.out.println(an.getAccountID());
        LendaryDAO.instance.setAnalytics(an);
        LendaryDAO.instance.setAccountID(accountID);
        
    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Analytics getAnalytics() throws InterruptedException{
        //System.out.println( "This is from Get " + LendaryDAO.instance.getAnalytics().getAccountID());
        while(LendaryDAO.instance.getAnalytics() == null) {
        	TimeUnit.SECONDS.sleep(1);
        }
        return LendaryDAO.instance.getAnalytics();
    }

}
