package com.example;

import java.sql.*;

public class Main {
    public static void main(String[] args) {
        String url = "jdbc:oracle:thin:@localhost:1521/XEPDB1";
        String user = "andrey";
        String password = "2845";

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            System.out.println("Connected to Oracle XE!");

            // Создание таблицы с правильной структурой
            Statement stmt = conn.createStatement();
            stmt.execute("""
                CREATE TABLE test_table1 (
                    id          NUMBER PRIMARY KEY,
                    name        VARCHAR2(50),
                    created_at  DATE
                )
            """);

            // Вставка данных
            stmt.executeUpdate("INSERT INTO test_table1 (id, name, created_at) VALUES (1, 'Test', SYSDATE)");

            // Выборка данных
            ResultSet rs = stmt.executeQuery("SELECT * FROM test_table1");
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id") + ", Name: " + rs.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}