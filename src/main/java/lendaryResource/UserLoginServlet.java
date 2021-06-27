package lendaryResource;

import database.ConnectionHandler;
import lendaryDAO.LendaryDAO2;
import lendaryDAO.UserDAO;
import lendaryModel.Analytics;
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
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.concurrent.TimeUnit;


@WebServlet("/login")
public class UserLoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    ConnectionHandler connectionHandler = new ConnectionHandler();

    public UserLoginServlet() throws SQLException {
        super();
    }

    /**
     * checks whether inserted email and password are correct. If so, it redirects the user to index.html, if not it displays the message and stays on the log in page
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        try {
            User user = connectionHandler.checkLogin(email, password);
            UserDAO.instance.setUser(user);
            String destPage = "login.html";

            if (user != null) {
                UserDAO.setLoggedIn(true);
                HttpSession session = request.getSession();
                session.setAttribute("user", user);
                destPage = "index.html";
                RequestDispatcher dispatcher = request.getRequestDispatcher(destPage);
                dispatcher.forward(request, response);

            } else {
                UserDAO.setLoggedIn(false);
                String message = "Invalid email/password";
                response.setContentType("text/html");
                PrintWriter out = response.getWriter();

                out.println("<HTML>\n" +
                        "<HEAD ><TITLE></TITLE>" +
                        "</HEAD>\n" +
                        "<BODY onload = 'myFunction()'>\n" +
                        "<script>" +
                        "function myFunction(){" +
                        "alert('email/password incorrect');" +
                        "location.assign('login.html');" +
                        "}" +
                        " </script>" +
                        "</BODY></HTML>");
                request.setAttribute("message", message);
            out.close();
            }


        } catch (SQLException | ClassNotFoundException | NoSuchAlgorithmException ex) {
            throw new ServletException(ex);
        }
    }

}

