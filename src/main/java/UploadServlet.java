import com.prowidesoftware.swift.model.MtSwiftMessage;
import com.prowidesoftware.swift.model.mt.mt9xx.MT940;
import database.ConnectionHandler;
import lendaryDAO.LendaryDAO;
import lendaryModel.LendaryModel;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.sql.SQLException;


@WebServlet("/upload")
@MultipartConfig
public class UploadServlet extends HttpServlet {

    MT940 mt940;
    ConnectionHandler connectionHandler;
    public UploadServlet() throws IOException, SQLException {
    }


    // ...
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//     /   String description = request.getParameter("description"); // Retrieves <input type="text" name="description">
        Part filePart =  request.getPart("file"); // Retrieves <input type="file" name="file">
//        System.out.println("this is file part:" + filePart.getSubmittedFileName());

//        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // MSIE fix.
        InputStream inputStream = null;
        if (filePart != null) {
//            long fileSize = filePart.getSize();
//            String fileContent = filePart.getContentType();
//            inputStream = filePart.getInputStream();
            mt940 = new MT940(new File("/upload"));
            if (mt940 != null) {
                String text = "start";
                if (mt940.getField20().getValue() != null) {
                    text += mt940.getField20().getValue();
                }
                try {
                    connectionHandler = new ConnectionHandler();
                    connectionHandler.insertMethod(2, text, 10, 23, "ddd", "dddd", "ssddd", 'D');
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}