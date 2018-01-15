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
@Table(name = "conceito")
@XmlRootElement
public class Conceito implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "idConceito")
	private int idConceito;
	
	@Column(name = "conceito")
	private String conceito;

	public int getIdConceito() {
		return idConceito;
	}

	public void setIdConceito(int idConceito) {
		this.idConceito = idConceito;
	}

	public String getConceito() {
		return conceito;
	}

	public void setConceito(String conceito) {
		this.conceito = conceito;
	}	
	
}
