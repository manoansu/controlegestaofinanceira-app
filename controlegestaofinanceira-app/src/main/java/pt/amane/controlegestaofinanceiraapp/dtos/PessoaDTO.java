package pt.amane.controlegestaofinanceiraapp.dtos;

import java.io.Serializable;

import pt.amane.controlegestaofinanceiraapp.entities.Endereco;
import pt.amane.controlegestaofinanceiraapp.entities.Pessoa;

public class PessoaDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String nome;
	private Endereco endereco;
	private Boolean ativo;

	public PessoaDTO() {
	}

	public PessoaDTO(Long id, String nome, Endereco endereco, Boolean ativo) {
		this.id = id;
		this.nome = nome;
		this.endereco = endereco;
		this.ativo = ativo;
	}

	public PessoaDTO(Pessoa pessoa) {
		id = pessoa.getId();
		nome = pessoa.getNome();
		endereco = pessoa.getEndereco();
		ativo = pessoa.getAtivo();
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

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

}
