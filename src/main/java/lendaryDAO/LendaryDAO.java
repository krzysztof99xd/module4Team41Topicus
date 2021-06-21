package lendaryDAO;
import com.prowidesoftware.swift.model.mt.mt9xx.MT940;
import lendaryModel.*;

//import javax.servlet.ServletException;
//import javax.servlet.annotation.MultipartConfig;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.Part;
//import java.io.*;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.Scanner;


public enum LendaryDAO{
    instance;

    private static Analytics analytics;
    private static String accountID;

    public static void setAccountID(String accountID) {
        LendaryDAO.accountID = accountID;
    }

    public static Analytics getAnalytics() {
        return analytics;
    }

    public static void setAnalytics(Analytics analytics) {
        LendaryDAO.analytics = analytics;
    }

    public static String getAccountID() {
        return accountID;
    }
}
