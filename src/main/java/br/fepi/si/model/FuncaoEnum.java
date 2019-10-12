package br.fepi.si.model;

public enum FuncaoEnum {
	ADMINISTRADOR("Administrador"), PACIENTE("Paciente");

	private String descricao;

	FuncaoEnum(String descricao){
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
}
