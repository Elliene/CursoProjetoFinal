package br.unb.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import br.unb.dominio.Produto;

public class ProdutoDAO {
	public Produto salvar(Produto produto) {
		// Configuração da sessão do Hibernate (SessionFactory)
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();

		// Iniciando a transação
		Transaction tx = session.beginTransaction();

		// Salvando a pessoa no banco de dados
		session.save(produto);

		// Comitando a transação
		tx.commit();

		// Fechando a sessão
		session.close();
		return produto;

	}
}
