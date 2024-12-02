package br.com.fiap.lanchonete.pedidoservicefase4.app.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class ClienteDto {

	private Long id;
	private String nome;

	@Pattern(regexp = "(\\d{3}).\\d{3}.\\d{3}-\\d{2}$", message = "CPF inválido")
	@NotNull
	private String cpf;
	@Pattern(regexp = "(\\d{2})\\d{5}-\\d{4}$", message = "Telefone inválido")
	private String telefone;
	private String endereco;
	@Pattern(regexp = "^[A-Za-z0-9+_.-]+@(.+)$", message = "E-mail inválido")
	private String email;

	public ClienteDto(String nome, String cpf, String telefone, String endereco, String email) {
		this.nome = nome;
		this.cpf = cpf;
		this.telefone = telefone;
		this.endereco = endereco;
		this.email = email;
	}

	public ClienteDto() {
	}

	public String getNome() {
		return nome;
	}

	public String getCpf() {
		return cpf;
	}

	public String getTelefone() {
		return telefone;
	}

	public String getEndereco() {
		return endereco;
	}

	public String getEmail() {
		return email;
	}

	public Long getId() {
		return id;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
