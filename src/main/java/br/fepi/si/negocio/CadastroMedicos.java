package br.fepi.si.negocio;

import java.io.Serializable;

import br.fepi.si.model.Medico;
import br.fepi.si.repository.Medicos;

public class CadastroMedicos implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Medicos medicos;
	
	public CadastroMedicos (Medicos medicos) {
		this.medicos = medicos;
	}
	
	public void salvar(Medico medico) throws Exception{
		this.medicos.guardar(medico);
	}
	
	public void excluir(Medico medico) throws Exception{
		medico = this.medicos.medicoCrm(medico.getCrm());
		this.medicos.remover(medico);
	}
}
