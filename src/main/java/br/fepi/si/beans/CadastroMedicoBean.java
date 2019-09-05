package br.fepi.si.beans;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import br.fepi.si.model.EspecialidadesEnum;
import br.fepi.si.model.EstadoEnum;
import br.fepi.si.model.Medico;
import br.fepi.si.model.SexoEnum;
import br.fepi.si.negocio.CadastroMedicos;
import br.fepi.si.repository.Medicos;
import br.fepi.si.util.DataSource;

@ManagedBean
@ViewScoped
public class CadastroMedicoBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Medico medico;
	
	public void prepararCadastro() {
		if (this.medico == null) {
			this.medico = new Medico();
		}
	}
	
	public void salvar() {
		EntityManager em = DataSource.getEntityManager();
		EntityTransaction et = em.getTransaction();
		FacesContext faces = FacesContext.getCurrentInstance();
		
		try {
			et.begin();
			CadastroMedicos cadastro = new CadastroMedicos(new Medicos(em));
			cadastro.salvar(medico);
			this.medico = new Medico();
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
	
	public Medico getMedico() {
		return medico;
	}
	
	public void setMedico(Medico medico) {
		this.medico = medico;
	}
	
	public SexoEnum[] getSexoMedico() {
		return SexoEnum.values();
	}
	
	public EspecialidadesEnum[] getEspecialidadeMedico() {
		return EspecialidadesEnum.values();
	}
	
	public EstadoEnum[] getCrmEstado() {
		return EstadoEnum.values();
	}
}
