package br.edu.ifpb.pweb.calendar.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.hibernate.HibernateException;
import org.jboss.logging.Logger;

import br.edu.ifpb.pweb.calendar.model.CalendarMobileHoliday;


public class FeriadoMovelDAO extends GenericDAOJPAImpl<CalendarMobileHoliday, Long>{
	private static Logger logger = Logger.getLogger(FeriadoMovelDAO.class);
	
	public FeriadoMovelDAO() {
		this(PersistenceUtil.getCurrentEntityManager());
	}

	public FeriadoMovelDAO(EntityManager em) {
		super(em);
	}
	
	public CalendarMobileHoliday read(int id){
		try{
			Query q = this.getEntityManager().createQuery("select c from CalendarMobileHoliday c where c.id = '"+id+"' ");
			CalendarMobileHoliday p = (CalendarMobileHoliday) q.getSingleResult();
			return p;
		}catch(NoResultException e){
			return null;
		}
	}
	
	public List<CalendarMobileHoliday> findAll() throws DAOException{
		List<CalendarMobileHoliday> CalendarMobileHolidays = null;
		try {
			Query q = this.getEntityManager()
					.createQuery("from CalendarMobileHoliday t");
			CalendarMobileHolidays = (List<CalendarMobileHoliday>) q.getResultList();
		} catch (HibernateException e) {
			throw new DAOException("Erro ao tentar pegar CalendarMobileHolidays", e);
		}
		return CalendarMobileHolidays;
	}
	
	public List<CalendarMobileHoliday> findPorData(int mes, int ano) throws DAOException{
		String data = "%/"+mes+"/"+ano;
		List<CalendarMobileHoliday> CalendarMobileHolidays = null;
		try {
			Query q = this.getEntityManager()
					.createQuery("select c from CalendarMobileHoliday c LIKE '"+data+"' ");
			CalendarMobileHolidays = (List<CalendarMobileHoliday>) q.getResultList();
		} catch (HibernateException e) {
			throw new DAOException("Erro ao tentar pegar CalendarMobileHolidays", e);
		}
		return CalendarMobileHolidays;
	}
}
