package br.ufscar.dc.dsw.domain;

public class cliente {

	private Long id_cliente;
	private String cpf;
	private String telefone;
	private String sexo;
	private String data_nascimento;
	private Usuario usuario;
	
	public cliente(Long id_cliente) {
        this.id_cliente = id_cliente;
    }

    public cliente(String cpf, String telefone, String sexo, String data_nascimento,
            Usuario usuario) {
        this.cpf = cpf;
        this.telefone = telefone;
        this.sexo = sexo;
        this.data_nascimento = data_nascimento;
        this.usuario = usuario;
    }

    public cliente(Long id_cliente, String cpf, String telefone, String sexo, String data_nascimento,
            Usuario usuario) {
        this(cpf, telefone, sexo, data_nascimento, usuario);
        this.id_cliente = id_cliente;
    }
    
    public Long getId_cliente() {
		return id_cliente;
	}

	public void setId_cliente(Long id_cliente) {
		this.id_cliente = id_cliente;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getData_nascimento() {
		return data_nascimento;
	}

	public void setData_nascimento(String data_nascimento) {
		this.data_nascimento = data_nascimento;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
    
}
