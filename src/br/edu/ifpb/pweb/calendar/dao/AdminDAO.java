package br.edu.ifpb.pweb.calendar.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.hibernate.HibernateException;
import org.jboss.logging.Logger;

import br.edu.ifpb.pweb.calendar.model.Admin;


public class AdminDAO extends GenericDAOJPAImpl<Admin, Long>{
	private static Logger logger = Logger.getLogger(AdminDAO.class);
	
	public AdminDAO() {
		this(PersistenceUtil.getCurrentEntityManager());
	}

	public AdminDAO(EntityManager em) {
		super(em);
	}
	
	public List<Admin> findAll() throws DAOException{
		List<Admin> Admins = null;
		try {
			Query q = this.getEntityManager()
					.createQuery("from Admin a");
			Admins = (List<Admin>) q.getResultList();
		} catch (HibernateException e) {
			throw new DAOException("Erro ao tentar pegar Admins", e);
		}
		return Admins;
	}
}
