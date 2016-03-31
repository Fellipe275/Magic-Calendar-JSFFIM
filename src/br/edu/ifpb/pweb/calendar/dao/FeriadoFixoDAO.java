package br.edu.ifpb.pweb.calendar.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.hibernate.HibernateException;
import org.jboss.logging.Logger;

import br.edu.ifpb.pweb.calendar.model.CalendarFixedHoliday;


public class FeriadoFixoDAO extends GenericDAOJPAImpl<CalendarFixedHoliday, Long>{
	private static Logger logger = Logger.getLogger(FeriadoFixoDAO.class);
	
	public FeriadoFixoDAO() {
		this(PersistenceUtil.getCurrentEntityManager());
	}

	public FeriadoFixoDAO(EntityManager em) {
		super(em);
	}
	
	public CalendarFixedHoliday read(int id){
		try{
			Query q = this.getEntityManager().createQuery("select c from CalendarFixedHoliday c where c.id = '"+id+"' ");
			CalendarFixedHoliday p = (CalendarFixedHoliday) q.getSingleResult();
			return p;
		}catch(NoResultException e){
			return null;
		}
	}
	
	public List<CalendarFixedHoliday> findAll() throws DAOException{
		List<CalendarFixedHoliday> CalendarFixedHolidays = null;
		try {
			Query q = this.getEntityManager()
					.createQuery("from CalendarFixedHoliday t");
			CalendarFixedHolidays = (List<CalendarFixedHoliday>) q.getResultList();
		} catch (HibernateException e) {
			throw new DAOException("Erro ao tentar pegar CalendarFixedHolidays", e);
		}
		return CalendarFixedHolidays;
	}
	
	public List<CalendarFixedHoliday> findPorData(int mes, int ano) throws DAOException{
		String data = "%/"+mes+"/"+ano;
		List<CalendarFixedHoliday> CalendarFixedHolidays = null;
		try {
			Query q = this.getEntityManager()
					.createQuery("select c from CalendarFixedHoliday c LIKE '"+data+"' ");
			CalendarFixedHolidays = (List<CalendarFixedHoliday>) q.getResultList();
		} catch (HibernateException e) {
			throw new DAOException("Erro ao tentar pegar CalendarFixedHolidays", e);
		}
		return CalendarFixedHolidays;
	}
}
