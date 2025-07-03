package com.example;

import java.sql.*;

public class Main {
    public static void main(String[] args) {
        String url = "jdbc:oracle:thin:@localhost:1521/XEPDB1";
        String user = "cafe";
        String password = "2845";

                String query = """
            SELECT 
                m.CATEGORY,
                COUNT(oi.ORDER_ITEM_ID) AS TOTAL_ITEMS,
                SUM(oi.SUBTOTAL) AS TOTAL_REVENUE
            FROM MENUS m
            JOIN ORDER_ITEMS oi ON m.MENU_ID = oi.MENU_ID
            GROUP BY m.CATEGORY
            """;

        try (Connection conn = DriverManager.getConnection(url, user, password);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            System.out.println("Connected to Oracle XE!");
            System.out.println("Sales Statistics by Category:");

            while (rs.next()) {
                String category = rs.getString("CATEGORY");
                int totalItems = rs.getInt("TOTAL_ITEMS");
                double totalRevenue = rs.getDouble("TOTAL_REVENUE");

                System.out.printf("Category: %s | Items Sold: %d | Revenue: %.2f%n",
                        category, totalItems, totalRevenue);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}