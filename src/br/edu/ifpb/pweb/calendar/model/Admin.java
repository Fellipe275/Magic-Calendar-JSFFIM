package br.edu.ifpb.pweb.calendar.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class Admin extends Pessoa {
	@OneToMany(mappedBy="admin", cascade=CascadeType.ALL, orphanRemoval=true)
	private List<CalendarFixedHoliday> listFixedHoliday;
	@OneToMany(mappedBy="admin", cascade=CascadeType.ALL, orphanRemoval=true)
	private List<CalendarMobileHoliday> listMobileHolidays;
	
	public Admin(){
		super();
		this.listFixedHoliday = new ArrayList<CalendarFixedHoliday>();
		this.listMobileHolidays = new ArrayList<CalendarMobileHoliday>();
	}
	
	public void addFeriadoFixo(CalendarFixedHoliday holiday){
		holiday.setAdmin(this);
		this.listFixedHoliday.add(holiday);
	}
	
	public void addFeriadoMovel(CalendarMobileHoliday feriadoMovel){
		feriadoMovel.setAdmin(this);
		this.listMobileHolidays.add(feriadoMovel);
	}
	
	public void delFeriadoFixo(CalendarFixedHoliday holiday){
		this.listFixedHoliday.remove(holiday);
	}
	
	public void delFeriadoMovel(CalendarMobileHoliday feriadoMovel){
		this.listMobileHolidays.remove(feriadoMovel);
	}
	
	public CalendarFixedHoliday getFeriadoFixo(int id){
		for(CalendarFixedHoliday holiday : listFixedHoliday){
			if(holiday.getId() == id){
				return holiday;
			}
		}
		return null;
	}
	
	public void setFeriadoFixo(CalendarFixedHoliday ch){
		int index = 0;
		for(CalendarFixedHoliday holiday : listFixedHoliday){
			if(holiday.getId() == ch.getId()){
				this.listFixedHoliday.set(index, ch);
			}
			index++;
		}
	}
	
	public void setFeriadoMovel(CalendarMobileHoliday cm){
		int index = 0;
		for(CalendarMobileHoliday calendarMobileHoliday : listMobileHolidays){
			if(calendarMobileHoliday.getId() == cm.getId()){
				this.listMobileHolidays.set(index, calendarMobileHoliday);
			}
			index++;
		}
	}

	public List<CalendarFixedHoliday> getListFixedHoliday() {
		return listFixedHoliday;
	}

	public void setListFixedHoliday(List<CalendarFixedHoliday> listFixedHoliday) {
		this.listFixedHoliday = listFixedHoliday;
	}

	public List<CalendarMobileHoliday> getListMobileHolidays() {
		return listMobileHolidays;
	}

	public void setListMobileHolidays(List<CalendarMobileHoliday> listMobileHolidays) {
		this.listMobileHolidays = listMobileHolidays;
	}
	
}
