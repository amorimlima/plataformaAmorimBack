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
import javax.xml.bind.annotation.XmlRootElement;


@Entity
@Table(name = "historico_eventos")
@XmlRootElement
public class historicoEventos implements Serializable{
	
	private static final long serialVersionUID = 1L;
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 @Basic(optional = false)
	 @Column(name = "idHistEventos")
	 private int idHistEventos;
	 
	 @Column(name = "ano")
	 private int ano;
	 
	 @Column(name = "data_inicio")
	 private Date data_inicio;
	 
	 @Column(name = "data_fim")
	 private Date data_fim;
	 
	 @Column(name = "evento")
	 private String evento;
	 
	 @Column(name = "descricao")
	 private String descricao;
	 
	 @Column(name = "feriado")
	 private String feriado;
	 
	 @Column(name = "aula")
	 private String aula;
	 
	 @JoinColumn(name = "calendario", referencedColumnName = "idcalendario")
	 @ManyToOne
	 private Calendario calendario;
	 
	 @JoinColumn(name = "tipo_evento", referencedColumnName = "idtipo_evento")
	 @ManyToOne
	 private TipoEvento tipoEvento;
	 
	 
	 private Template template;
	 @OneToOne
	 
	 
	 private CalendarioEventos eventos;
	 @OneToOne
	 
	 

	public int getIdHistEventos() {
		return idHistEventos;
	}

	public void setIdHistEventos(int idHistEventos) {
		this.idHistEventos = idHistEventos;
	}

	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}

	public Date getData_inicio() {
		return data_inicio;
	}

	public void setData_inicio(Date data_inicio) {
		this.data_inicio = data_inicio;
	}

	public Date getData_fim() {
		return data_fim;
	}

	public void setData_fim(Date data_fim) {
		this.data_fim = data_fim;
	}

	public String getEvento() {
		return evento;
	}

	public void setEvento(String evento) {
		this.evento = evento;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getFeriado() {
		return feriado;
	}

	public void setFeriado(String feriado) {
		this.feriado = feriado;
	}

	public String getAula() {
		return aula;
	}

	public void setAula(String aula) {
		this.aula = aula;
	}

	public Calendario getCalendario() {
		return calendario;
	}

	public void setCalendario(Calendario calendario) {
		this.calendario = calendario;
	}

	public TipoEvento getTipoEvento() {
		return tipoEvento;
	}

	public void setTipoEvento(TipoEvento tipoEvento) {
		this.tipoEvento = tipoEvento;
	}


	public Template getTemplate() {
		return template;
	}

	public void setTemplate(Template template) {
		this.template = template;
	}

	public CalendarioEventos getEventos() {
		return eventos;
	}

	public void setEventos(CalendarioEventos eventos) {
		this.eventos = eventos;
	}




	 
	 	

}
