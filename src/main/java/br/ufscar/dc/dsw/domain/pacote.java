package br.ufscar.dc.dsw.domain;
/* 
import java.io.Serializable;
//import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
//import javax.xml.bind.annotation.XmlID;

@Entity
@Table(name = "Pacote")
@NamedQueries({
	@NamedQuery(name = "Pacote.getAll", query = "SELECT * FROM Pacote p"),
	@NamedQuery(name = "Pacote.getbyDestino", query = "SELCT * FROM Pacote p where p.destino=:destino"),
	@NamedQuery(name = "Pacote.getbyAgencia", query = "SELCT * FROM Pacote p where p.agencia=:nomeAgencia"),
	@NamedQuery(name = "Pacote.getbyData_partida", query = "SELCT * FROM Pacote p where p.data_partida=:dataPartida")
})

*/
public class pacote{

	//private static final long serialVersionUID = 1L;
	//@Id
	//@Basic(optional = false)
	//@Column(name = "id_pacote")
	private Long id_pacote;
	//@Column(name = "data_partida")
	private String data_partida;
	//@Column(name = "duracao")
	private int duracao;
	//@Column(name = "valor")
	private Float valor;
	//@Column(name = "descricao")
	private String descricao;
	//@OneToMany(mappedBy = "Agencia")
	private agencia agencia;
	//@OneToMany(mappedBy = "Destino")
	private destino destino;

	public pacote(Long id_pacote){
		this.id_pacote = id_pacote;
	}
	
	public pacote(String data_partida, int duracao, Float valor, String descricao,
			agencia agencia, destino destino) {
		this.data_partida = data_partida;
		this.duracao = duracao;
		this.valor = valor;
		this.descricao = descricao;
		this.agencia = agencia;
		this.destino = destino;
	}

	public pacote(Long id_pacote, String data_partida, int duracao, Float valor, String descricao,
			agencia agencia, destino destino){
		this(data_partida, duracao, valor, descricao, agencia, destino);
		this.id_pacote = id_pacote;

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
