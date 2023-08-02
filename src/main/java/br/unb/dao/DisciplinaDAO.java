package br.unb.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.unb.dominio.Disciplina;

public class DisciplinaDAO {
	private static final String URL = "jdbc:h2:tcp://localhost/~/test";
	private static final String USER = "sa";
	private static final String PASSWORD = "";
	
	public DisciplinaDAO() {
		try {
			Class.forName("org.h2.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void salvar(Disciplina disciplina) throws SQLException {
		
		try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
			String sql = "INSERT INTO disciplina (nome, local, turma, curso) VALUES (?, ?, ?,?)";
			try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
				statement.setString(1, disciplina.getNome());
				statement.setString(2, disciplina.getLocal());
				statement.setString(3, disciplina.getTurma());
				statement.setString(4, disciplina.getCurso());
				statement.executeUpdate();

				ResultSet generatedKeys = statement.getGeneratedKeys();
				if (generatedKeys.next()) {
					disciplina.setId(generatedKeys.getInt(1));
				}
			}
		}
	}

	public void atualizar(Disciplina disciplina) throws SQLException {
		System.out.println("DisciplinaDAO::atualizar::disciplina->"+disciplina);
		try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
			String sql = "UPDATE disciplina SET nome = ?, local = ? , turma = ?, curso = ? WHERE id = ?";
			try (PreparedStatement statement = connection.prepareStatement(sql)) {
				statement.setString(1, disciplina.getNome());
				statement.setString(2, disciplina.getLocal());
				statement.setString(3, disciplina.getTurma());
				statement.setString(4, disciplina.getCurso());
				statement.setInt(5, disciplina.getId());
				statement.executeUpdate();
			}
		}
	}

	public void excluir(int id) throws SQLException {
		try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
			String sql = "DELETE FROM disciplina WHERE id = ?";
			try (PreparedStatement statement = connection.prepareStatement(sql)) {
				statement.setInt(1, id);
				statement.executeUpdate();
			}
		}
	}

	public Disciplina buscarPorId(int id) throws SQLException {
		try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
			String sql = "SELECT * FROM disciplina WHERE id = ?";
			try (PreparedStatement statement = connection.prepareStatement(sql)) {
				statement.setInt(1, id);
				try (ResultSet resultSet = statement.executeQuery()) {
					if (resultSet.next()) {
						String nome = resultSet.getString("nome");
						String local = resultSet.getString("local");
						String turma = resultSet.getString("turma");
						String curso = resultSet.getString("curso");
						Disciplina disciplina = new Disciplina(nome, local,turma,curso);
						disciplina.setId(id);
						return disciplina;
					}
				}
			}
		}
		return null;
	}

	public List<Disciplina> listarTodos() throws SQLException, ClassNotFoundException {
		System.out.println("DisciplinaDAO::listarTodos");
		List<Disciplina> disciplinas = new ArrayList<>();
		try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
			String sql = "SELECT * FROM disciplina";
			try (PreparedStatement statement = connection.prepareStatement(sql)) {
				try (ResultSet resultSet = statement.executeQuery()) {
					while (resultSet.next()) {
						int id = resultSet.getInt("id");
						String nome = resultSet.getString("nome");
						String local = resultSet.getString("local");
						String turma = resultSet.getString("turma");
						String curso = resultSet.getString("curso");
						Disciplina disciplina = new Disciplina(nome, local, turma, curso);
						disciplina.setId(id);
						disciplinas.add(disciplina);
					}
				}
			}
		}
		System.out.println("DisciplinaDAO::listarTodos::disciplinas->"+disciplinas);
		return disciplinas;
	}
}

