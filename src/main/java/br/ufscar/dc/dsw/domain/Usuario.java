package br.ufscar.dc.dsw.domain;

public class Usuario {

	private Long id;
	private String nome;
	private String login;
	private String senha;
	private String papel;
	private String cpf;
	private String telefone;
	private String sexo;
	private String data_nascimento;
	private String cnpj;
	private String descricao;


	public Usuario(Long id) {
		this.id = id;
	}

	public Usuario(String nome, String login, String senha, String papel) {
		super();
		this.nome = nome;
		this.login = login;
		this.senha = senha;
		this.papel = papel;
	}

	public Usuario(Long id, String nome, String login, String senha, String papel) {
		super();
		this.id = id;
		this.nome = nome;
		this.login = login;
		this.senha = senha;
		this.papel = papel;
	}

	public Usuario(String nome, String login, String senha, String papel, String cpf, String telefone, String sexo, String data_nascimento) {
		super();
		this.nome = nome;
		this.login = login;
		this.senha = senha;
		this.papel = papel;
		this.cpf = cpf;
        this.telefone = telefone;
        this.sexo = sexo;
        this.data_nascimento = data_nascimento;
	}

	public Usuario(Long id, String nome, String login, String senha, String papel, String cpf, String telefone, String sexo, String data_nascimento) {
		super();
		this.id = id;
		this.nome = nome;
		this.login = login;
		this.senha = senha;
		this.papel = papel;
		this.cpf = cpf;
        this.telefone = telefone;
        this.sexo = sexo;
        this.data_nascimento = data_nascimento;
	}

	public Usuario(String nome, String login, String senha, String papel, String cnpj, String descricao) {
		super();
		this.nome = nome;
		this.login = login;
		this.senha = senha;
		this.papel = papel;
		this.cnpj = cnpj;
		this.descricao = descricao;
	}

	public Usuario(Long id, String nome, String login, String senha, String papel, String cnpj, String descricao) {
		super();
		this.id = id;
		this.nome = nome;
		this.login = login;
		this.senha = senha;
		this.papel = papel;
		this.cnpj = cnpj;
		this.descricao = descricao;
	}

	public Usuario(String nome, String login, String senha, String papel, String cpf, String telefone, String sexo, String data_nascimento, String cnpj, String descricao) {
		super();
		this.nome = nome;
		this.login = login;
		this.senha = senha;
		this.papel = papel;
		this.cpf = cpf;
        this.telefone = telefone;
        this.sexo = sexo;
        this.data_nascimento = data_nascimento;
		this.cnpj = cnpj;
		this.descricao = descricao;
	}

	public Usuario(Long id, String nome, String login, String senha, String papel, String cpf, String telefone, String sexo, String data_nascimento, String cnpj, String descricao) {
		super();
		this.id = id;
		this.nome = nome;
		this.login = login;
		this.senha = senha;
		this.papel = papel;
		this.cpf = cpf;
        this.telefone = telefone;
        this.sexo = sexo;
        this.data_nascimento = data_nascimento;
		this.cnpj = cnpj;
		this.descricao = descricao;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String password) {
		this.senha = password;
	}

	public String getPapel() {
		return papel;
	}

	public void setPapel(String papel) {
		this.papel = papel;
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
}
