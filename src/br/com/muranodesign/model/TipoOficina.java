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
@Table(name = "tipo_oficina")
@XmlRootElement
public class TipoOficina implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 @Basic(optional = false)
	 @Column(name = "idtipoOficina")
	 private int idTipoOficina;
	 
	 @Basic
	 @Column (name = "nome")
	 private String nome;
	 
	 @ManyToOne
	 @JoinColumn (name = "cor", referencedColumnName = "Idcor")
	 private Cores cor;
	 
	 @Basic
	 @Column (name = "visibilidade")
	 private int visilbilidade;

	public int getVisilbilidade() {
		return visilbilidade;
	}

	public void setVisilbilidade(int visilbilidade) {
		this.visilbilidade = visilbilidade;
	}

	public int getIdTipoOficina() {
		return idTipoOficina;
	}

	public void setIdTipoOficina(int idTipoOficina) {
		this.idTipoOficina = idTipoOficina;
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
	

}
