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


/**
 * 
 * @author Kevyn
 *
 */
@Entity
@Table(name = "so_versao")
@XmlRootElement
public class SO implements Serializable{
	private static final long serialVersionUID = 1L;
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 @Basic(optional = false)
	 @Column(name = "id_so")
	 private int idSo;
	 @Column(name = "so")
	 private String so;


	 
	 
	public int getIdSo() {
		return idSo;
	}
	public void setIdSo(int idSo) {
		this.idSo = idSo;
	}
	public String getSo() {
		return so;
	}
	public void setSo(String so) {
		this.so = so;
	}


}
