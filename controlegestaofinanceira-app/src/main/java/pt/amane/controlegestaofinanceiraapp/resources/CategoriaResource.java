package pt.amane.controlegestaofinanceiraapp.resources;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import pt.amane.controlegestaofinanceiraapp.dtos.CategoriaDTO;
import pt.amane.controlegestaofinanceiraapp.services.CategoriaService;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {

	@Autowired
	private CategoriaService service;

	@GetMapping(value = "/{id}")
	public ResponseEntity<CategoriaDTO> findById(@PathVariable Long id) {
		CategoriaDTO dto = service.findById(id);
		return ResponseEntity.ok(dto);
	}

	@GetMapping
	public ResponseEntity<List<CategoriaDTO>> findAll() {
		List<CategoriaDTO> dtos = service.findAll();
		return ResponseEntity.ok(dtos);
	}

	@PostMapping
	public ResponseEntity<CategoriaDTO> create(@Valid @RequestBody CategoriaDTO dto) {
		dto = service.create(dto);
		/**
		 * atraves dessa classe ServletUriComponentsBuilder do spring, vou pegar
		 * apartir da requisição atual (/categorias), adicionar o codigo e adicionar
		 * o codigo na uri..
		 */
		URI uri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/{id}").buildAndExpand(dto.getId())
				.toUri();
		return ResponseEntity.created(uri).body(dto);
	}

}
