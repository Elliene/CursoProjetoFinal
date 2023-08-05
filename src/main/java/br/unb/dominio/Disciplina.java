package br.unb.dominio;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Column;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Disciplina {

	//mapeia a classe para o sql**classe da camada de negocio
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //Estrategia para criação do ID,
	private int id; 
	@Column 
	private String nome; // nome da disciplina
	private String curso; // nome do curso: civil, medicina, direito
	private String turma; // 20231
	private String local; // Bloco sala	
	
	public Disciplina(String nome, String curso, String turma, String local) {
		super();
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
	
	@Override
	public String toString() {
		return "Disciplina { " + " id =" + id + ", nome=" + nome + ", curso=" + curso + ", turma=" + turma + ", local=" 
	   + local +  '\'' + '}';
	}



	
}
