package pt.amane.controlegestaofinanceiraapp.dtos;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import pt.amane.controlegestaofinanceiraapp.entities.Categoria;
import pt.amane.controlegestaofinanceiraapp.entities.Lancamento;
import pt.amane.controlegestaofinanceiraapp.entities.Pessoa;
import pt.amane.controlegestaofinanceiraapp.enums.TipoLancamento;

public class LancamentoDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String descricao;
	private LocalDate dataVencimento;
	private LocalDate dataPagamento;
	private BigDecimal valor;
	private String observacao;
	private TipoLancamento tipo;
	private Categoria categoria;
	private Pessoa pessoa;

	public LancamentoDTO() {
	}

	public LancamentoDTO(Long id, String descricao, LocalDate dataVencimento, LocalDate dataPagamento, BigDecimal valor,
			String observacao, TipoLancamento tipo, Categoria categoria, Pessoa pessoa) {
		this.id = id;
		this.descricao = descricao;
		this.dataVencimento = dataVencimento;
		this.dataPagamento = dataPagamento;
		this.valor = valor;
		this.observacao = observacao;
		this.tipo = tipo;
		this.categoria = categoria;
		this.pessoa = pessoa;
	}

	public LancamentoDTO(Lancamento lancamento) {
		id = lancamento.getId();
		descricao = lancamento.getDescricao();
		dataVencimento = lancamento.getDataVencimento();
		dataPagamento = lancamento.getDataPagamento();
		valor = lancamento.getValor();
		observacao = lancamento.getObservacao();
		tipo = lancamento.getTipo();
		categoria = lancamento.getCategoria();
		pessoa = lancamento.getPessoa();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public LocalDate getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(LocalDate dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public LocalDate getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(LocalDate dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public TipoLancamento getTipo() {
		return tipo;
	}

	public void setTipo(TipoLancamento tipo) {
		this.tipo = tipo;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

}
