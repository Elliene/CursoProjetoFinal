package br.unb.dominio.heranca.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import br.unb.dao.HibernateUtil;
import br.unb.dominio.heranca.PessoaSingleTable;

public class PessoaDAO {

	public PessoaSingleTable salvar(PessoaSingleTable pessoa) {
		// Configuração da sessão do Hibernate (SessionFactory)
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();

		// Iniciando a transação
		Transaction tx = session.beginTransaction();

		// Salvando a pessoa no banco de dados
		session.save(pessoa);

		// Comitando a transação
		tx.commit();

		// Fechando a sessão
		session.close();
		return pessoa;

	}

	public PessoaSingleTable getById(Long id) {
		Session session = HibernateUtil.getSessionFactory().openSession();

		// Usando get() para ler a pessoa com o ID especificado
		PessoaSingleTable pessoa = (PessoaSingleTable) session.get(PessoaSingleTable.class, id);

//		// Carregando uma pessoa com ID 
//		Pessoa pessoa = (Pessoa) session.load(Pessoa.class, id);

		// Encerrando a sessão
		session.close();
		return pessoa;
	}

	public PessoaSingleTable update(PessoaSingleTable p) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();

		// Atualizando a pessoa no banco de dados
		session.update(p);

		tx.commit();
		session.close();

		return p;

	}

	public void delete(Long id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();

		// Lendo uma pessoa existente pelo ID
		PessoaSingleTable pessoa = (PessoaSingleTable) session.get(PessoaSingleTable.class, id);

		// Excluindo a pessoa do banco de dados
		session.delete(pessoa);

		tx.commit();
		session.close();

	}

	public List<PessoaSingleTable> findAll() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Query query = session.createQuery("FROM Pessoa");
		List<PessoaSingleTable> pessoas = query.list();
		session.close();
		return pessoas;
	}

	public List<PessoaSingleTable> findPorIdadeMinima(int idade) {
		int idadeMinima = idade;
		Session session = HibernateUtil.getSessionFactory().openSession();
		Query query = session.createQuery("FROM Pessoa WHERE idade > :idade");
		query.setParameter("idade", idadeMinima);
		List<PessoaSingleTable> pessoas = query.list();
		session.close();
		return pessoas;
	}

	public List<Object[]> pessoasPorProjeto(int idProjeto) {
		long projetoId = 1L;
		Session session = HibernateUtil.getSessionFactory().openSession();
		Query query = session.createQuery(
				"SELECT COUNT(p), p.nome FROM Pessoa p WHERE p.projeto.id = :projetoId GROUP BY p.nome ORDER BY p.nome");
		query.setParameter("projetoId", projetoId);
		List<Object[]> result = query.list();
		session.close();
		return result;
	}

	public List<PessoaSingleTable> listByNomeSQL(String nome) {
		String sql = "SELECT * FROM pessoa WHERE nome = :nome";
		Session session = HibernateUtil.getSessionFactory().openSession();
		SQLQuery sqlQuery = session.createSQLQuery(sql);
		sqlQuery.addEntity(PessoaSingleTable.class);
		sqlQuery.setParameter("nome", nome);
		List<PessoaSingleTable> pessoas = sqlQuery.list();
		session.close();
		return pessoas;

	}
}
