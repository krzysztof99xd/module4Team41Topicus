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

    private static Map<String, LendaryModel> fileProvider = new HashMap<String, LendaryModel>();

//    public LendaryDAO(Part filePart) throws ServletException, IOException {
////         filePart = request.getPart("file");
//     String fileName = String.valueOf("fileName");
//     File file = new File("http://localhost:8080/Module4Team41Topicus_war_exploded/rest/files" + fileName);
//        OutputStream outfile = new FileOutputStream(file);
//        InputStream fileContent = filePart.getInputStream();
//        int read = 0;
//        byte[] bytes = new byte[1024];
//        while((read = fileContent.read(bytes)) != -1){
//            outfile.write(bytes, 0 , read);
//        }
//
//    }
    public LendaryDAO() throws IOException {
        LendaryModel file1 = null;

//        File file = new File("C:/Users/48504/Desktop/mt940-npp-sample-file.940");

//        File file = new File("http://localhost:8080/Module4Team41Topicus_war_exploded/upload");
//        MT940 mt = new MT940(file);
//        String text = "start";
//        if(mt.getField20().getValue() != null){
//            text += mt.getField20().getValue();
//        }
//        try {
//            file1 = new LendaryModel("1","firstMT940file",  text);
//        } catch (IOException e) {
//            e.printStackTrace();
//            System.out.print("it didnt work");
//        }
//        fileProvider.put("1", file1);
    }
    public static Map<String, LendaryModel> getModel() {
        return fileProvider;
    }

}
