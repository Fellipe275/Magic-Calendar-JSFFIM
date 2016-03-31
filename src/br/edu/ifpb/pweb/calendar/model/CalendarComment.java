package br.edu.ifpb.pweb.calendar.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class CalendarComment extends Calendar{

	@ManyToOne
	private Usuario usuario;
	
	public CalendarComment(){}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
}
