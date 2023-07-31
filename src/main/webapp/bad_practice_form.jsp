<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.sql.*" %>
<%@ page import="java.io.PrintWriter" %>

<%
    // Declaração de variáveis
    Connection conn = null;
    PreparedStatement pstmt = null;

    // Parâmetros do formulário
    String nome = request.getParameter("nome");
    String matricula = request.getParameter("matricula");

    try {
        // Conexão com o banco de dados H2
        Class.forName("org.h2.Driver");
        String dbUrl = "jdbc:h2:tcp://localhost/~/test"; // Caminho para o banco de dados H2
        String dbUser = "sa";
        String dbPassword = "";

        conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword);

        // Inserindo o aluno no banco de dados
        pstmt = conn.prepareStatement("INSERT INTO aluno (nome, matricula) VALUES (?, ?)");
        pstmt.setString(1, nome);
        pstmt.setString(2, matricula);
        pstmt.executeUpdate();

        // Exibindo mensagem de sucesso
        out.println("<h1>Cadastro de Aluno</h1>");
        out.println("<p>Aluno cadastrado com sucesso!</p>");
    } catch (Exception e) {
        e.printStackTrace();
        // Exibindo mensagem de erro em caso de falha
        out.println("<h1>Erro no Cadastro</h1>");
        out.println("<p>Ocorreu um erro ao cadastrar o aluno.</p>");
    } finally {
        // Fechando os recursos
        try {
            if (pstmt != null) pstmt.close();
            if (conn != null) conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
%>

<h2>Formulário de Cadastro de Aluno</h2>
<form action="bad_practice_form.jsp" method="post">
    <label for="nome">Nome:</label>
    <input type="text" name="nome" required><br>

    <label for="matricula">Matrícula:</label>
    <input type="text" name="matricula" required><br>

    <input type="submit" value="Cadastrar">
</form>
