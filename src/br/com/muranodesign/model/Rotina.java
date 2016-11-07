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
@Table(name = "rotina")
@XmlRootElement
public class Rotina implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 @Basic(optional = false)
	 @Column(name = "Idrotina")
	 private int Idrotina;
	 
	 @Column(name = "hora")
	 private long hora;
	 
	 @OneToOne
	 private Oficina oficina;
	
	 @OneToOne
	 private Agrupamento agrupamento;
	
	 @OneToOne
	 private Semana dia;
	 
	 @OneToOne
	 private AnoLetivo anoLetivo;
	 /*
	 @OneToOne
	 private Salas sala;*/

	public int getIdrotina() {
		return Idrotina;
	}

	public void setIdrotina(int idrotina) {
		Idrotina = idrotina;
	}

	public Oficina getOficina() {
		return oficina;
	}

	public void setOficina(Oficina oficina) {
		this.oficina = oficina;
	}

	public Agrupamento getAgrupamento() {
		return agrupamento;
	}

	public void setAgrupamento(Agrupamento agrupamento) {
		this.agrupamento = agrupamento;
	}

	public Semana getDia() {
		return dia;
	}

	public void setDia(Semana dia) {
		this.dia = dia;
	}
/*
	public Salas getSala() {
		return sala;
	}

	public void setSala(Salas sala) {
		this.sala = sala;
	}*/

	public long getHora() {
		return hora;
	}

	public void setHora(long hora) {
		this.hora = hora;
	}

	public AnoLetivo getAnoLetivo() {
		return anoLetivo;
	}

	public void setAnoLetivo(AnoLetivo anoLetivo) {
		this.anoLetivo = anoLetivo;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}




	 

	 

}
