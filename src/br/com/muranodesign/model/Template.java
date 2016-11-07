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
@Table(name = "template")
@XmlRootElement
public class Template implements Serializable{

	private static final long serialVersionUID = 1L;
	
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 @Basic(optional = false)
	 @Column(name = "Idtemplate")
	 private int Idtemplate;
	 
	 @Column(name = "template")
	 private String template;
	 
	 
	public int getIdtemplate() {
		return Idtemplate;
	}
	public void setIdtemplate(int idtemplate) {
		Idtemplate = idtemplate;
	}
	public String getTemplate() {
		return template;
	}
	public void setTemplate(String template) {
		this.template = template;
	}
	
}
