package br.edu.ifpb.pweb.calendar.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class CalendarFixedHoliday extends Calendar {
	
	@ManyToOne
	private Admin admin;
	@OneToOne
	@JoinColumn(name="substituto")
	private CalendarFixedHoliday substituto;
	
	public CalendarFixedHoliday(){}

	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	public CalendarFixedHoliday getSubstituto() {
		return substituto;
	}

	public void setSubstituto(CalendarFixedHoliday substituto) {
		this.substituto = substituto;
	}

}
