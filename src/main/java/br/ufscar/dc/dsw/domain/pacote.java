package br.ufscar.dc.dsw.domain;



public class pacote{

	private Long id_pacote;
	private String data_partida;
	private int duracao;
	private Float valor;
	private Float valorProposta;
	private String descricao;
	private Usuario usuario;
	private destino destino;

	public pacote(Long id_pacote){
		this.id_pacote = id_pacote;
	}
	
	public pacote(String data_partida, int duracao, Float valor, String descricao,
			Usuario usuario, destino destino) {
		this.data_partida = data_partida;
		this.duracao = duracao;
		this.valor = valor;
		this.descricao = descricao;
		this.usuario = usuario;
		this.destino = destino;
	}

	public pacote(Long id_pacote, String data_partida, int duracao, Float valor, String descricao,
			Usuario usuario, destino destino){
		this(data_partida, duracao, valor, descricao, usuario, destino);
		this.id_pacote = id_pacote;

	}

	public pacote(Long id_pacote, String data_partida, int duracao, Float valor, Float valorProposta, String descricao,
			Usuario usuario, destino destino){
		this(data_partida, duracao, valor, descricao, usuario, destino);
		this.valorProposta = valorProposta;
		this.id_pacote = id_pacote;

	}

	public pacote(Long id_pacote, Float valorProposta){
		this.id_pacote = id_pacote;
		this.valorProposta = valorProposta;
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
	
	public int getDuracao() {
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

	public Float getValorProposta() {
		return valorProposta;
	}
	public void setValorProposta(Float valorProposta) {
		this.valorProposta = valorProposta;
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
	
	public destino getDestino() {
		return destino;
	}
	
	public void setDestino(destino destino) {
		this.destino = destino;
	}
	
	
}
