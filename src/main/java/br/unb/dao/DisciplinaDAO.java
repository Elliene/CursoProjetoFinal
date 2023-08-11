package br.unb.dao;

import java.util.List;


import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import br.unb.dominio.Disciplina;

public class DisciplinaDAO { //utilizada para instanciar as classes para atender as regras de negocio

	public Disciplina salvar(Disciplina disciplina) {
		// Configuração da sessão do Hibernate (SessionFactory)
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();

		// Iniciando a transação
		Transaction tx = session.beginTransaction();

		// Salvando a disciplina no banco de dados
		session.save(disciplina);

		// Comitando a transação
		tx.commit();

		// Fechando a sessão
		session.close();
		return disciplina;

	}

	public Disciplina update(Disciplina d) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();

		// Atualizando a pessoa no banco de dados
		session.update(d);

		tx.commit();
		session.close();

		return d;

	}

	public void delete(int id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();

		// Lendo uma disciplina existente pelo ID
		Disciplina disciplina = (Disciplina) session.get(Disciplina.class, id);

		// Excluindo a pessoa do banco de dados
		session.delete(disciplina);

		tx.commit();
		session.close();

	}

	public Disciplina getById(int id) {
		Session session = HibernateUtil.getSessionFactory().openSession();

		// Usando get() para ler a disciplina com o ID especificado
		Disciplina disciplina = (Disciplina) session.get(Disciplina.class, id);

		// Encerrando a sessão
		session.close();
		return disciplina;
	}

	@SuppressWarnings("unchecked")
	public List<Disciplina> findAll() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Query query = session.createQuery("FROM Disciplina");
		List<Disciplina> disciplinas = query.list();
		session.close();
		return disciplinas;
	}

}

