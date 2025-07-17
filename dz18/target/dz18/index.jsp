<% /*Реализовать web-приложение на JSP (JSF) запрос к БД (Oracle, SQL Server) и 
вывод данных с помощью JDBC или Hibernate
Результат в браузере.*/%> 


<%@ page import="com.example.DatabaseHelper" %>
<%@ page import="java.util.List" %>
<%@ page import="java.io.InputStream" %>
<%@ page import="java.util.Properties" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<%

    Properties props = new Properties();
    InputStream input = application.getResourceAsStream("/WEB-INF/classes/db.properties");
    props.load(input);

    String url = props.getProperty("db.url");
    String user = props.getProperty("db.user");
    String password = props.getProperty("db.password");

    DatabaseHelper dbHelper = new DatabaseHelper(url, user, password);
    List<String> data = dbHelper.fetchData("SELECT name FROM employees");
%>

<html>
<head>
    <title>DB Query Result</title>
</head>
<body>
    <h2>Результаты из БД:</h2>
    <ul>
        <% for (String item : data) { %>
            <li><%= item %></li>
        <% } %>
    </ul>
</body>
</html>