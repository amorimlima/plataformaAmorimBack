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
@Table(name = "ciclos")
@XmlRootElement
public class Ciclos implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 @Basic(optional = false)
	 @Column(name = "Idciclos")
	 private int Idciclos;
	 
	
	 @Column(name = "ciclo")
	 private String ciclo;
	 
	 @Column(name = "sigla")
	 private String sigla;
	 


	public int getIdciclos() {
		return Idciclos;
	}


	public void setIdciclos(int idciclos) {
		Idciclos = idciclos;
	}


	public String getCiclo() {
		return ciclo;
	}


	public void setCiclo(String ciclo) {
		this.ciclo = ciclo;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	public String getSigla() {
		return sigla;
	}


	public void setSigla(String sigla) {
		this.sigla = sigla;
	}
	 
	

}
