<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Sucesso</title>
</head>
<body>
    <h1>Aluno vinculado à Disciplina:</h1>
    <p>Aluno: <%= ((br.unb.dominio.Aluno) request.getAttribute("aluno")).getNome() %>, Matrícula: <%= ((br.unb.dominio.Aluno) request.getAttribute("aluno")).getMatricula() %></p>
    <p>Disciplina: <%= ((br.unb.dominio.Disciplina) request.getAttribute("disciplina")).getNome() %>, Turma: <%= ((br.unb.dominio.Disciplina) request.getAttribute("disciplina")).getTurma() %></p>
</body>
</html>