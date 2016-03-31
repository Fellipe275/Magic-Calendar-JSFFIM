package br.edu.ifpb.pweb.calendar.model;

import java.util.ArrayList;
import java.util.List;

public class DiasCalendar extends Calendar {
	
	private Calendar feriado;
	private List<CalendarComment> comentarios;
	
	public DiasCalendar(){
		this.comentarios = new ArrayList<CalendarComment>();
	}
	
	public void addComentario(CalendarComment comentario) {
		this.comentarios.add(comentario);
	}
	
	public Calendar getFeriado() {
		return feriado;
	}

	public void setFeriado(Calendar feriado) {
		this.feriado = feriado;
	}

	public void setComentarios(List<CalendarComment> comentarios) {
		this.comentarios = comentarios;
	}

	public void updateCalendar(CalendarComment cometario){
		int index = 0;
		for(CalendarComment c : this.comentarios){
			if(c.equals(cometario))
				this.comentarios.set(index, cometario);
			index++;
		}
	}

	public void deleteComentario(CalendarComment comentario){
		this.comentarios.remove(comentario);
	}

	public List<CalendarComment> getComentarios() {
		return comentarios;
	}
	
}
