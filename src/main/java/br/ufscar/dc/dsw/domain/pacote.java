package br.ufscar.dc.dsw.domain;

public class pacote {
	private Long id_pacote;
	private String data_partida;
	private Integer duracao;
	private Float valor;
	private String descricao;
	private agencia agencia;
	private destino destino;
	public pacote(Long id_pacote, String data_partida, Integer duracao, Float valor, String descricao,
			br.ufscar.dc.dsw.domain.agencia agencia, br.ufscar.dc.dsw.domain.destino destino) {
		super();
		this.id_pacote = id_pacote;
		this.data_partida = data_partida;
		this.duracao = duracao;
		this.valor = valor;
		this.descricao = descricao;
		this.agencia = agencia;
		this.destino = destino;
	}
	public Long getId_pacote() {
		return id_pacote;
	}
	public void setId_pacote(Long id_pacote) {
		this.id_pacote = id_pacote;
	}
	public String getData_partida() {
		return data_partida;
	}
	public void setData_partida(String data_partida) {
		this.data_partida = data_partida;
	}
	public Integer getDuracao() {
		return duracao;
	}
	public void setDuracao(Integer duracao) {
		this.duracao = duracao;
	}
	public Float getValor() {
		return valor;
	}
	public void setValor(Float valor) {
		this.valor = valor;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public agencia getAgencia() {
		return agencia;
	}
	public void setAgencia(agencia agencia) {
		this.agencia = agencia;
	}
	public destino getDestino() {
		return destino;
	}
	public void setDestino(destino destino) {
		this.destino = destino;
	}
	
	
}
