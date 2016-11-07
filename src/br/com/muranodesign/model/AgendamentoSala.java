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
@Table(name = "agendamento_sala")
@XmlRootElement
public class AgendamentoSala implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 @Basic(optional = false)
	 @Column(name = "Idagendamento_sala")
	 private int Idagendamento_sala;
	 
	 @Column(name = "outra")
	 public String outra;
	 
	 @Column(name = "hora")
	 public long hora;
	 
	 @OneToOne
	 private Salas sala;
	 
	 @OneToOne
	 private Rotina rotina;
	 
	 @OneToOne
	 private Semana dia;
	 
	 /*
	 @OneToOne
	 private Atividade atividade;*/

	public int getIdagendamento_sala() {
		return Idagendamento_sala;
	}

	public void setIdagendamento_sala(int idagendamento_sala) {
		Idagendamento_sala = idagendamento_sala;
	}

	public String getOutra() {
		return outra;
	}

	public void setOutra(String outra) {
		this.outra = outra;
	}

	/*
	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}*/

	public Salas getSala() {
		return sala;
	}

	public void setSala(Salas sala) {
		this.sala = sala;
	}

	public Semana getDia() {
		return dia;
	}

	public void setDia(Semana dia) {
		this.dia = dia;
	}

	public Rotina getRotina() {
		return rotina;
	}

	public void setRotina(Rotina rotina) {
		this.rotina = rotina;
	}

	public long getHora() {
		return hora;
	}

	public void setHora(long hora) {
		this.hora = hora;
	}

	/*
	public Atividade getAtividade() {
		return atividade;
	}

	public void setAtividade(Atividade atividade) {
		this.atividade = atividade;
	}
*/
	 

	 

}
