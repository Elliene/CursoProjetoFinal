<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<html>
<head>
    <title>Cadastro de Disciplina</title>
  <!-- Outras meta tags, títulos, etc... -->
  <link href="https://fonts.googleapis.com/css?family=Roboto&display=swap" rel="stylesheet">
   <link rel="stylesheet" type="text/css" href="css/estilo.css">
</head>
<body>

    <h1>Cadastro das Disciplinas da eliene teste 1</h1>

    <html:form action="/disciplina" method="post">

        <html:hidden property="method" value="${disciplinaForm.id == 0 ? 'salvar' : 'atualizar'}"/>
		<html:hidden property="id" value="${disciplinaForm.id}"/> <!-- Incluímos o campo oculto para o ID -->
		
		<span style="color: red">
        <html:errors/>
		</span>
        <p>
            <label for="nome">Nome:</label>
            <html:text property="nome" size="50"/>
        </p>

        <p>
            <label for="local">Local:</label>
            <html:text property="local" size="50"/>
        </p>
        
        <p>
            <label for="turma">Turma:</label>
            <html:text property="turma" size="20"/>
        </p>
        
                <p>
            <label for="curso">Curso:</label>
            <html:text property="curso" size="30"/>
        </p>

        <p>
            <html:submit value="${disciplinaForm.id == 0 ? 'Salvar' : 'atualizar'}"/>
        </p>

    </html:form>

    <h2>Lista das Disciplinas</h2>

    <table border="1">
        <tr>
            <th>ID</th>
            <th>Nome</th>
            <th>Local</th>
            <th>Turma</th>
            <th>Curso<repareStatement(sql)) {
				statement.setString(1, disciplina.getNome());/th>
            <th>Editar</th>
            <th>Excluir</th>
        </tr>
        <logic:iterate name="disciplinas" id="disciplina">
             <tr>
                <td><bean:write name="disciplina" property="id" /></td>
                <td><bean:write name="disciplina" property="nome" /></td>
                <td><bean:write name="disciplina" property="local" /></td>
                <td><bean:write name="disciplina" property="turma" /></td>
                <td><bean:write name="disciplina" property="curso" /></td>
                <td>
                    <a href="<%= request.getContextPath() %>/disciplina.do?method=editar&id=<bean:write name='disciplina' property='id' />">Editar</a>
                </td>
                <td>
                    <a href="<%= request.getContextPath() %>/disciplina.do?method=excluir&id=<bean:write name='disciplina' property='id' />"
                       onclick="return confirm('Tem certeza que deseja excluir a disciplina?');">Excluir</a>
                </td>
            </tr>
        </logic:iterate>
    </table>

</body>
</html>