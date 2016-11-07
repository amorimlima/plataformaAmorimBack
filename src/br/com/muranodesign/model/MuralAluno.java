package br.com.muranodesign.model;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;


@Entity
@Table(name = "mural_aluno")
@XmlRootElement
public class MuralAluno implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "Idmural_aluno")
	private int IdMuralAluno;
	
	@OneToOne
	private AlunoVariavel alunoVariavel;
	
	@OneToOne
	private Mural mural;

	public int getIdMuralAluno() {
		return IdMuralAluno;
	}

	public void setIdMuralAluno(int idMuralAluno) {
		IdMuralAluno = idMuralAluno;
	}

	public AlunoVariavel getAlunoVariavel() {
		return alunoVariavel;
	}

	public void setAlunoVariavel(AlunoVariavel alunoVariavel) {
		this.alunoVariavel = alunoVariavel;
	}

	public Mural getMural() {
		return mural;
	}

	public void setMural(Mural mural) {
		this.mural = mural;
	}
}
