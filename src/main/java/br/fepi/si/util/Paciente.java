package br.fepi.si.util;

public abstract class Paciente {
	
	private int matricula;
	private String nome;
	
	public Paciente() {
		super();
	}
	
	public int getMatricula() {
		return matricula;
	}
	
	public void setMatricula(int matricula) {
		this.matricula = matricula;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
}
