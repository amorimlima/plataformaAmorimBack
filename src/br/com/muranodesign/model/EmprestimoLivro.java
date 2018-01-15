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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;


@Entity
@Table(name="emprestimo_livro")
@XmlRootElement
public class EmprestimoLivro implements Serializable{
	
	 private static final long serialVersionUID = 1L;
	 
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 @Basic(optional = false)
	 @Column(name = "idEmprestimoLivro")
	 private Integer idEmprestimoLivro;
	 
	 @Column(name = "livro")
	 private String livro;
	 
	 @Column(name = "retirada")
	 @Temporal(TemporalType.DATE)
	 private Date retirada;

	@Column(name = "retorno")
	 @Temporal(TemporalType.DATE)
	 private Date retorno;
	 
	 @JoinColumn(name = "aluno", referencedColumnName="ID_ALUNO")
	 private Aluno aluno;
	 
	 public Integer getIdEmprestimoLivro() {
			return idEmprestimoLivro;
		}

		public void setIdEmprestimoLivro(Integer idEmprestimoLivro) {
			this.idEmprestimoLivro = idEmprestimoLivro;
		}

		public String getLivro() {
			return livro;
		}

		public void setLivro(String livro) {
			this.livro = livro;
		}

		public Date getRetirada() {
			return retirada;
		}

		public void setRetirada(Date retirada) {
			this.retirada = retirada;
		}

		public Date getRetorno() {
			return retorno;
		}

		public void setRetorno(Date retorno) {
			this.retorno = retorno;
		}

		public Aluno getAluno() {
			return aluno;
		}

		public void setAluno(Aluno aluno) {
			this.aluno = aluno;
		}
	 

}
