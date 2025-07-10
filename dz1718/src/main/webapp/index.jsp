<% /*Написать страницу на JSP / JSTL / JSF с кодом на Java
Ввести число N, посчитать:
1) факториал
2) Фибоначчи
Запустить под сервером приложений WildFly (GlassFish, Tomcat)*/%> 

<%@ page import="java.math.BigInteger" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<%
    String nStr = request.getParameter("n");
    String error = null;
    String factorialResult = "";
    String fibonacciResult = "";

    if (nStr != null && !nStr.trim().isEmpty()) {
        try {
            int n = Integer.parseInt(nStr);
            if (n < 0) throw new IllegalArgumentException("Число должно быть неотрицательным.");

            BigInteger factorial = BigInteger.ONE;
            for (int i = 1; i <= n; i++) {
                factorial = factorial.multiply(BigInteger.valueOf(i));
            }
            factorialResult = factorial.toString();

            StringBuilder fibBuilder = new StringBuilder();
            if (n >= 1) {
                fibBuilder.append("0");
            }
            if (n >= 2) {
                fibBuilder.append(", 1");
            }
            if (n > 2) {
                int a = 0, b = 1;
                for (int i = 2; i < n; i++) {
                    int next = a + b;
                    a = b;
                    b = next;
                    fibBuilder.append(", ").append(b);
                }
            }
            fibonacciResult = fibBuilder.toString();

        } catch (NumberFormatException e) {
            error = "Введите корректное число.";
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }
    }
%>

<html>
<head>
    <title>Факториал и Фибоначчи</title>
    <script>
        function submitForm(e) {
            e.preventDefault();
            const formData = new FormData(document.getElementById("calcForm"));
            let resultDiv = document.getElementById("result");

            let data = {};
            formData.forEach((value, key) => data[key] = value);

            fetch(window.location.href, {
                method: "POST",
                headers: {
                    "Content-Type": "application/x-www-form-urlencoded"
                },
                body: new URLSearchParams(data).toString()
            })
            .then(response => response.text())
            .then(html => {
                document.open();
                document.write(html);
                document.close();
                window.scrollTo(0, document.body.scrollHeight);
            });
        }
    </script>
</head>
<body>
    <h2>Введите число N:</h2>
    <form id="calcForm" onsubmit="submitForm(event)">
        <input type="number" name="n" min="0" required>
        <button type="submit">Вычислить</button>
    </form>

    <% if (error != null) { %>
        <p style="color:red;"><%= error %></p>
    <% } %>

    <% if (!factorialResult.isEmpty()) { %>
        <div id="result">
            <h3>Результат:</h3>
            <p>Факториал: <%= factorialResult %></p>
            <p>Фибоначчи: <%= fibonacciResult %></p>
        </div>
    <% } %>
</body>
</html>