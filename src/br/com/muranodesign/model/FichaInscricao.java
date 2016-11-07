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
@Table(name = "ficha_finalizacao")
@XmlRootElement
public class FichaInscricao implements Serializable{
	 private static final long serialVersionUID = 1L;
	 
	 	@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Basic(optional = false)
	    @Column(name = "idficha_inscricao")
	    private Integer idfichainscricao;
	 	@Basic(optional = false)
	    @Column(name = "local")
	    private String local;

	 	
	 	@JoinColumn(name = "ano_letivo", referencedColumnName = "idano_letivo")
	    @ManyToOne
	    private AnoLetivo anoLetivo;
	 	 
	 	@JoinColumn(name = "roteiro", referencedColumnName = "idroteiro")
	    @ManyToOne
	    private Roteiro roteiro;

	 	
		public Integer getIdfichainscricao() {
			return idfichainscricao;
		}

		public void setIdfichainscricao(Integer idfichainscricao) {
			this.idfichainscricao = idfichainscricao;
		}

		public String getLocal() {
			return local;
		}

		public void setLocal(String local) {
			this.local = local;
		}

		public AnoLetivo getAnoLetivo() {
			return anoLetivo;
		}

		public void setAnoLetivo(AnoLetivo anoLetivo) {
			this.anoLetivo = anoLetivo;
		}

		public Roteiro getRoteiro() {
			return roteiro;
		}

		public void setRoteiro(Roteiro roteiro) {
			this.roteiro = roteiro;
		}
	 	
	 	
	

}
