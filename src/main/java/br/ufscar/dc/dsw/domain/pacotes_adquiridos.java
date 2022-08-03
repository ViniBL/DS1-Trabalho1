package br.ufscar.dc.dsw.domain;

public class pacotes_adquiridos {
	private Long id_pacote_adquirido;
	private String status;
	private cliente cliente;
	private pacote pacote;
	public pacotes_adquiridos(Long id_pacote_adquirido, String status, br.ufscar.dc.dsw.domain.cliente cliente,
			br.ufscar.dc.dsw.domain.pacote pacote) {
		super();
		this.id_pacote_adquirido = id_pacote_adquirido;
		this.status = status;
		this.cliente = cliente;
		this.pacote = pacote;
	}
	public Long getId_pacote_adquirido() {
		return id_pacote_adquirido;
	}
	public void setId_pacote_adquirido(Long id_pacote_adquirido) {
		this.id_pacote_adquirido = id_pacote_adquirido;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public cliente getCliente() {
		return cliente;
	}
	public void setCliente(cliente cliente) {
		this.cliente = cliente;
	}
	public pacote getPacote() {
		return pacote;
	}
	public void setPacote(pacote pacote) {
		this.pacote = pacote;
	}
	
	
}
