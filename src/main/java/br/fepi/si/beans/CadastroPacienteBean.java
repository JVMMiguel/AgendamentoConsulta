package br.fepi.si.beans;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import br.fepi.si.model.GrupoEnum;
import br.fepi.si.model.Paciente;
import br.fepi.si.model.SexoEnum;
import br.fepi.si.negocio.CadastroPacientes;
import br.fepi.si.repository.Pacientes;
import br.fepi.si.util.DataSource;

@ManagedBean
@ViewScoped
public class CadastroPacienteBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Paciente paciente;
	
	public void prepararCadastro() {
		if (this.paciente == null) {
			this.paciente = new Paciente();
		}
	}
	
	public void salvar() {
		EntityManager em = DataSource.getEntityManager();
		EntityTransaction et = em.getTransaction();
		FacesContext faces = FacesContext.getCurrentInstance();
		
		try {
			et.begin();
			CadastroPacientes cadastro = new CadastroPacientes(new Pacientes(em));
			cadastro.salvar(paciente);
			this.paciente = new Paciente();
			faces.addMessage(null, new FacesMessage("Salvo com sucesso"));
			et.commit();
		} catch (Exception e) {
			et.rollback();
			FacesMessage mensagem = new FacesMessage(e.getMessage());
			mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
			faces.addMessage(null, mensagem);
		} finally {
			em.close();
		}
		
	}
	
	public Paciente getPaciente() {
		return paciente;
	}
	
	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}
	
	public SexoEnum[] getSexoPaciente() {
		return SexoEnum.values();
	}
	
	public GrupoEnum[] getGrupoPaciente() {
		return GrupoEnum.values();
	}
}
