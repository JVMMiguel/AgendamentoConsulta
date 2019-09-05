package br.fepi.si.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import br.fepi.si.model.Consulta;
import br.fepi.si.model.ConsultaEnum;
import br.fepi.si.model.Medico;
import br.fepi.si.model.Paciente;
import br.fepi.si.negocio.CadastroConsultas;
import br.fepi.si.negocio.exception.NegocioException;
import br.fepi.si.repository.Consultas;
import br.fepi.si.repository.Medicos;
import br.fepi.si.repository.Pacientes;
import br.fepi.si.util.DataSource;

@ManagedBean
@ViewScoped
public class CadastroConsultaBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Consulta consulta;
	private List<Paciente> todosPacientes = new ArrayList<>();
	private List<Medico> todosMedicos = new ArrayList<>();
	
	public void prepararCadastro() {
		EntityManager em = DataSource.getEntityManager();
		try {
			this.todosPacientes = new Pacientes(em).todos();
			this.todosMedicos = new Medicos(em).todos();
		} finally {
			em.close();
		}
		if (this.consulta == null) {
			this.consulta = new Consulta();
		}
	}
	
	public void salvar() {
		EntityManager em = DataSource.getEntityManager();
		EntityTransaction et = em.getTransaction();
		FacesContext context = FacesContext.getCurrentInstance();
		
		try {
			et.begin();
			CadastroConsultas cadastro = new CadastroConsultas(new Consultas(em));
			cadastro.salvar(consulta);
			this.consulta = new Consulta();
			context.addMessage(null, new FacesMessage("Consulta marcada com sucesso!"));
			et.commit();
		} catch (NegocioException e) {
			et.rollback();
			FacesMessage mensagem = new FacesMessage(e.getMessage());
			mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(null, mensagem);
		} finally {
			em.close();
		}
	}

	public Consulta getConsulta() {
		return consulta;
	}

	public void setConsulta(Consulta consulta) {
		this.consulta = consulta;
	}

	public List<Paciente> getTodosPacientes() {
		return todosPacientes;
	}

	public void setTodosPacientes(List<Paciente> todosPacientes) {
		this.todosPacientes = todosPacientes;
	}

	public List<Medico> getTodosMedicos() {
		return todosMedicos;
	}

	public void setTodosMedicos(List<Medico> todosMedicos) {
		this.todosMedicos = todosMedicos;
	}
	
	public ConsultaEnum[] getTipoConsulta() {
		return ConsultaEnum.values();
	}
}
