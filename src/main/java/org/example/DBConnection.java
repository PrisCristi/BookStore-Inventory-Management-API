package org.example;

import java.sql.*;

public class DBConnection {

    public static Connection getConnection() {
        String url = "jdbc:mysql://localhost:3306/bookstore";
        String user = "root";
        String password = "Berlin!!2023";

        // Abrir conexao com DB e conectcar com ela
        try {
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static void closeConnection(Connection con) {
        try {
            if (con != null)
                con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

       /*

        String query = "SELECT * FROM books WHERE title > ? AND author = ?";
        PreparedStatement pst =  pst = con.prepareStatement(query);

        pst.setString(1, "The Color Purple");
        pst.setString(2, "Ferminism is for Everybody");
        ResultSet myRs = pst.executeQuery();
        System.out.println("Title\t Author");

        while (myRs.next()) {
        String name = myRs.getString("Author");
        String title = myRs.getString("Title");
        System.out.println(name + "\t " + title);
        }