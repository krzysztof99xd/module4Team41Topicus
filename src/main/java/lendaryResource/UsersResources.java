package lendaryResource;

import lendaryDAO.UserDAO;
import lendaryModel.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.io.IOException;

@Path("/users")
public class UsersResources extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public UsersResources() {
        super();
    }
    @PUT
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_FORM_URLENCODED})
    @Produces(MediaType.APPLICATION_JSON)
    public void logOut(@Context HttpServletRequest request, @Context HttpServletResponse response)
            throws ServletException, IOException {
        UserDAO.setLoggedIn(false);
        UserDAO.setUser(null);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getLoggedIn(@Context HttpServletRequest request, @Context HttpServletResponse response){
        return String.valueOf(UserDAO.isLoggedIn());
    }
}
