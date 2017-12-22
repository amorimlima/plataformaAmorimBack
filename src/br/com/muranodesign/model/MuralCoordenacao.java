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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "mural_coordenacao")
@XmlRootElement
public class MuralCoordenacao implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 @Basic(optional = false)
	 @Column(name = "Idmural_coordenacao")
	 private int IdMuralCoordenacao;
	
	@Column(name = "mensagem")
	private String mensagem;
	
	@OneToOne
	private ProfessorFuncionario professor;
	
	@ManyToOne
	@JoinColumn(name = "perfil", referencedColumnName = "idperfil")
	private Perfil perfil;
	
	@ManyToOne
	@JoinColumn(name = "periodo", referencedColumnName = "idperiodo")
	private Periodo periodo;
	
	public Perfil getPerfil() {
		return perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}

	public Periodo getPeriodo() {
		return periodo;
	}

	public void setPeriodo(Periodo periodo) {
		this.periodo = periodo;
	}

	@Basic(optional = false)
	@Column(name = "data")
	@Temporal(TemporalType.DATE)
	private Date data;
	
	@Basic(optional = false)
    @Column(name = "hora")
    private String hora;

	public int getIdMuralCoordenacao() {
		return IdMuralCoordenacao;
	}

	public void setIdMuralCoordenacao(int idMuralCoordenacao) {
		IdMuralCoordenacao = idMuralCoordenacao;
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

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}
	
}
