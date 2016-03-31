package br.edu.ifpb.pweb.calendar.beans;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import br.edu.ifpb.pweb.calendar.dao.FeriadoFixoDAO;
import br.edu.ifpb.pweb.calendar.dao.FeriadoMovelDAO;
import br.edu.ifpb.pweb.calendar.dao.PersistenceUtil;
import br.edu.ifpb.pweb.calendar.model.CalendarComment;
import br.edu.ifpb.pweb.calendar.model.CalendarFixedHoliday;
import br.edu.ifpb.pweb.calendar.model.CalendarMobileHoliday;
import br.edu.ifpb.pweb.calendar.model.DiasCalendar;
import br.edu.ifpb.pweb.calendar.model.Usuario;
import br.edu.ifpb.pweb.calendar.util.Color;

@ManagedBean
@ViewScoped
public class MagicCalendarBean {
	private List<DiasCalendar> diasCalendar;
	private Date dataAtual;
	private String dataAtualConcatenada;
	@ManagedProperty(value="#{pessoaBean}")
	private PessoaBean pessoaBean;

	public MagicCalendarBean(){
		this.diasCalendar = new ArrayList<DiasCalendar>();
		this.dataAtual = new Date();
	}
	
	public void gerarCalendar(){
		this.incrementaDataAtual(0);
		
		for(int i=1; i<=quantidadeDiaMes(this.dataAtual); i++){
			DiasCalendar data = new DiasCalendar();
			data.setId(i);
			
			// pintando o dia atual
			Date tmpDate = new Date();
			if(data.getId() == tmpDate.getDate() && this.dataAtual.getMonth() == tmpDate.getMonth() && this.dataAtual.getYear() == tmpDate.getYear())
				data.setColor(Color.PINK);
			
			
			// gerando os feriados fixo
			List<CalendarFixedHoliday> tmpCf = new FeriadoFixoDAO(PersistenceUtil.getCurrentEntityManager()).findAll();
			if(tmpCf.size() > 0){
				for (CalendarFixedHoliday cf : tmpCf) {	
					if(cf.getSubstituto() != null && cf.getSubstituto().getColor().equals(Color.BLUE) && cf.getSubstituto().getStartDate().getYear() == this.dataAtual.getYear()){
						if(cf.getSubstituto().getStartDate().getDate() == data.getId() && cf.getSubstituto().getStartDate().getMonth() == this.dataAtual.getMonth()){						
							data.setFeriado(cf.getSubstituto());
						}
					}else if(cf.getColor().equals(Color.RED) && cf.getStartDate().getDate() == data.getId() && cf.getStartDate().getMonth() == this.dataAtual.getMonth()){
						data.setFeriado(cf);
					}		
				}
			}
			
			// gerando os feriados moveis
			List<CalendarMobileHoliday> tmpCmob = new FeriadoMovelDAO(PersistenceUtil.getCurrentEntityManager()).findAll();
			Date tmpD = new Date();
			tmpD.setTime(this.dataAtual.getTime());
			tmpD.setDate(data.getId());
			if(tmpCmob.size() > 0){
				for (CalendarMobileHoliday cm : tmpCmob) {	
					if(tmpD.after(cm.getStartDate()) && tmpD.before(cm.getEndDate())){
						data.setFeriado(cm);				
					}		
				}
			}
			
			// gerando os comentarios do usuario online
			if(this.pessoaBean.getLogado() != null){
				
				if(this.pessoaBean.getTipoUsuario() == this.pessoaBean.getUSUARIO()){
					List<CalendarComment> tmpCm = ((Usuario)this.pessoaBean.getLogado()).getListCommentMonth(this.dataAtual);
					if(tmpCm.size() > 0){
						for (CalendarComment cm : tmpCm) {
							if(cm.getStartDate().getDate() == data.getId() && cm.getStartDate().getYear() == this.dataAtual.getYear())
								data.addComentario(cm);
						}
					}
				}
			}
			
			this.diasCalendar.add(data);
		}
	}
	
	public int quantidadeDiaMes(Date data){
		Calendar c = Calendar.getInstance();
		c.setTime(data);
		return c.getActualMaximum(Calendar.DAY_OF_MONTH);
	}
	
	public void incrementaDataAtual(int i){
		Calendar c = Calendar.getInstance();
		this.dataAtual.setMonth(dataAtual.getMonth()+i);
		c.setTime(this.dataAtual);
		this.dataAtualConcatenada = mesAno(c.get(Calendar.MONTH))+"/"+c.get(Calendar.YEAR);
		this.diasCalendar = new ArrayList<DiasCalendar>();
	}
	
	public String diaSemana(int dia){
		Calendar c = Calendar.getInstance();
		Date d = new Date();
		d.setDate(dia);
		d.setMonth(this.dataAtual.getMonth());
		d.setYear(this.dataAtual.getYear());
		c.setTime(d);
		dia = c.get(Calendar.DAY_OF_WEEK);
		String[] diaSemana = new String[]{"","Domingo","Segunda","Terça","Quarta","Quinta","Sexta","Sábado"}; 
		return diaSemana[dia];
	}
	
	public  String mesAno(int mes){
		String[] mesAno = new String[]{"Janeiro","Fevereiro","Março","Abril","Maio","Junho","Julho","Agosto"
									   ,"Setembro","Outubro","Novembro","Dezembro"}; 
		return mesAno[mes];
	}
	
	public List<DiasCalendar> getDiasCalendar() {
		return diasCalendar;
	}

	public void setDiasCalendar(List<DiasCalendar> diasCalendar) {
		this.diasCalendar = diasCalendar;
	}

	public String getDataAtualConcatenada() {
		return dataAtualConcatenada;
	}

	public void setDataAtualConcatenada(String dataAtualConcatenada) {
		this.dataAtualConcatenada = dataAtualConcatenada;
	}

	public PessoaBean getPessoaBean() {
		return pessoaBean;
	}

	public void setPessoaBean(PessoaBean pessoaBean) {
		this.pessoaBean = pessoaBean;
	}

	public Date getDataAtual() {
		return dataAtual;
	}

	public void setDataAtual(Date dataAtual) {
		this.dataAtual = dataAtual;
	}

}
