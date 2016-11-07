package br.com.muranodesign.model;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;


@Entity
@Table(name = "objetivo_aula")
@XmlRootElement
public class ObjetivoAula implements Serializable{
	private static final long serialVersionUID = 1L;
	
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 @Basic(optional = false)
	 @Column(name = "Idobjetivo_aula")
	 private int Idobjetivo_aula;
	 
	 @Column(name = "objetivo")
	 private String objetivo;

	 @Column(name = "status")
	 private int status;
	 
	 @OneToOne
	 private RoteiroAula roteiro;

	public int getIdobjetivo_aula() {
		return Idobjetivo_aula;
	}

	public void setIdobjetivo_aula(int idobjetivo_aula) {
		Idobjetivo_aula = idobjetivo_aula;
	}

	public String getObjetivo() {
		return objetivo;
	}

	public void setObjetivo(String objetivo) {
		this.objetivo = objetivo;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public RoteiroAula getRoteiro() {
		return roteiro;
	}

	public void setRoteiro(RoteiroAula roteiro) {
		this.roteiro = roteiro;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	 
	 

}
