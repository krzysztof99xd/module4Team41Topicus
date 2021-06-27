import com.prowidesoftware.swift.model.MtSwiftMessage;
import com.prowidesoftware.swift.model.field.Field61;
import com.prowidesoftware.swift.model.mt.mt9xx.MT940;
import database.ConnectionHandler;
import exceptions.WrongFileFormatException;
import lendaryDAO.LendaryDAO2;
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
    public UploadServlet() throws IOException {
        super();
        try {
			connectionHandler = new ConnectionHandler();
		} catch (SQLException e) {
			
		}
        parser = new Parser();
    }

	/**
	 * Takes the inputStream from filePart and creates a new instance of MT940 file. Later the parsing is done and connectionHandler inserts the data into database
	 * @param request from HTTPServlet
	 * @param response from HTTPResponse
	 * @throws ServletException
	 * @throws IOException
	 */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Part filePart = request.getPart("file"); // Retrieves <input type="file" name="file">
        String msg = "";
        Balance balance = new Balance();
        if (filePart.getSubmittedFileName() != "") {
			mt940 = new MT940(filePart.getInputStream());
			if (mt940 != null && mt940.isMT()) {
				if (parser.isSafe(mt940.toString())) {
					try {
						is_correct = true;
						if (mt940.getField25() != null) {
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

						boolean inserted;
						try {
							inserted = connectionHandler.insertBalance(balance);
							msg = "";
							if (!inserted) {
								connectionHandler.removeBalance(balance.getAccountID());
								inserted = connectionHandler.insertBalance(balance);
								msg = "alert('The file already exited and was replaced!');";
							}
						} catch (SQLException e) {
							msg = "alert('Couldn't connect to the database!\n"
									+ "Please check your internet connection');";
						}
					} catch (Exception e) {
						msg = "alert('The file content doesn't match MT940 formatting');";
					}
				}else{
					msg = "alert('The file is not safe');";
				}
			}
		}
        else {
        	msg = "alert('No file Has been inserted');";
        }
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<HTML     onload='lll()'>\n" +
                "<HEAD  onload='lll()'><TITLE></TITLE>" +
                "</HEAD>\n" +
                "<BODY onload='lll()'>\n" +

                " <script> " +
                         " function lll(){\n" +
                          	msg +"\n"+
                           "  window.close(); " +
                             "}"+
                         "</script>"+

                "</BODY></HTML>");
    }
}



