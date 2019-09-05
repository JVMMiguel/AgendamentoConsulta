package br.fepi.si.negocio;

import java.io.Serializable;

import br.fepi.si.model.Paciente;
import br.fepi.si.repository.Pacientes;

public class CadastroPacientes implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Pacientes pacientes;
	
	public CadastroPacientes(Pacientes pacientes) {
		this.pacientes = pacientes;
	}
	
	public void salvar(Paciente paciente) throws Exception{
		this.pacientes.guardar(paciente);
	}
	
	public void excluir(Paciente paciente) throws Exception{
		paciente = this.pacientes.pacienteId(paciente.getMatricula());
		this.pacientes.remover(paciente);
	}
}
