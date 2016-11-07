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
@Table(name = "agrupamento")
@XmlRootElement
public class Agrupamento  implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 @Basic(optional = false)
	 @Column(name = "Idagrupamento")
	 private int Idagrupamento;
	
	 @Column(name = "nome")
	 private String nome;
	 
	 @OneToOne
	 private AnoLetivo anoLetivo;
	 
	 //Alteração que pode mudar
	 @OneToOne
	 private Ciclos ciclo;
	 //Alteração que pode mudar
	 
	public int getIdagrupamento() {
		return Idagrupamento;
	}

	public void setIdagrupamento(int idagrupamento) {
		Idagrupamento = idagrupamento;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
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

	//Alteração que pode mudar
	public Ciclos getCiclo() {
		return ciclo;
	}

	public void setCiclo(Ciclos ciclo) {
		this.ciclo = ciclo;
	}
	//Alteração que pode mudar
	 

}
