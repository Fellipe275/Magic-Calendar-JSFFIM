package br.edu.ifpb.pweb.calendar.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.hibernate.HibernateException;
import org.jboss.logging.Logger;

import br.edu.ifpb.pweb.calendar.model.Pessoa;


public class PessoaDAO extends GenericDAOJPAImpl<Pessoa, Long>{
	private static Logger logger = Logger.getLogger(PessoaDAO.class);
	
	public PessoaDAO() {
		this(PersistenceUtil.getCurrentEntityManager());
	}

	public PessoaDAO(EntityManager em) {
		super(em);
	}
	
	public Pessoa read(Pessoa p){
		try{
			Query q = this.getEntityManager().createQuery("select p from Pessoa p where p.id ='"+p.getId()+"' ");
			return (Pessoa) q.getSingleResult();
		}catch(NoResultException e){
			return null;
		}
	}
	
	public Pessoa readUser(String name, String pass){
		try{
			Query q = this.getEntityManager().createQuery("select p from Pessoa p where p.name = '"+name+"' AND p.password = '"+pass+"' ");
			Pessoa p = (Pessoa) q.getSingleResult();
			return p;
		}catch(NoResultException e){
			return null;
		}
	}
	
	public List<Pessoa> findAll() throws DAOException{
		List<Pessoa> Pessoas = null;
		try {
			Query q = this.getEntityManager()
					.createQuery("from Pessoa t");
			Pessoas = (List<Pessoa>) q.getResultList();
		} catch (HibernateException e) {
			throw new DAOException("Erro ao tentar pegar Pessoas", e);
		}
		return Pessoas;
	}
}
