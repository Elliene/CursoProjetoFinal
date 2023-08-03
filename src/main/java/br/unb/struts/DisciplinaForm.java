package br.unb.struts;

import org.apache.struts.action.ActionForm;

@SuppressWarnings("serial")
public class DisciplinaForm extends ActionForm {
	private int id;
	private String nome;
	private String local;
	private String turma;
	private String curso;
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
	public String getLocal() {
		return local;
	}
	public void setLocal(String local) {
		this.local = local;
	}
	public String getTurma() {
		return turma;
	}
	public void setTurma(String turma) {
		this.turma = turma;
	}
	public String getCurso() {
		return curso;
	}
	public void setCurso(String curso) {
		this.curso = curso;
	}


	

	// Getters e Setters
	// ...

}
