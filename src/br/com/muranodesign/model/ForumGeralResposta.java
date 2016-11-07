package br.com.muranodesign.model;

import java.awt.TextArea;
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

/**
 * 
 * @author Kevyn
 *
 */
@Entity
@Table(name = "forum_geral_resposta")
@XmlRootElement
public class ForumGeralResposta implements Serializable{
	private static final long serialVersionUID = 1L;
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 @Basic(optional = false)
	 @Column(name = "idForumResposta")
	 private int idForumResposta;
	 @Column(name = "questao")
	 private TextArea questao;
	 @Column(name = "resposta")
	 private TextArea resposta;
	 @Column(name = "anexo")
	 private String anexo;
	 @Column(name = "data")
	 private Date data;
	 
	 
	 @JoinColumn(name = "forum_geral_questao", referencedColumnName = "idForumQuestao")
	 @ManyToOne
	 private ForumGeralQuestao forumQuestao;

	 @OneToOne
	 @JoinColumn(name = "idusuario")
	 private Usuario usuario;

	public int getIdForumResposta() {
		return idForumResposta;
	}


	public void setIdForumResposta(int idForumResposta) {
		this.idForumResposta = idForumResposta;
	}


	public TextArea getQuestao() {
		return questao;
	}


	public void setQuestao(TextArea questao) {
		this.questao = questao;
	}


	public TextArea getResposta() {
		return resposta;
	}


	public void setResposta(TextArea resposta) {
		this.resposta = resposta;
	}


	public String getAnexo() {
		return anexo;
	}


	public void setAnexo(String anexo) {
		this.anexo = anexo;
	}


	public Date getData() {
		return data;
	}


	public void setData(Date data) {
		this.data = data;
	}


	public ForumGeralQuestao getForumQuestao() {
		return forumQuestao;
	}


	public void setForumQuestao(ForumGeralQuestao forumQuestao) {
		this.forumQuestao = forumQuestao;
	}


	public Usuario getUsuario() {
		return usuario;
	}


	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}



	 
	 

}
