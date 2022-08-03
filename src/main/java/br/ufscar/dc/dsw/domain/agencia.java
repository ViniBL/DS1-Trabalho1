package br.ufscar.dc.dsw.domain;

public class agencia {
	private Long id_agencia;
	private String cnpj;
	private String descricao;
	private Usuario usuario;
	
	public agencia(Long id_agencia, String cnpj, String descricao, Usuario usuario) {
		super();
		this.id_agencia = id_agencia;
		this.cnpj = cnpj;
		this.descricao = descricao;
		this.usuario = usuario;
	}

	public Long getId_agencia() {
		return id_agencia;
	}

	public void setId_agencia(Long id_agencia) {
		this.id_agencia = id_agencia;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	
}
