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
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;


@Entity
@Table(name = "plano_agrupamento")
@XmlRootElement
public class PlanoAgrupamento implements Serializable {

	private static final long serialVersionUID = 1L;
	
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 @Basic(optional = false)
	 @Column(name = "idPlano_agrupamento")
	 private int idPlano_agrupamento;
	 
	 @ManyToOne
	 @JoinColumn(name = "planejamentoAula", referencedColumnName = "Idplanejamento_aula")
	 private PlanejamentoAula planejamentoAula;

	@ManyToOne
	 @JoinColumn(name = "agrupamento", referencedColumnName = "Idagrupamento")
	 private Agrupamento agrupamento;

	public int getIdPlano_agrupamento() {
		return idPlano_agrupamento;
	}
	
	public PlanejamentoAula getPlanejamentoAula() {
		return planejamentoAula;
	}

	public void setPlanejamentoAula(PlanejamentoAula planejamentoAula) {
		this.planejamentoAula = planejamentoAula;
	}

	public void setIdPlano_agrupamento(int idPlano_agrupamento) {
		this.idPlano_agrupamento = idPlano_agrupamento;
	}

	public Agrupamento getAgrupamento() {
		return agrupamento;
	}

	public void setAgrupamento(Agrupamento agrupamento) {
		this.agrupamento = agrupamento;
	}
	 
}
