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

import pt.amane.controlegestaofinanceiraapp.dtos.PessoaDTO;
import pt.amane.controlegestaofinanceiraapp.entities.Pessoa;
import pt.amane.controlegestaofinanceiraapp.repositories.PessoaRepository;
import pt.amane.controlegestaofinanceiraapp.services.exceptions.DataBaseIntegrityViolationException;
import pt.amane.controlegestaofinanceiraapp.services.exceptions.ResourceNotFoundException;

@Service
public class PessoaService {

	@Autowired
	private PessoaRepository repository;

	@Transactional(readOnly = true)
	public PessoaDTO findById(Long id) {
		Optional<Pessoa> pessoaId = repository.findById(id);
		Pessoa pessoa = pessoaId.orElseThrow(() -> new ResourceNotFoundException(
				"Object not found! Id: " + id + ", Type: " + PessoaDTO.class.getName()));
		return new PessoaDTO(pessoa);
	}

	@Transactional(readOnly = true)
	public List<PessoaDTO> findAll() {
		List<Pessoa> pessoas = repository.findAll();
		return pessoas.stream().map(obj -> new PessoaDTO(obj)).collect(Collectors.toList());
	}
	
	@Transactional
	public PessoaDTO create(PessoaDTO dto) {
		Pessoa pessoa = new Pessoa();
		pessoa = copyPessoa(pessoa, dto);
		pessoa = repository.save(pessoa);
		return new PessoaDTO(pessoa);		
	}
	
	@Transactional
	public PessoaDTO update(PessoaDTO dto, Long id) {
		try {
			@SuppressWarnings("deprecation")
			Pessoa pessoa = repository.getOne(id);
			pessoa = copyPessoa(pessoa, dto);
			return new PessoaDTO(pessoa);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Id not found! Id: " + id + ", Type: " + PessoaDTO.class.getName());
		}
	}
	
	public void delete(Long id) {
		try {
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Id not found! Id: " + id);
		}catch (DataIntegrityViolationException e) {
			throw new DataBaseIntegrityViolationException("person cannot be deleted! has associated object..");
		}
	}

	private Pessoa copyPessoa(Pessoa pessoa, PessoaDTO dto) {
		pessoa.setNome(dto.getNome());
		pessoa.setAtivo(dto.getAtivo());
		pessoa.setEndereco(dto.getEndereco());
		return pessoa;
	}

}
