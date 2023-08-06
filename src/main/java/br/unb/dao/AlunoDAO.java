package br.unb.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import br.unb.dominio.Aluno;

public class AlunoDAO {

	public Aluno salvar(Aluno aluno) {
		// Configuração da sessão do Hibernate (SessionFactory)
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();

		// Iniciando a transação
		Transaction tx = session.beginTransaction();

		// Salvando a aluno no banco de dados
		session.save(aluno);

		// Comitando a transação
		tx.commit();

		// Fechando a sessão
		session.close();
		return aluno;

	}

	public Aluno update(Aluno a) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();

		// Atualizando a pessoa no banco de dados
		session.update(a);

		tx.commit();
		session.close();

		return a;

	}

	public void delete(int id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();

		// Lendo uma aluno existente pelo ID
		Aluno aluno = (Aluno) session.get(Aluno.class, id);

		// Excluindo a pessoa do banco de dados
		session.delete(aluno);

		tx.commit();
		session.close();

	}

	@SuppressWarnings("unchecked")
	public List<Aluno> findAll() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Query query = session.createQuery("FROM Aluno");
		List<Aluno> alunos = query.list();
		session.close();
		return alunos;
	}

	public Aluno getById(int id) {
		Session session = HibernateUtil.getSessionFactory().openSession();

		// Usando get() para ler a aluno com o ID especificado
		Aluno aluno = (Aluno) session.get(Aluno.class, id);

		// Encerrando a sessão
		session.close();
		return aluno;
	}

}