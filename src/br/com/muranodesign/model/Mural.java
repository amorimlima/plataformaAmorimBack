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
@Table(name = "mural")
@XmlRootElement
public class Mural implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 @Basic(optional = false)
	 @Column(name = "Idmural")
	 private int Idmural;

	 @Column(name = "mensagem")
	 private String mensagem;
	 
	 @OneToOne
	 private ProfessorFuncionario professor;

	 @OneToOne
	 private Oficina oficina;
	 
	 @OneToOne
	 private Agrupamento agrupamento;
	 
	 @OneToOne
	 private Grupo grupo;
	 
	 @Column(name = "tutoria")
	 private int tutoria;

	 /*@OneToOne
	 private AlunoVariavel aluno;*/
	 
	 @Basic(optional = false)
	 @Column(name = "data")
	 @Temporal(TemporalType.DATE)
	 private Date data;

	public int getIdmural() {
		return Idmural;
	}

	public void setIdmural(int idmural) {
		Idmural = idmural;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public ProfessorFuncionario getProfessor() {
		return professor;
	}

	public void setProfessor(ProfessorFuncionario professor) {
		this.professor = professor;
	}

	/*public AlunoVariavel getAluno() {
		return aluno;
	}

	public void setAluno(AlunoVariavel aluno) {
		this.aluno = aluno;
	}*/

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}
	
	 public Oficina getOficina() {
		return oficina;
	}

	public void setOficina(Oficina oficina) {
		this.oficina = oficina;
	}

	public Agrupamento getAgrupamento() {
		return agrupamento;
	}

	public void setAgrupamento(Agrupamento agrupamento) {
		this.agrupamento = agrupamento;
	}

	public int getTutoria() {
		return tutoria;
	}

	public void setTutoria(int tutoria) {
		this.tutoria = tutoria;
	}

	public Grupo getGrupo() {
		return grupo;
	}

	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}
	 
}