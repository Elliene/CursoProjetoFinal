package br.unb.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import br.unb.dominio.Professor;

public class ProfessorDAO {

	public Professor salvar(Professor professor) {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();

		Transaction tx = session.beginTransaction();
		session.save(professor);

		tx.commit();
		session.close();
		return professor;

	}

	public Professor update(Professor p) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();

		session.update(p);

		tx.commit();
		session.close();

		return p;

	}

	public void delete(int id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();

		// Lendo uma aluno existente pelo ID
		Professor professor = (Professor) session.get(Professor.class, id);

		// Excluindo o aluno do banco de dados
		session.delete(professor);

		tx.commit();
		session.close();

	}

	@SuppressWarnings("unchecked")
	public List<Professor> findAll() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Query query = session.createQuery("FROM Professor");
		List<Professor> professores = query.list();
		session.close();
		return professores;
	}

	public Professor getById(int id) {
		Session session = HibernateUtil.getSessionFactory().openSession();

		Professor professor = (Professor) session.get(Professor.class, id);

		session.close();
		return professor;

	}

}
