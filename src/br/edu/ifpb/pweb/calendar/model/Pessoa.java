package br.edu.ifpb.pweb.calendar.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
public abstract class Pessoa {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String name;
	private String password;
	
	
	public Pessoa(String nome, String senha){
		this.name = nome;
		this.password = senha;
	}
	
	public Pessoa(){}
	
	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String nome) {
		this.name = nome;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setSenha(String senha) {
		this.password = senha;
	}
	
}
