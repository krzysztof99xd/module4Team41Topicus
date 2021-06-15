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
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet("/upload")
@MultipartConfig
public class UploadServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    MT940 mt940;
    ConnectionHandler connectionHandler;
    Parser parser;
    boolean is_correct;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UploadServlet() throws IOException, SQLException {
        super();
        connectionHandler = new ConnectionHandler();
        parser = new Parser();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Part filePart = request.getPart("file"); // Retrieves <input type="file" name="file">
        Balance balance = new Balance();
        if (filePart != null) {
            mt940 = new MT940(filePart.getInputStream());
            if (mt940 != null && mt940.isMT()) {
                is_correct = true;
                if(mt940.getField25() != null) {
                    balance.setAccountID(mt940.getField25().getValue());
                }
                balance.setTransaction_number(mt940.getField20().getValue());
                if (mt940.getField28C() != null) {
                    balance.setSequence_number(mt940.getField28C().getValue());
                }
                if (mt940.getField60F() != null) {
                    balance = parser.parseField60(mt940.getField60F().getValue(), balance);
                } else {
                    balance = parser.parseField60(mt940.getField60M().getValue(), balance);
                }
                balance = parser.parseField62F(mt940.getField62F().getValue(), balance);
                if (mt940.getField64() != null) {
                    balance = parser.parseField64(mt940.getField64().getValue(), balance);
                }
                for (Field61 f61 : mt940.getField61()) {
                    Transaction transaction = new Transaction();
                    transaction = parser.parseField61(f61.getValue(), transaction);
                    balance.addTransaction(transaction);
                }
                connectionHandler.insertBalance(balance);
                response.setContentType("text/html");
                PrintWriter out = response.getWriter();

                 out.println(
                "<HTML     onload='lll()'>\n" +
                "<HEAD  onload='lll()'><TITLE></TITLE>" +
                "</HEAD>\n" +
                "<BODY onload='lll()'>\n" +

                " <script> " +
                         " function lll(){ " +
                           "  window.close(); " +
                             "}"+
                         "</script>"+

                "</BODY></HTML>");


            }
        }
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        response.setContentType("text/html");
//        String correctness = "";
//        PrintWriter out = response.getWriter();
//        if (is_correct){
//            correctness = "Congratulations, file has been uploaded correctly, please click the button below to come back to the table";
//        }else {
//            correctness = "Unfortunately, the file which you uploaded is not in MT940 format, try again, please click the button below to come back to the table";
//        }
//        String docType =
//                "<!DOCTYPE HTML>\n";
//        String title = "Uploaded file";
//        out.println(docType +
//                "<HTML>\n" +
//                "<HEAD><TITLE>" + title + "</TITLE>" +
//                "</HEAD>\n" +
//                "<BODY BGCOLOR=\"#FDF5E6\">\n" +
//                "<H1>" + title + "</H1>\n" +
//                correctness +
//                "</BODY></HTML>");
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<h1>" + "Loooowy" + "</h1>");
        out.println("<p>" + "Hello friends" + "</p>");

    }
}