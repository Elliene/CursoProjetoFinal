package br.unb.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.unb.dominio.Aluno;

public class AlunoDAO {
	private static final String URL = "jdbc:h2:tcp://localhost/~/test";
	private static final String USER = "sa";
	private static final String PASSWORD = "";
	
	public AlunoDAO() {
		try {
			Class.forName("org.h2.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void salvar(Aluno aluno) throws SQLException {
		
		try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
			String sql = "INSERT INTO aluno (nome, matricula) VALUES (?, ?)";
			try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
				statement.setString(1, aluno.getNome());
				statement.setString(2, aluno.getMatricula());
				statement.executeUpdate();

				ResultSet generatedKeys = statement.getGeneratedKeys();
				if (generatedKeys.next()) {
					aluno.setId(generatedKeys.getInt(1));
				}
			}
		}
	}

	public void atualizar(Aluno aluno) throws SQLException {
		System.out.println("AlunoDAO::atualizar::aluno->"+aluno);
		try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
			String sql = "UPDATE aluno SET nome = ?, matricula = ? WHERE id = ?";
			try (PreparedStatement statement = connection.prepareStatement(sql)) {
				statement.setString(1, aluno.getNome());
				statement.setString(2, aluno.getMatricula());
				statement.setInt(3, aluno.getId());
				statement.executeUpdate();
			}
		}
	}

	public void excluir(int id) throws SQLException {
		try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
			String sql = "DELETE FROM aluno WHERE id = ?";
			try (PreparedStatement statement = connection.prepareStatement(sql)) {
				statement.setInt(1, id);
				statement.executeUpdate();
			}
		}
	}

	public Aluno buscarPorId(int id) throws SQLException {
		try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
			String sql = "SELECT * FROM aluno WHERE id = ?";
			try (PreparedStatement statement = connection.prepareStatement(sql)) {
				statement.setInt(1, id);
				try (ResultSet resultSet = statement.executeQuery()) {
					if (resultSet.next()) {
						String nome = resultSet.getString("nome");
						String matricula = resultSet.getString("matricula");
						Aluno aluno = new Aluno(nome, matricula);
						aluno.setId(id);
						return aluno;
					}
				}
			}
		}
		return null;
	}

	public List<Aluno> listarTodos() throws SQLException, ClassNotFoundException {
		System.out.println("AlunoDAO::listarTodos");
		List<Aluno> alunos = new ArrayList<>();
		try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
			String sql = "SELECT * FROM aluno";
			try (PreparedStatement statement = connection.prepareStatement(sql)) {
				try (ResultSet resultSet = statement.executeQuery()) {
					while (resultSet.next()) {
						int id = resultSet.getInt("id");
						String nome = resultSet.getString("nome");
						String matricula = resultSet.getString("matricula");
						Aluno aluno = new Aluno(nome, matricula);
						aluno.setId(id);
						alunos.add(aluno);
					}
				}
			}
		}
		System.out.println("AlunoDAO::listarTodos::alunos->"+alunos);
		return alunos;
	}
}
