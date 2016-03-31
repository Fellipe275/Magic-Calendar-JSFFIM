package br.edu.ifpb.pweb.calendar.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.hibernate.HibernateException;
import org.jboss.logging.Logger;

import br.edu.ifpb.pweb.calendar.model.CalendarComment;


public class ComentarioDAO extends GenericDAOJPAImpl<CalendarComment, Long>{
	private static Logger logger = Logger.getLogger(ComentarioDAO.class);
	
	public ComentarioDAO() {
		this(PersistenceUtil.getCurrentEntityManager());
	}

	public ComentarioDAO(EntityManager em) {
		super(em);
	}
	
	public CalendarComment readUser(int id){
		try{
			Query q = this.getEntityManager().createQuery("select c from CalendarComment c where c.id = '"+id+"' ");
			CalendarComment p = (CalendarComment) q.getSingleResult();
			return p;
		}catch(NoResultException e){
			return null;
		}
	}
	
	public List<CalendarComment> findAll() throws DAOException{
		List<CalendarComment> CalendarComments = null;
		try {
			Query q = this.getEntityManager()
					.createQuery("from CalendarComment t");
			CalendarComments = (List<CalendarComment>) q.getResultList();
		} catch (HibernateException e) {
			throw new DAOException("Erro ao tentar pegar CalendarComments", e);
		}
		return CalendarComments;
	}
}
