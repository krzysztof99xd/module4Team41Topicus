package lendaryDAO;
import com.prowidesoftware.swift.model.mt.mt9xx.MT940;
import lendaryModel.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

@MultipartConfig
@WebServlet(name ="file", urlPatterns = "/files")
public class LendaryDAO extends HttpServlet{

    private static Map<String, Balance> fileProvider = new HashMap<String, Balance>();

    public LendaryDAO() throws IOException {
        Balance file1 = null;
    }
    public static Map<String, Balance> getModel() {
        return fileProvider;
    }

}
