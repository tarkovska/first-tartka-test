package sql;

import java.sql.*;
import java.util.*;

/**
 * Created by Nataliya_Tarkovska on 2/7/2018.
 */
public class connection {

    private static final String url = "jdbc:mysql://netlab.mysql.tools:3306/netlab_tartka";
    private static final String user = "netlab_tartka";
    private static final String password = "kwgdy99u";


    private static Connection con;
    private static Statement stmt;
    private static ResultSet rs;

    public static void main(String args[]) {

        ArrayList<String> fields = new ArrayList<String>();

        String query = "SELECT post_title FROM wp_posts where post_title like 'Home%'";

        try {
            // opening database connection to MySQL server
            con = DriverManager.getConnection(url, user, password);

            // getting Statement object to execute query
            stmt = con.createStatement();

            // executing SELECT query
            rs = stmt.executeQuery(query);

            while (rs.next()) {
                fields.add(rs.getString(1));
            }
            System.out.println("count"+fields.size());
            System.out.println("post_title : " + fields);
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        } finally {
            //close connection ,stmt and resultset here
            try { con.close(); } catch(SQLException se) { /*can't do anything */ }
            try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
            try { rs.close(); } catch(SQLException se) { /*can't do anything */ }
        }
    }
}
