package br.fepi.si.negocio;

import java.io.Serializable;

import br.fepi.si.model.Medico;
import br.fepi.si.negocio.exception.NegocioException;
import br.fepi.si.repository.Medicos;

public class CadastroMedicos implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Medicos medicos;
	
	public CadastroMedicos (Medicos medicos) {
		this.medicos = medicos;
	}
	
	public void salvar(Medico medico) throws NegocioException {
		this.medicos.guardar(medico);
	}
	
	public void excluir(Medico medico) throws NegocioException {
		medico = this.medicos.medicoCrm(medico.getCrm());
		this.medicos.remover(medico);
	}
}
