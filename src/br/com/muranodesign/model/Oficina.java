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
@Table(name = "oficina")
@XmlRootElement
public class Oficina implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 @Basic(optional = false)
	 @Column(name = "Idoficina")
	 private int Idoficina;
	 
	 @Column(name = "nome")
	 private String nome;
	 
	 @OneToOne
	 private Cores cor;
	 
	 @OneToOne
	 private AnoLetivo anoLetivo;
	 
	 @OneToOne
	 private Ciclos ciclo;
	 
	 @OneToOne
	 private Periodo periodo;
	 

	 public int getIdoficina() {
		return Idoficina;
	 }

	 public void setIdoficina(int idoficina) {
		Idoficina = idoficina;
	 }

	 public String getNome() {
		return nome;
	 }

	 public void setNome(String nome) {
		this.nome = nome;
	 }

	public Cores getCor() {
		return cor;
	}

	public void setCor(Cores cor) {
		this.cor = cor;
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


	public Periodo getPeriodo() {
		return periodo;
	}

	public void setPeriodo(Periodo periodo) {
		this.periodo = periodo;
	}

	public Ciclos getCiclo() {
		return ciclo;
	}

	public void setCiclo(Ciclos ciclo) {
		this.ciclo = ciclo;
	}
	 
}
