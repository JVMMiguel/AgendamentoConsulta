package br.fepi.si.model;

public enum EstadoEnum {
	
	MG("Minas Gerais"), SP("São Paulo"), ES("Espírito Santo");
	
	private String descricao;
	
	EstadoEnum(String descricao){
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}
}