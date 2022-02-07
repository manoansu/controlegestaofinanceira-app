package pt.amane.controlegestaofinanceiraapp.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pt.amane.controlegestaofinanceiraapp.dtos.CategoriaDTO;
import pt.amane.controlegestaofinanceiraapp.entities.Categoria;
import pt.amane.controlegestaofinanceiraapp.repositories.CategoriaRepository;
import pt.amane.controlegestaofinanceiraapp.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repository;

	@Transactional(readOnly = true)
	public CategoriaDTO findById(Long id) {
		Optional<Categoria> catId = repository.findById(id);
		Categoria categoria = catId.orElseThrow(
				() -> new ObjectNotFoundException("Object not found! Id:" + id + ", Type: " + Categoria.class));
		return new CategoriaDTO(categoria);
	}

	@Transactional(readOnly = true)
	public List<CategoriaDTO> findAll() {
		List<Categoria> categorias = repository.findAll();
		return categorias.stream().map(x -> new CategoriaDTO(x)).collect(Collectors.toList());
	}

	public CategoriaDTO create(CategoriaDTO dto) {
		Categoria categoria = new Categoria();
		categoria.setNome(dto.getNome());
		categoria = repository.save(categoria);
		return new CategoriaDTO(categoria);
	}

}
