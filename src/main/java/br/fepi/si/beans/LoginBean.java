package br.fepi.si.beans;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;

import br.fepi.si.model.Paciente;
import br.fepi.si.repository.Pacientes;
import br.fepi.si.util.DataSource;

@ManagedBean
@SessionScoped
public class LoginBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Paciente paciente = new Paciente();
	private List<Paciente> lista_pacientes;

	EntityManager em = DataSource.getEntityManager();
	private Pacientes pacientes = new Pacientes(em);

	public void prepararCadastro() {
		if (this.paciente == null) {
			this.paciente = new Paciente();
		}
	}

	public String login() throws IOException {
		try {
			lista_pacientes = pacientes.loginUsuario(paciente.getMatricula(), paciente.getSenha());
			if (lista_pacientes.isEmpty()) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
						"Usuário ou senha incorretos!", "Erro de login!"));
				return null;
			} 
		} catch (Exception e) {
		}
		return "/ConsultaConsultas?faces-redirect=true";
	}
	
	public String logout() {
		paciente = null;
		pacientes = null;
		lista_pacientes = null;
		return "/Home?faces-redirect=true";
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public List<Paciente> getLista_pacientes() {
		return lista_pacientes;
	}

	public void setLista_pacientes(List<Paciente> lista_pacientes) {
		this.lista_pacientes = lista_pacientes;
	}

	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}

	public Pacientes getPacientes() {
		return pacientes;
	}

	public void setPacientes(Pacientes pacientes) {
		this.pacientes = pacientes;
	}

}
