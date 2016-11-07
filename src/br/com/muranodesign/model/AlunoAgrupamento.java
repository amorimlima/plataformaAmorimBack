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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;


@Entity
@Table(name = "aluno_agrupamento")
@XmlRootElement
public class AlunoAgrupamento implements Serializable{
		
	private static final long serialVersionUID = 1L;
	
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 @Basic(optional = false)
	 @Column(name = "Idaluno_agrupamento")
	 private int Idaluno_agrupamento;
	 
	 
	 
	 @JoinColumn(name = "aluno", referencedColumnName = "idaluno_variavel")
	 @ManyToOne
	 private AlunoVariavel aluno;
	 
	 @OneToOne
	 private Agrupamento agrupamento;
	 
	 
	 

	public int getIdaluno_agrupamento() {
		return Idaluno_agrupamento;
	}

	public void setIdaluno_agrupamento(int idaluno_agrupamento) {
		Idaluno_agrupamento = idaluno_agrupamento;
	}

	public Agrupamento getAgrupamento() {
		return agrupamento;
	}

	public void setAgrupamento(Agrupamento agrupamento) {
		this.agrupamento = agrupamento;
	}

	public AlunoVariavel getAluno() {
		return aluno;
	}

	public void setAluno(AlunoVariavel aluno) {
		this.aluno = aluno;
	}




	 

}
