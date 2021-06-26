package lendaryResource;


import database.ConnectionHandler;
import lendaryDAO.LendaryDAO2;
import lendaryModel.Analytics;
import lendaryModel.Balance;

import javax.servlet.ServletException;
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
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Path("/files")
public class LendariesResources extends HttpServlet  {
    @Context
    UriInfo uriInfo;

    @Context
    Request request;

    ConnectionHandler con; 

    public LendariesResources() throws IOException {
    	try {
			con = new ConnectionHandler();
		} catch (SQLException e) {
			
		}
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Balance> getLendaryBrowser()  {

        List<Balance> files;
		try {
			files = con.getBalances();
			return files;
		} catch (SQLException | NullPointerException npe) {
			Balance b = new Balance();
			b.setAccountID("SQLError");
			files = new ArrayList<Balance>();
			files.add(b);
			return files;
		}
       
    }
    
    @Path("/remove")
    @DELETE
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_FORM_URLENCODED})
    @Produces(MediaType.APPLICATION_JSON)
    public  void removeLendary(@Context HttpServletResponse response, @Context HttpServletRequest request, String json) {
    	String remove = json.substring(1, json.length()-1);
        System.out.println("This is a new remove :"+ remove);
        try {
			con.removeBalance(remove);
		} catch (NullPointerException | SQLException e ) {
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
}

