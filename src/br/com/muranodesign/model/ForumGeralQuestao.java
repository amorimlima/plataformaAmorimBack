package br.com.muranodesign.model;

import java.awt.TextArea;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author Kevyn
 *
 */
@Entity
@Table(name = "forum_geral_questao")
@XmlRootElement
public class ForumGeralQuestao implements Serializable{
	private static final long serialVersionUID = 1L;
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 @Basic(optional = false)
	 @Column(name = "idForumQuestao")
	 private int idForumQuestao;
	 @Column(name = "questao")
	 private TextArea questao;
	 @Column(name = "assunto")
	 private String assunto;
	 @Column(name = "anexo")
	 private String anexo;
	 @Column(name = "data")
	 private Date data;
	 
	 @JoinColumn(name = "sessao_forum", referencedColumnName = "idSessaoForum")
	 @ManyToOne
	 private SessaoForumGeral sessaoForum;
	 
	 @OneToMany(cascade = CascadeType.ALL, mappedBy = "forumQuestao")
	 private Collection<ForumGeralResposta> forumRespostas;
	 
	 @OneToOne
	 @JoinColumn(name = "idusuario")
	 private Usuario usuario;
	 

	public int getIdForumQuestao() {
		return idForumQuestao;
	}

	public void setIdForumQuestao(int idForumQuestao) {
		this.idForumQuestao = idForumQuestao;
	}

	public TextArea getQuestao() {
		return questao;
	}

	public void setQuestao(TextArea questao) {
		this.questao = questao;
	}

	public String getAssunto() {
		return assunto;
	}

	public void setAssunto(String assunto) {
		this.assunto = assunto;
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

	public SessaoForumGeral getSessaoForum() {
		return sessaoForum;
	}

	public void setSessaoForum(SessaoForumGeral sessaoForum) {
		this.sessaoForum = sessaoForum;
	}

	public Collection<ForumGeralResposta> getForumRespostas() {
		return forumRespostas;
	}

	public void setForumRespostas(Collection<ForumGeralResposta> forumRespostas) {
		this.forumRespostas = forumRespostas;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}


	 
	 
	 
	 
}
