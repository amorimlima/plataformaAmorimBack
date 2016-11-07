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
@Table(name = "menu_perfil")
@XmlRootElement
public class MenuPerfil implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 @Basic(optional = false)
	 @Column(name = "Idmenu_perfil")
	 private int Idmenu_perfil;
	 
	 
	 @OneToOne
	 private Menu menu;
	 
	 @Column(name = "ordem")
	 private int ordem;
	 
	 @OneToOne
	 private Perfil perfil;
	 

	public int getIdmenu_perfil() {
		return Idmenu_perfil;
	}

	public void setIdmenu_perfil(int idmenu_perfil) {
		Idmenu_perfil = idmenu_perfil;
	}

	public Menu getMenu() {
		return menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	public Perfil getPerfil() {
		return perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public int getOrdem() {
		return ordem;
	}

	public void setOrdem(int ordem) {
		this.ordem = ordem;
	}
	 
	 
	 
	 

}
