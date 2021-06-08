import com.prowidesoftware.swift.model.MtSwiftMessage;
import com.prowidesoftware.swift.model.field.Field61;
import com.prowidesoftware.swift.model.mt.mt9xx.MT940;
import database.ConnectionHandler;
import lendaryDAO.LendaryDAO;
import lendaryModel.Balance;
import lendaryModel.Parser;
import lendaryModel.Transaction;

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
    Parser parser;
    public UploadServlet() throws IOException, SQLException {
        connectionHandler = new ConnectionHandler();
        parser = new Parser();
    }


    // add transactions please!
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Part filePart =  request.getPart("file"); // Retrieves <input type="file" name="file">
        Balance balance = new Balance();
        if (filePart != null) {
            mt940 = new MT940(filePart.getInputStream());
            if (mt940 != null) {
                balance.setAccountID(mt940.getField25().getValue());
                balance.setTransaction_number(mt940.getField20().getValue());
                balance.setSequence_number(mt940.getField28C().getValue());
                balance = parser.parseField60F(mt940.getField60F().getValue(), balance);
                balance = parser.parseField62F(mt940.getField62F().getValue(), balance);
                balance = parser.parseField64(mt940.getField64().getValue(), balance);
                for(Field61 f61 : mt940.getField61()){
                    Transaction transaction = new Transaction();
                    transaction = parser.parseField61(f61.getValue(), transaction);
                    balance.addTransaction(transaction);
                }
                connectionHandler.insertBalance(balance);
            }

        }
    }

    //        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // MSIE fix.
//        InputStream inputStream = null;
    //                    connectionHandler.insertMethod(2, text, 10, 23, "2019-11-02", "2019-11-04", "ssddd", 'D');

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}