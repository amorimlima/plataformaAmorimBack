package br.com.muranodesign.model;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "menu")
@XmlRootElement
public class Menu implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 @Basic(optional = false)
	 @Column(name = "Idmenu")
	 private int Idmenu;
	 
	 @Column(name = "nome")
	 private String nome;
	 
	 @Column(name = "classe")
	 private String classe;
	 
	 @Column(name = "link")
	 private String link;

	public int getIdmenu() {
		return Idmenu;
	}

	public void setIdmenu(int idmenu) {
		Idmenu = idmenu;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getClasse() {
		return classe;
	}

	public void setClasse(String classe) {
		this.classe = classe;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	 
	 
	 

}
