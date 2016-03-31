package br.edu.ifpb.pweb.calendar.beans;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import br.edu.ifpb.pweb.calendar.dao.AdminDAO;
import br.edu.ifpb.pweb.calendar.dao.PersistenceUtil;
import br.edu.ifpb.pweb.calendar.dao.PessoaDAO;
import br.edu.ifpb.pweb.calendar.dao.UsuarioDAO;
import br.edu.ifpb.pweb.calendar.model.Admin;
import br.edu.ifpb.pweb.calendar.model.Pessoa;
import br.edu.ifpb.pweb.calendar.model.Usuario;

@ManagedBean
@ViewScoped
public class CadastrarPessoaBean extends GenericBean {
	private final String ADMIN = "admin";
	
	private String nome;
	private String senha;
	private String reSenha;
	
	private List<Usuario> usuarios;
	private boolean mostrarListaUsuario;
	private Map<Integer, Boolean> editavel = new HashMap<Integer, Boolean>();
	
	@ManagedProperty(value="#{pessoaBean}")
	private PessoaBean pessoaBean;
	
	public String cadastrarPessoa(int tipo){
		
		if(!this.senha.equals(this.reSenha)){
			this.addInfoMessage("Senha diferente, digite novamente!");
			return null;
		}else{
			PessoaDAO pDAO = new PessoaDAO(PersistenceUtil.getCurrentEntityManager());
			Pessoa p = null;
			
			if(this.nome.equals(this.ADMIN)){
				tipo=0;
			}
			
			if(tipo == 0){
				p = new Admin();
				p.setName(this.nome);
				p.setSenha(this.senha);
				if(new AdminDAO(PersistenceUtil.getCurrentEntityManager()).findAll().size() > 0){
					System.out.println("entrou aq");
					this.addInfoMessage("No sistema so pode existir apenas um administrador!");
					return "index.xhtml";
				}
			}else if(tipo == 1){
				p = new Usuario(this.nome, this.senha);				
			}
			
			pDAO.beginTransaction();
			pDAO.insert(p);
			pDAO.commit();
			
			this.addInfoMessage("Cadastro realizado com sucesso!");
			return "index.xhtml";
		}
		
	}
	
	public String alterarSenha(){
		if(!this.senha.equals(this.reSenha)){
			this.addInfoMessage("Senha diferente, digite novamente!");
			return null;
		}else{
			this.pessoaBean.getLogado().setSenha(this.senha);
			PessoaDAO pDAO = new PessoaDAO(PersistenceUtil.getCurrentEntityManager());
			pDAO.beginTransaction();
			pDAO.update(this.pessoaBean.getLogado());
			pDAO.commit();

			this.addInfoMessage("Senha alterada com sucesso!");
			return "index.xhtml";
		}
	}
	
	public void ativarUsuarios(){
		PessoaDAO pDAO = new PessoaDAO(PersistenceUtil.getCurrentEntityManager());
		for(Usuario u : this.usuarios){
			if(editavel.get(u.getId())){
				u.setStatus(true);
				pDAO.beginTransaction();
				pDAO.update(u);
				pDAO.commit();
			}
		}
	}
	
	public void listarUsuarios(){
		UsuarioDAO pDAO = new UsuarioDAO(PersistenceUtil.getCurrentEntityManager());
		this.usuarios = pDAO.readAllUserInactive();
		
		if(this.usuarios.size() > 0){
			this.mostrarListaUsuario = true;
			if(this.editavel.size() == 0){
				for(Usuario u : this.usuarios)
					editavel.put(u.getId(), false);
			}
		}else{
			this.mostrarListaUsuario = false;
		}
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
	public String getReSenha() {
		return reSenha;
	}
	public void setReSenha(String reSenha) {
		this.reSenha = reSenha;
	}

	public PessoaBean getPessoaBean() {
		return pessoaBean;
	}

	public void setPessoaBean(PessoaBean pessoaBean) {
		this.pessoaBean = pessoaBean;
	}

	public Map<Integer, Boolean> getEditavel() {
		return editavel;
	}

	public void setEditavel(Map<Integer, Boolean> editavel) {
		this.editavel = editavel;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public boolean isMostrarListaUsuario() {
		return mostrarListaUsuario;
	}

	public void setMostrarListaUsuario(boolean mostrarListaUsuario) {
		this.mostrarListaUsuario = mostrarListaUsuario;
	}
	
}
