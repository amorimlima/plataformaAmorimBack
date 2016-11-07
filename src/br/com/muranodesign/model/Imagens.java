package br.com.muranodesign.model;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;


@Entity
@Table(name = "imagens")
@XmlRootElement
public class Imagens  implements Serializable{
	
	private static final long serialVersionUID = 1L;
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 @Basic(optional = false)
	 @Column(name = "Idimagens")
	 private int Idimagens;
	 
	 @Column(name = "imagens")
	 private String imagens;
	 
	 @JoinColumn(name = "historico_eventos", referencedColumnName = "idHistEventos")
	 @ManyToOne
	 private historicoEventos historico;
	 
	 
	public int getIdimagens() {
		return Idimagens;
	}
	public void setIdimagens(int idimagens) {
		Idimagens = idimagens;
	}
	public String getImagens() {
		return imagens;
	}
	public void setImagens(String imagens) {
		this.imagens = imagens;
	}
	public historicoEventos getHistorico() {
		return historico;
	}
	public void setHistorico(historicoEventos historico) {
		this.historico = historico;
	}

	 
	 
	 

}
