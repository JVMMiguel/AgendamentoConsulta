package br.fepi.si.negocio;

import java.io.Serializable;
import java.util.Date;

import br.fepi.si.model.Paciente;
import br.fepi.si.negocio.exception.NegocioException;
import br.fepi.si.repository.Pacientes;

public class CadastroPacientes implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Pacientes pacientes;
	
	public CadastroPacientes(Pacientes pacientes) {
		this.pacientes = pacientes;
	}
	
	public void salvar(Paciente paciente) throws NegocioException{
		if(paciente.getDataNascimento() != null && paciente.getDataNascimento().after(new Date())) {
			throw new NegocioException("Data de nascimento não pode ser futura à data atual.");
		}
		this.pacientes.guardar(paciente);
	}
	
	public void excluir(Paciente paciente) throws NegocioException{
		paciente = this.pacientes.pacienteId(paciente.getMatricula());
		this.pacientes.remover(paciente);
	}
}
