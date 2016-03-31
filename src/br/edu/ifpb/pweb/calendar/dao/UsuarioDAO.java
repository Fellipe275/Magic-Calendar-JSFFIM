package br.edu.ifpb.pweb.calendar.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.hibernate.HibernateException;
import org.jboss.logging.Logger;

import br.edu.ifpb.pweb.calendar.model.Usuario;


public class UsuarioDAO extends GenericDAOJPAImpl<Usuario, Long>{
	private static Logger logger = Logger.getLogger(UsuarioDAO.class);
	
	public UsuarioDAO() {
		this(PersistenceUtil.getCurrentEntityManager());
	}

	public UsuarioDAO(EntityManager em) {
		super(em);
	}
	
	public List<Usuario> readAllUserInactive(){
		try{
			Query q = this.getEntityManager().createQuery("select u from Usuario u where u.status = 'false' ");
			List<Usuario> list = (List<Usuario>) q.getResultList();
			return list;
		}catch(NoResultException e){
			return null;
		}
	}
	
	public List<Usuario> findAll() throws DAOException{
		List<Usuario> Usuarios = null;
		try {
			Query q = this.getEntityManager()
					.createQuery("from Usuario t");
			Usuarios = (List<Usuario>) q.getResultList();
		} catch (HibernateException e) {
			throw new DAOException("Erro ao tentar pegar Usuarios", e);
		}
		return Usuarios;
	}
}
