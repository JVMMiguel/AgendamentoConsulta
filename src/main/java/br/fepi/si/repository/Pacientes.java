package br.fepi.si.repository;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.fepi.si.model.Paciente;

public class Pacientes implements Serializable {

	private static final long serialVersionUID = 1L;

	private EntityManager em;

	public Pacientes(EntityManager em) {
		this.em = em;
	}

	public List<Paciente> loginUsuario(Long matricula, String senha) {
		TypedQuery<Paciente> query = em.createQuery("SELECT p FROM Paciente p WHERE"
		+ " p.matricula = :matricula and p.senha = :senha", Paciente.class)
		.setParameter("matricula", matricula)
		.setParameter("senha", senha);
		return query.getResultList();
	}
	
	public List<Paciente> loginUsuarioFuncao(Long matricula, String funcao) {
		TypedQuery<Paciente> query = em.createQuery("SELECT p FROM Paciente p WHERE"
		+ " p.matricula = :matricula and p.funcao = :funcao", Paciente.class)
		.setParameter("matricula", matricula)
		.setParameter("funcao", funcao);
		return query.getResultList();
	}
	
	public List<Paciente> userLogado(String nomeUsuario)
	{
		TypedQuery<Paciente> query = em.createQuery(
		"SELECT p FROM Paciente p WHERE p.nome = :nome", Paciente.class)
		.setParameter("nome", nomeUsuario);
		return query.getResultList();
	}

	public Paciente pacienteId(Long matricula) {
		return em.find(Paciente.class, matricula);
	}

	public List<Paciente> todos() {
		TypedQuery<Paciente> query = em.createQuery("from Paciente p order by p.matricula", Paciente.class);
		return query.getResultList();
	}

	public void adicionar(Paciente paciente) {
		this.em.persist(paciente);
	}

	public void guardar(Paciente paciente) {
		this.em.merge(paciente);
	}

	public void remover(Paciente paciente) {
		this.em.remove(paciente);
	}
}
