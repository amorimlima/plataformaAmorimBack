package br.com.muranodesign.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "compensacao")
@XmlRootElement
public class Compensacao implements Serializable{
	 private static final long serialVersionUID = 1L;
	 
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 @Basic(optional = false)
	 @Column(name = "idCompensacao")
	 private Integer idCompensacao;
	 
	 @Column(name = "descricao")
	 private String descricao;
	 
	 @Column(name = "data")
	 @Temporal(TemporalType.DATE)
	 private Date data;
	 
	 @JoinColumn(name = "tutoria", referencedColumnName = "idtutoria")
	 @ManyToOne
	 private Tutoria tutoria;

	public Integer getIdCompensacao() {
		return idCompensacao;
	}

	public void setIdCompensacao(Integer idCompensacao) {
		this.idCompensacao = idCompensacao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Tutoria getTutoria() {
		return tutoria;
	}

	public void setTutoria(Tutoria tutoria) {
		this.tutoria = tutoria;
	}
	 
	 
	 
}
