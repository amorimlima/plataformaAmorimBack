package br.com.muranodesign.dao;

import java.util.List;

import br.com.muranodesign.model.LicensaProfessor;

public interface LicensaProfessorDAO {

	public void criar(LicensaProfessor result);

	public List<LicensaProfessor> listarkey(int id);

	public void deletar(LicensaProfessor result);

	public void update(LicensaProfessor result);

	public List<LicensaProfessor> listarProfessor(int idProfessor);

}
