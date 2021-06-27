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

    public static User getUser() {
        return user;
    }

    public static void setUser(User user) {
        UserDAO.user = user;
    }

    public static boolean isLoggedIn() {
        return loggedIn;
    }

    public static void setLoggedIn(boolean loggedIn) {
        UserDAO.loggedIn = loggedIn;
    }

}