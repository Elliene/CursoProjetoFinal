package br.unb.dominio;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.*;

@Entity
@Table(name = "disciplina")
public class Disciplina {

	// mapeia a classe para o sql**classe da camada de negocio
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Estrategia para criação do ID,
	private int id;
	@Column(name = "nome")
	private String nome; // nome da disciplina

	@Column(name = "curso")
	private String curso; // nome do curso: civil, medicina, direito

	@Column(name = "turma")
	private String turma; // 20231

	@Column(name = "local")
	private String local; // Bloco sala

	public Disciplina() {
	}
/*
	@ManyToMany
	@JoinTable(name = "aluno_disciplina", joinColumns = @JoinColumn(name = "disciplina_id"), inverseJoinColumns = @JoinColumn(name = "aluno_id"))
	private List<Aluno> Alunos;

	@ManyToOne
	@JoinColumn(name = "professor_id")
	private Professor professor;*/

	public Disciplina(String nome, String curso, String turma, String local) {
		super();
		this.nome = nome;
		this.curso = curso;
		this.turma = turma;
		this.local = local;
	}

	public Disciplina(int id, String nome, String curso, String turma, String local) {
		super();
		this.id = id;
		this.nome = nome;
		this.curso = curso;
		this.turma = turma;
		this.local = local;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCurso() {
		return curso;
	}

	public void setCurso(String curso) {
		this.curso = curso;
	}

	public String getTurma() {
		return turma;
	}

	public void setTurma(String turma) {
		this.turma = turma;
	}

	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
	}

/*	public List<Aluno> getAlunos() {
		return Alunos;
	}

	public void setAlunos(List<Aluno> alunos) {
		Alunos = alunos;
	}

	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}*/

	@Override
	public String toString() {
		return "Disciplina [id =" + id + ", nome=" + nome + ", curso=" + curso + ", turma=" + turma + ", local=" + local
				+ "]";
	}

}
