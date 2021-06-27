package lendaryDAO;

import database.ConnectionHandler;
import lendaryModel.Analytics;
import lendaryModel.User;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.commons.codec.binary.Hex;


import java.sql.*;

public enum UserDAO {
   instance;


    private static User user;
    private static boolean loggedIn = false;


     /**
     * gets the user
     * @return the user
     */
    public static User getUser(){
        return user;
    }

    /**
     * sets a user
     * @param user user
     */
    public static void setUser(User user) {
        UserDAO.user = user;
    }

    /**
     * checks if user is logged in
     * @return true when user logged in
     */
    public static boolean isLoggedIn() {
        return loggedIn;
    }

    /**
     * sets that user logged in
     * @param loggedIn
     */
    public static void setLoggedIn(boolean loggedIn) {
        UserDAO.loggedIn = loggedIn;
    }

}