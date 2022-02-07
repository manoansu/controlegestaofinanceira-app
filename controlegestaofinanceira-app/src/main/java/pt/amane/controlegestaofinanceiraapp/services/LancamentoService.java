package pt.amane.controlegestaofinanceiraapp.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pt.amane.controlegestaofinanceiraapp.dtos.LancamentoDTO;
import pt.amane.controlegestaofinanceiraapp.entities.Lancamento;
import pt.amane.controlegestaofinanceiraapp.repositories.LancamentoRepository;
import pt.amane.controlegestaofinanceiraapp.services.exceptions.DataBaseIntegrityViolationException;
import pt.amane.controlegestaofinanceiraapp.services.exceptions.ResourceNotFoundException;

@Service
public class LancamentoService {

	@Autowired
	private LancamentoRepository repository;

	@Transactional(readOnly = true)
	public LancamentoDTO findById(Long id) {
		Optional<Lancamento> lancamentoId = repository.findById(id);
		Lancamento lancamento = lancamentoId.orElseThrow(() -> new ResourceNotFoundException(
				"Object not found! Id: " + id + ", Type: " + LancamentoDTO.class.getName()));
		return new LancamentoDTO(lancamento);
	}

	@Transactional(readOnly = true)
	public List<LancamentoDTO> findAll() {
		List<Lancamento> lancamentos = repository.findAll();
		return lancamentos.stream().map(obj -> new LancamentoDTO(obj)).collect(Collectors.toList());
	}
	
	@Transactional
	public LancamentoDTO create(LancamentoDTO dto) {
		Lancamento lancamento = new Lancamento();
		lancamento = copyLancamento(lancamento, dto);
		lancamento = repository.save(lancamento);
		return new LancamentoDTO(lancamento);		
	}
	
	@Transactional
	public LancamentoDTO update(LancamentoDTO dto, Long id) {
		try {
			@SuppressWarnings("deprecation")
			Lancamento lancamento = repository.getOne(id);
			lancamento = copyLancamento(lancamento, dto);
			return new LancamentoDTO(lancamento);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Id not found! Id: " + id + ", Type: " + LancamentoDTO.class.getName());
		}
	}
	
	public void delete(Long id) {
		try {
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Id not found! Id: " + id);
		}catch (DataIntegrityViolationException e) {
			throw new DataBaseIntegrityViolationException("launch cannot be deleted! has associated object..");
		}
	}

	private Lancamento copyLancamento(Lancamento lancamento, LancamentoDTO dto) {
		lancamento.setCategoria(dto.getCategoria());
		lancamento.setDataPagamento(dto.getDataPagamento());
		lancamento.setDataVencimento(dto.getDataVencimento());
		lancamento.setDescricao(dto.getDescricao());
		lancamento.setObservacao(dto.getObservacao());
		lancamento.setPessoa(dto.getPessoa());
		lancamento.setTipo(dto.getTipo());
		lancamento.setValor(dto.getValor());
		return lancamento;
	}

}
