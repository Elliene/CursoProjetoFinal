package br.unb.struts;

import org.apache.struts.action.ActionForm;
//clase de camada de apresentação
@SuppressWarnings("serial")
public class DisciplinaForm extends ActionForm {
	private int id;
	private String nome;
	private String local;
	private String turma;
	private String curso;
	private String method;
	
	
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

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

  //aqui pode ter relacionamentos das entidades
	

	// Getters e Setters
	// ...

}
