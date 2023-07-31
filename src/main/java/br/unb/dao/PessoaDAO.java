package br.unb.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import br.unb.dominio.Endereco;
import br.unb.dominio.Pessoa;
import br.unb.dominio.Projeto;

public class PessoaDAO {

	public Pessoa salvar(Pessoa pessoa) {
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
	public Projeto salvar(Projeto Projeto) {
		// Configuração da sessão do Hibernate (SessionFactory)
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();

		// Iniciando a transação
		Transaction tx = session.beginTransaction();

		// Salvando a pessoa no banco de dados
		session.save(Projeto);

		// Comitando a transação
		tx.commit();

		// Fechando a sessão
		session.close();
		return Projeto;

	}
	public Projeto getProjetoById(Long id) {
		Session session = HibernateUtil.getSessionFactory().openSession();

		// Usando get() para ler a pessoa com o ID especificado
		Projeto projeto = (Projeto) session.get(Projeto.class, id);

//		// Carregando uma pessoa com ID 
//		Pessoa pessoa = (Pessoa) session.load(Pessoa.class, id);

		// Encerrando a sessão
		session.close();
		return projeto;
	}
	public Pessoa getById(Long id) {
		Session session = HibernateUtil.getSessionFactory().openSession();

		// Usando get() para ler a pessoa com o ID especificado
		Pessoa pessoa = (Pessoa) session.get(Pessoa.class, id);

//		// Carregando uma pessoa com ID 
//		Pessoa pessoa = (Pessoa) session.load(Pessoa.class, id);

		// Encerrando a sessão
		session.close();
		return pessoa;
	}

	public Pessoa update(Pessoa p) {
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
		Pessoa pessoa = (Pessoa) session.get(Pessoa.class, id);

		// Excluindo a pessoa do banco de dados
		session.delete(pessoa);

		tx.commit();
		session.close();

	}

	public List<Pessoa> findAll() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Query query = session.createQuery("FROM Pessoa");
		List<Pessoa> pessoas = query.list();
		session.close();
		return pessoas;
	}

	public List<Pessoa> findPorIdadeMinima(int idade) {
		int idadeMinima = idade;
		Session session = HibernateUtil.getSessionFactory().openSession();
		Query query = session.createQuery("FROM Pessoa WHERE idade > :idade");
		query.setParameter("idade", idadeMinima);
		List<Pessoa> pessoas = query.list();
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

	public List<Pessoa> listByNomeSQL(String nome) {
		String sql = "SELECT * FROM pessoa WHERE nome = :nome";
		Session session = HibernateUtil.getSessionFactory().openSession();
		SQLQuery sqlQuery = session.createSQLQuery(sql);
		sqlQuery.addEntity(Pessoa.class);
		sqlQuery.setParameter("nome", nome);
		List<Pessoa> pessoas = sqlQuery.list();
		session.close();
		return pessoas;

	}
}
