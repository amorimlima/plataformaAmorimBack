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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**	
 * 
 * @author Kevyn
 *
 */

@Entity
@Table(name = "historico_conexao")
@XmlRootElement
public class Historico_conexao implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 @Basic(optional = false)
	 @Column(name = "idConexao")
	 
	 private int idConexao;
	 @Column(name = "data")
	
	 private Date data;
	 @Column(name = "cnx_escola")
	 private String cnx_escola;
	 @Column(name = "cnx_externo")
	 private String cnx_externo;
	 
	 @OneToOne
	 @JoinColumn(name = "idSo")
	 private SO s_o;
	 @OneToOne
	 @JoinColumn(name = "id")
	 private Usuario usuario;
	 
	 
	 
	public int getIdConexao() {
		return idConexao;
	}
	public void setIdConexao(int idConexao) {
		this.idConexao = idConexao;
	}
	public SO getS_o() {
		return s_o;
	}
	public void setS_o(SO s_o) {
		this.s_o = s_o;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public String getCnx_escola() {
		return cnx_escola;
	}
	public void setCnx_escola(String cnx_escola) {
		this.cnx_escola = cnx_escola;
	}
	public String getCnx_externo() {
		return cnx_externo;
	}
	public void setCnx_externo(String cnx_externo) {
		this.cnx_externo = cnx_externo;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}

}