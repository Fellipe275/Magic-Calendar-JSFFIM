package br.edu.ifpb.pweb.calendar.beans;

import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.edu.ifpb.pweb.calendar.dao.PersistenceUtil;
import br.edu.ifpb.pweb.calendar.dao.PessoaDAO;
import br.edu.ifpb.pweb.calendar.model.Calendar;
import br.edu.ifpb.pweb.calendar.model.DiasCalendar;
import br.edu.ifpb.pweb.calendar.model.Pessoa;
import br.edu.ifpb.pweb.calendar.model.Usuario;


@ManagedBean
@SessionScoped
public class PessoaBean extends GenericBean {
	private final int ADMIN = 0;
	private final int USUARIO = 1;
	
	private String nome;
	private String senha;
	private Pessoa logado;
	private int tipoUsuario;
	
	private Date dataAtual;
	private DiasCalendar diaSelecionado;
	private Calendar calendarSelecionado;
	
	
	public String logar(){
		PessoaDAO pDAO = new PessoaDAO(PersistenceUtil.getCurrentEntityManager());
		Pessoa p = pDAO.readUser(nome, senha);

		if(p instanceof Usuario && !((Usuario)p).isStatus()){
			this.addInfoMessage("Aguarde a aprovação do ADM!");
			return null;
		}
		else
		if(p == null){
			this.addInfoMessage("Usuario Inexistente!");
			return null;
		}else{			
			this.logado = p;
			this.tipoUsuario = verificaTipoPessoa(p);
			return "index.xhtml?faces-redirect=true";
		}
	}
	
	public int verificaTipoPessoa(Pessoa p){
		if(p instanceof Usuario)
			return this.USUARIO;
		else
			return this.ADMIN;
	}
	
	public String desLogar(){
		this.setLogado(null);
		return "index.xhtml?faces-redirect=true";
	}
	
	public String deleteConta(){
		PessoaDAO pDAO = new PessoaDAO(PersistenceUtil.getCurrentEntityManager());
		pDAO.beginTransaction();
		pDAO.delete(this.logado);
		pDAO.commit();
		this.setLogado(null);
		return "index.xhtml?faces-redirect=true";
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public Pessoa getLogado() {
		return logado;
	}
	public void setLogado(Pessoa logado) {
		this.logado = logado;
	}

	public int getADMIN() {
		return ADMIN;
	}

	public int getUSUARIO() {
		return USUARIO;
	}

	public void setTipoUsuario(int tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}

	public int getTipoUsuario() {
		return tipoUsuario;
	}

	public Date getDataAtual() {
		return dataAtual;
	}

	public void setDataAtual(Date dataAtual) {
		this.dataAtual = dataAtual;
	}

	public DiasCalendar getDiaSelecionado() {
		return diaSelecionado;
	}

	public void setDiaSelecionado(DiasCalendar diaSelecionado) {
		this.diaSelecionado = diaSelecionado;
	}

	public Calendar getCalendarSelecionado() {
		return calendarSelecionado;
	}

	public void setCalendarSelecionado(Calendar calendarSelecionado) {
		this.calendarSelecionado = calendarSelecionado;
	}
	
}
