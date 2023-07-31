package br.unb.dominio;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "produtos")
public class Produto {
    // campos e anotações de mapeamento
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
	
    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Produto [categoria=" + categoria + "]";
	}
    
}
