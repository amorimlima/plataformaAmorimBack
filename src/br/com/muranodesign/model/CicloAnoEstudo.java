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
@Table(name = "ciclo_anoEstudo")
@XmlRootElement
public class CicloAnoEstudo implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 @Basic(optional = false)
	 @Column(name = "Idciclo_anoEstudo")
	 private int Idciclo_anoEstudo;
	 
	 @OneToOne
	 private Ciclos ciclo;
	 
	 @OneToOne
	 private AnoEstudo ano;

	public int getIdciclo_anoEstudo() {
		return Idciclo_anoEstudo;
	}

	public void setIdciclo_anoEstudo(int idciclo_anoEstudo) {
		Idciclo_anoEstudo = idciclo_anoEstudo;
	}

	public Ciclos getCiclo() {
		return ciclo;
	}

	public void setCiclo(Ciclos ciclo) {
		this.ciclo = ciclo;
	}

	public AnoEstudo getAno() {
		return ano;
	}

	public void setAno(AnoEstudo ano) {
		this.ano = ano;
	}
	
	 

}
