<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.sql.*" %>
<%@ page import="java.io.PrintWriter" %>

<%
    // Declaração de variáveis
    Connection conn = null;
    Statement stmt = null;
    ResultSet rs = null;

    try {
        // Conexão com o banco de dados H2
        Class.forName("org.h2.Driver");
        String dbUrl = "jdbc:h2:tcp://localhost/~/test"; // Caminho para o banco de dados H2
        String dbUser = "sa";
        String dbPassword = "";

        conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword);

        // Consulta ao banco de dados
        stmt = conn.createStatement();
        rs = stmt.executeQuery("SELECT * FROM aluno");

        // Exibindo os resultados
        out.println("<h1>Lista de Alunos</h1>");
        while (rs.next()) {
            int id = rs.getInt("id");
            String nome = rs.getString("nome");
            String matricula = rs.getString("matricula");
            out.println("<p>ID: " + id + ", Nome: " + nome + ", Matrícula: " + matricula + "</p>");
        }
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        // Fechando os recursos
        try {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
%>
