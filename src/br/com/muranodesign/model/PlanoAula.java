package br.com.muranodesign.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "plano_aula")
@XmlRootElement
public class PlanoAula implements Serializable{

	private static final long serialVersionUID = 1L;
	
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 @Basic(optional = false)
	 @Column(name = "Iplano_aula")
	 private int Idplano_aula;
	 
	 @Column(name = "data_ini")
	 @Temporal(TemporalType.DATE)
	 private Date data_ini;
	 
	 @Column(name = "data_fim")
	 @Temporal(TemporalType.DATE)
	 private Date data_fim;
	 
	 @Column(name = "objetivos")
	 private String objetivos;
	 
	 @Column(name = "tarefa_casa")
	 private String tarefa_casa;
	 
	 @Column(name = "registro_atividade")
	 private String registro_atividade;
	 
	 @OneToOne
	 private Blog blog;
	 
	 @OneToOne
	 private ProfessorFuncionario professor;

	public int getIdplano_aula() {
		return Idplano_aula;
	}

	public void setIdplano_aula(int idplano_aula) {
		Idplano_aula = idplano_aula;
	}

	public Date getData_ini() {
		return data_ini;
	}

	public void setData_ini(Date data_ini) {
		this.data_ini = data_ini;
	}

	public Date getData_fim() {
		return data_fim;
	}

	public void setData_fim(Date data_fim) {
		this.data_fim = data_fim;
	}

	public String getObjetivos() {
		return objetivos;
	}

	public void setObjetivos(String objetivos) {
		this.objetivos = objetivos;
	}

	public String getTarefa_casa() {
		return tarefa_casa;
	}

	public void setTarefa_casa(String tarefa_casa) {
		this.tarefa_casa = tarefa_casa;
	}

	public String getRegistro_atividade() {
		return registro_atividade;
	}

	public void setRegistro_atividade(String registro_atividade) {
		this.registro_atividade = registro_atividade;
	}

	public Blog getBlog() {
		return blog;
	}

	public void setBlog(Blog blog) {
		this.blog = blog;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public ProfessorFuncionario getProfessor() {
		return professor;
	}

	public void setProfessor(ProfessorFuncionario professor) {
		this.professor = professor;
	}
	 
	 
	 
}
