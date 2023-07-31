<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<html>
<head>
    <title>Cadastro de Alunos</title>
  <!-- Outras meta tags, títulos, etc... -->
  <link href="https://fonts.googleapis.com/css?family=Roboto&display=swap" rel="stylesheet">
   <link rel="stylesheet" type="text/css" href="css/estilo.css">
</head>
<body>

    <h1>Cadastro de Alunos</h1>

    <html:form action="/aluno" method="post">

        <html:hidden property="method" value="${alunoForm.id == 0 ? 'salvar' : 'atualizar'}"/>
		<html:hidden property="id" value="${alunoForm.id}"/> <!-- Incluímos o campo oculto para o ID -->
		
		<span style="color: red">
        <html:errors/>
		</span>
        <p>
            <label for="nome">Nome:</label>
            <html:text property="nome" size="50"/>
        </p>

        <p>
            <label for="matricula">Matrícula:</label>
            <html:text property="matricula" size="20"/>
        </p>

        <p>
            <html:submit value="${alunoForm.id == 0 ? 'Salvar' : 'Atualizar'}"/>
        </p>

    </html:form>

    <h2>Lista de Alunos</h2>

    <table border="1">
        <tr>
            <th>ID</th>
            <th>Nome</th>
            <th>Matrícula<repareStatement(sql)) {
				statement.setString(1, aluno.getNome());/th>
            <th>Editar</th>
            <th>Excluir</th>
        </tr>
        <logic:iterate name="alunos" id="aluno">
             <tr>
                <td><bean:write name="aluno" property="id" /></td>
                <td><bean:write name="aluno" property="nome" /></td>
                <td><bean:write name="aluno" property="matricula" /></td>
                <td>
                    <a href="<%= request.getContextPath() %>/aluno.do?method=editar&id=<bean:write name='aluno' property='id' />">Editar</a>
                </td>
                <td>
                    <a href="<%= request.getContextPath() %>/aluno.do?method=excluir&id=<bean:write name='aluno' property='id' />"
                       onclick="return confirm('Tem certeza que deseja excluir esse aluno?');">Excluir</a>
                </td>
            </tr>
        </logic:iterate>
    </table>

</body>
</html>
