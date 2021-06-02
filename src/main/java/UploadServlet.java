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
import java.sql.Connection;
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
        Part filePart =  request.getPart("file"); // Retrieves <input type="file" name="file">
        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // MSIE fix.
        InputStream inputStream = null;
        if (filePart != null) {
            long fileSize = filePart.getSize();
            String fileContent = filePart.getContentType();
            mt940 = new MT940(filePart.getInputStream());
            if (mt940 != null) {
                String text = "start";
                if (mt940.getField20().getValue() != null) {
                    text += mt940.getField20().getValue();
                }
                try {
                    connectionHandler = new ConnectionHandler();
                    connectionHandler.insertMethod(2, text, 10, 23, "2019-11-02", "2019-11-04", "ssddd", 'D');
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