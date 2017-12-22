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
@Table(name = "aprovacao")
@XmlRootElement
public class Aprovacao implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "idAprovacao")
	private Integer idAprovacao;
	
	@Column(name = "estado")
	private String estado;

	public Integer getIdAprovacao() {
		return idAprovacao;
	}

	public void setIdAprovacao(Integer idAprovacao) {
		this.idAprovacao = idAprovacao;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

}
