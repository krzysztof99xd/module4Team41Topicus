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
import java.io.PrintWriter;
import java.sql.SQLException;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Path("/analytics")
public class AnalyticsResources {
	ConnectionHandler c;
    Analytics an;

    public AnalyticsResources()  {
    	 try {
			c = new ConnectionHandler();
		} catch (SQLException e) {
			
		}
    }


    @POST
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_FORM_URLENCODED})
    @Produces(MediaType.APPLICATION_JSON)
    public  void createAnalytics(@Context HttpServletResponse response, @Context HttpServletRequest request, String json) {
        String accountID = json.substring(1, json.length()-1);
        System.out.println("This is a new accountID "+ accountID);
        System.out.println(accountID);
        try {
        	c = new ConnectionHandler();
			an = c.analysis(accountID);
			//System.out.println(an.getAccountID());
	        LendaryDAO.instance.setAnalytics(an);
	        LendaryDAO.instance.setAccountID(accountID);
			
		} catch (NullPointerException | SQLException e) {
			response.setContentType("text/html");
	        PrintWriter out;
		
	        try {
				out = response.getWriter();
				out.println("<HTML     onload='lll()'>\n" +
	                    "<HEAD  onload='lll()'><TITLE></TITLE>" +
	                    "</HEAD>\n" +
	                    "<BODY onload='lll()'>\n" +

	                    " <script> " +
	                             " function lll(){" +
	                              "alert('Couldn't connect to the database!\n"
	                              + "Please check your internet connection'); " +
	                               "  window.close(); " +
	                                 "}"+
	                             "</script>"+

	                    "</BODY></HTML>");
			} catch (IOException e1) {
				System.out.print("can't comunnicate with UI");
			}
		}
        
        
    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Analytics getAnalytics() throws InterruptedException{
        
    	//System.out.println( "This is from Get " + LendaryDAO.instance.getAnalytics().getAccountID());
    	try {
			c = new ConnectionHandler();
			TimeUnit.MILLISECONDS.sleep(100);
	        while(LendaryDAO.instance.getAnalytics() == null) {
	        	TimeUnit.MILLISECONDS.sleep(100);
	        }
	        return LendaryDAO.instance.getAnalytics();
		} catch (SQLException e) {
			return null;
		}
    	
    }

}
