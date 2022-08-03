package br.ufscar.dc.dsw.domain;

public class destino {
	private Long id_destino;
	private String cidade;
	private String estado;
	private String pais;
	public destino(Long id_destino, String cidade, String estado, String pais) {
		super();
		this.id_destino = id_destino;
		this.cidade = cidade;
		this.estado = estado;
		this.pais = pais;
	}
	public Long getId_destino() {
		return id_destino;
	}
	public void setId_destino(Long id_destino) {
		this.id_destino = id_destino;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getPais() {
		return pais;
	}
	public void setPais(String pais) {
		this.pais = pais;
	}
	
	
}
