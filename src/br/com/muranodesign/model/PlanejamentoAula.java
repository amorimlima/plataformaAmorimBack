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
@Table(name = "planejamento_aula")
@XmlRootElement
public class PlanejamentoAula  implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 @Basic(optional = false)
	 @Column(name = "Idplanejamento_aula")
	 private Integer Idplanejamento_aula;
	 
	 @JoinColumn (name = "oficina", referencedColumnName = "Idoficina")
	 @ManyToOne
	 private Oficina oficina;
	 
	 @Column(name = "data_inicio")
	 @Temporal(TemporalType.DATE)
	 private Date data_inicio;
	 
	 @Column(name = "data_fim")
	 @Temporal(TemporalType.DATE)
	 private Date data_fim;
	 
	 @Column(name = "objetivo")
	 private String objetivo;
	 
	 @Column(name = "conteudo")
	 private String conteudo;
	 
	 @Column(name = "metodologia")
	 private String metodologia;
	 
	 @Column(name = "atividades")
	 private String atividades;
	 
	 @JoinColumn(name = "professor", referencedColumnName = "idprofessor_funcionario")
	 @ManyToOne
	 private ProfessorFuncionario professor;
	 

	public ProfessorFuncionario getProfessor() {
		return professor;
	}

	public void setProfessor(ProfessorFuncionario professor) {
		this.professor = professor;
	}

	public int getIdplanejamento_aula() {
		return Idplanejamento_aula;
	}

	public void setIdplanejamento_aula(int idplanejamento_aula) {
		Idplanejamento_aula = idplanejamento_aula;
	}

	public Oficina getOficina() {
		return oficina;
	}

	public void setOficina(Oficina oficina) {
		this.oficina = oficina;
	}

	public Date getData_inicio() {
		return data_inicio;
	}

	public void setData_inicio(Date data_ini) {
		this.data_inicio = data_ini;
	}

	public Date getData_fim() {
		return data_fim;
	}

	public void setData_fim(Date data_fim) {
		this.data_fim = data_fim;
	}

	public String getObjetivo() {
		return objetivo;
	}

	public void setObjetivo(String objetivo) {
		this.objetivo = objetivo;
	}

	public String getConteudo() {
		return conteudo;
	}

	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}

	public String getMetodologia() {
		return metodologia;
	}

	public void setMetodologia(String metodologia) {
		this.metodologia = metodologia;
	}

	public String getAtividades() {
		return atividades;
	}

	public void setAtividades(String atividades) {
		this.atividades = atividades;
	}

}

