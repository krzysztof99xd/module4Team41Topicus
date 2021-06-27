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


public enum LendaryDAO2{
    instance;

    private static Analytics analytics;
    private static String accountID;

    /**
     * sets the acoount id
     * @param  accountID account ID
     */
    public static void setAccountID(String accountID) {
        LendaryDAO2.accountID = accountID;
    }

    /**
     * gets the analytics
     * @return the analytics
     */
    public static Analytics getAnalytics() {
        return analytics;
    }

    /**
     * sets the analytics
     * @param analytics analytics object
     */
    public static void setAnalytics(Analytics analytics) {
        LendaryDAO2.analytics = analytics;
    }

    /**
     * gets the account id
     * @return the account id
     */
    public static String getAccountID() {
        return accountID;
    }
}
