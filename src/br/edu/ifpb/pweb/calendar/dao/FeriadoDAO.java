package br.edu.ifpb.pweb.calendar.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.hibernate.HibernateException;
import org.jboss.logging.Logger;

import br.edu.ifpb.pweb.calendar.model.Calendar;


public class FeriadoDAO extends GenericDAOJPAImpl<Calendar, Long>{
	private static Logger logger = Logger.getLogger(FeriadoDAO.class);
	
	public FeriadoDAO() {
		this(PersistenceUtil.getCurrentEntityManager());
	}

	public FeriadoDAO(EntityManager em) {
		super(em);
	}
	
	public Calendar read(int id){
		try{
			Query q = this.getEntityManager().createQuery("select c from Calendar c where c.id = '"+id+"' ");
			Calendar p = (Calendar) q.getSingleResult();
			return p;
		}catch(NoResultException e){
			return null;
		}
	}
	
	public List<Calendar> findAll() throws DAOException{
		List<Calendar> Calendars = null;
		try {
			Query q = this.getEntityManager()
					.createQuery("from Calendar t");
			Calendars = (List<Calendar>) q.getResultList();
		} catch (HibernateException e) {
			throw new DAOException("Erro ao tentar pegar Calendars", e);
		}
		return Calendars;
	}
	
}
