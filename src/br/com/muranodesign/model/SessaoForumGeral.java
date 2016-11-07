package br.com.muranodesign.model;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author Kevyn
 *
 */
@Entity
@Table(name = "sessao_forum")
@XmlRootElement
public class SessaoForumGeral implements Serializable {
	private static final long serialVersionUID = 1L;
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 @Basic(optional = false)
	 @Column(name = "idSessaoForum")
	 private int idSessaoForum;
	 @Column(name = "sessao")
	 private String sessao;
	 
	 @OneToMany(cascade = CascadeType.ALL, mappedBy = "sessaoForum")
	 private Collection<ForumGeralQuestao> forumQuestao;
	 	

	 
	public int getIdSessaoForum() {
		return idSessaoForum;
	}
	public void setIdSessaoForum(int idSessaoForum) {
		this.idSessaoForum = idSessaoForum;
	}
	public String getSessao() {
		return sessao;
	}
	public void setSessao(String sessao) {
		this.sessao = sessao;
	}
	public Collection<ForumGeralQuestao> getForumQuestao() {
		return forumQuestao;
	}
	public void setForumQuestao(Collection<ForumGeralQuestao> forumQuestao) {
		this.forumQuestao = forumQuestao;
	}

	 
	 
	 
	 
}
