<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<html>
<head>
    <title>Vincular Aluno a Disciplina</title>
</head>
<body>
    <h1>Vincular Aluno a Disciplina</h1>
    
    <html:form action="/alunoDisciplina">
        <label for="aluno">Selecione o Aluno:</label>
        <html:select property="alunoId">
            <html:optionsCollection name="alunoList" label="nome" value="id" />
        </html:select>

        <label for="disciplina">Selecione a Disciplina:</label>
        <html:select property="disciplinaId">
            <html:optionsCollection name="disciplinaList" label="nome" value="id" />
        </html:select>

        <html:submit value="Vincular" />
    </html:form>
</body>
</html>






























