package br.unb.dominio.heranca;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import br.unb.dominio.Pessoa;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class PessoaJoined {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String nome;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public String toString() {
		return "PessoaJoined [id=" + id + ", nome=" + nome + "]";
	}

    // Outros atributos e mapeamento comuns a todas as subclasses
    
}
