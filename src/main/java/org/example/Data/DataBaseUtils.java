package org.example.Data;


import org.ini4j.Wini;

import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;



public class DataBaseUtils extends SQLUtils {

    public DataBaseUtils() throws IOException {
        Wini ini = new Wini(new File("C:\\Users\\Admin\\Desktop\\SpringWebApplication\\src\\main\\resources\\application.properties"));

        String pDatabaseUrl = ini.get("pDatabaseUrl", "spring.datasource.url");
        String pUser = ini.get("pUser","spring.datasource.username");
        String pPassword = ini.get("pPassword","spring.datasource.password");


        try {
            this.setDatabase(pDatabaseUrl, pUser, pPassword);

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
    public String getInfoStaff() {

        ResultSet rs;

        try {
            rs = onQuery("SELECT INFO FROM INFOBOX WHERE ROLLE = 'staff'");
            rs.next();
            return rs.getString("INFO");

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Failed onQuery");
            return "";
        }

    }
    public String getInfoStudent() {

        ResultSet rs;

        try {
            rs = onQuery("SELECT INFO FROM INFOBOX WHERE ROLLE = 'student'");
            rs.next();
            return rs.getString("INFO");

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Failed onQuery");
            return "";
        }
    }
    public void editInfoStaff(String text) {
        try {
            onExecute("UPDATE INFOBOX SET INFO = ? WHERE ROLLE = 'staff'", text);
            System.out.println("Changed Info Text for staff");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Failed onExecute");

        }
    }
    public void editInfoStudent(String text) {
        try {
            onExecute("UPDATE INFOBOX SET INFO = ? WHERE ROLLE = 'student'", text);
            System.out.println("Changed Info Text for student");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Failed onExecute");
        }
    }
}
