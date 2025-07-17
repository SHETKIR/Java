package com.example;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class DatabaseHelper 
{

    private String url;
    private String user;
    private String password;

    public DatabaseHelper(String url, String user, String password) 
    {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    public List<String> fetchData(String query) throws SQLException 
    {
        List<String> results = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(url, user, password);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query)) 
            {
                while (rs.next()) 
                {
                    results.add(rs.getString(1));
                }
            }
            return results;
    }
}