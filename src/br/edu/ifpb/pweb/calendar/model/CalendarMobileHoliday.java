package br.edu.ifpb.pweb.calendar.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class CalendarMobileHoliday extends Calendar {
	
	@ManyToOne
	private Admin admin;
	@Temporal(TemporalType.DATE)
	private Date endDate;
	
	public CalendarMobileHoliday(){}

	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

}
