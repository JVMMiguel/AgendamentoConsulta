package br.fepi.si.util;

public class Alunos extends Paciente{
	
	private String anoIngresso;
	private String estado;
	
	public Alunos() {
		super();
	}

	public String getAnoIngresso() {
		return anoIngresso;
	}

	public void setAnoIngresso(String anoIngresso) {
		this.anoIngresso = anoIngresso;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	public static void main(String[] args) {
		
		Alunos aluno = new Alunos();
		Alunos aluno1 = new Alunos();
		
		aluno.setAnoIngresso("2016");
		aluno.setEstado("Matriculado");
		aluno.setMatricula(14906);
		aluno.setNome("João Vittor");
		
		aluno1.setAnoIngresso("2019");
		aluno1.setEstado("Matriculado");
		aluno1.setMatricula(14253);
		aluno1.setNome("Luiz Fernando");
		
		
		System.out.println("Aluno: " + aluno.getNome() + "\nMatricula: " + aluno.getMatricula() + "\nAno de Ingresso: "
				+ aluno.getAnoIngresso() + "\nEstado: " + aluno.getEstado());
		
		System.out.println("\nAluno: " + aluno1.getNome() + "\nMatricula: " + aluno1.getMatricula() + "\nAno de Ingresso: "
				+ aluno1.getAnoIngresso() + "\nEstado: " + aluno1.getEstado());
		
	}
}
