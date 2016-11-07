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
@Table(name = "cores")
@XmlRootElement
public class Cores implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 @Basic(optional = false)
	 @Column(name = "Idcor")
	 private int Idcor;
	 
	 @Column(name = "nome")
	 private String nome;
	 
	 @Column(name = "Forte")
	 private String Forte;
	 
	 @Column(name = "Medio")
	 private String Medio;
	 
	 @Column(name = "Fraco")
	 private String Fraco;
	 
	 

	public int getIdcor() {
		return Idcor;
	}

	public void setIdcor(int idcor) {
		Idcor = idcor;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getForte() {
		return Forte;
	}

	public void setForte(String forte) {
		Forte = forte;
	}

	public String getMedio() {
		return Medio;
	}

	public void setMedio(String medio) {
		Medio = medio;
	}

	public String getFraco() {
		return Fraco;
	}

	public void setFraco(String fraco) {
		Fraco = fraco;
	}


	 
	 
	

}
