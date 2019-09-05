package br.fepi.si.beans;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import br.fepi.si.model.Consulta;
import br.fepi.si.negocio.CadastroConsultas;
import br.fepi.si.negocio.exception.NegocioException;
import br.fepi.si.repository.Consultas;
import br.fepi.si.util.DataSource;

@ManagedBean
@ViewScoped
public class ConsultasBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private List<Consulta> consultas;
	private Consulta consultaSelecionada;
	
	public void consultar() {
		EntityManager em = DataSource.getEntityManager();
		consultas = new Consultas(em).todos();
		em.close();
	}
	
	public void excluir() {
		FacesContext context = FacesContext.getCurrentInstance();
		
		EntityManager em = DataSource.getEntityManager();
		EntityTransaction et = em.getTransaction();
		
		CadastroConsultas cadastro = new CadastroConsultas(new Consultas(em));
		
		try {
			et.begin();
			cadastro.excluir(this.consultaSelecionada);
			context.addMessage(null, new FacesMessage("Consulta removida com sucesso!"));
			et.commit();
			this.consultar();
		} catch (NegocioException e) {
			et.rollback();
			FacesMessage mensagem = new FacesMessage(e.getMessage());
			mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(null, mensagem);
		} finally {
			em.close();
		}
	}

	public List<Consulta> getConsultas() {
		return consultas;
	}

	public void setConsultas(List<Consulta> consultas) {
		this.consultas = consultas;
	}

	public Consulta getConsultaSelecionada() {
		return consultaSelecionada;
	}

	public void setConsultaSelecionada(Consulta consultaSelecionada) {
		this.consultaSelecionada = consultaSelecionada;
	}
	
}
