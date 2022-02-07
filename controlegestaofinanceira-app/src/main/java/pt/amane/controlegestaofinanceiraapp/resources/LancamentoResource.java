package pt.amane.controlegestaofinanceiraapp.resources;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import pt.amane.controlegestaofinanceiraapp.dtos.LancamentoDTO;
import pt.amane.controlegestaofinanceiraapp.services.LancamentoService;

@RestController
@RequestMapping(value = "/lancamentos")
public class LancamentoResource {

	@Autowired
	private LancamentoService service;

	@GetMapping(value = "/{id}")
	public ResponseEntity<LancamentoDTO> findById(@PathVariable Long id) {
		LancamentoDTO lancamentoDTO = service.findById(id);
		return ResponseEntity.ok(lancamentoDTO);
	}

	@GetMapping
	public ResponseEntity<List<LancamentoDTO>> findAll() {
		List<LancamentoDTO> listPessoaDTOs = service.findAll();
		return ResponseEntity.ok(listPessoaDTOs);
	}

	@PostMapping
	public ResponseEntity<LancamentoDTO> create(@Valid @RequestBody LancamentoDTO dto) {
		dto = service.create(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/{id}").buildAndExpand(dto.getId())
				.toUri();
		return ResponseEntity.created(uri).body(dto);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<LancamentoDTO> update(@Valid @RequestBody LancamentoDTO dto, @PathVariable Long id){
		dto = service.update(dto, id);
		return ResponseEntity.ok(dto);
	}
	
	@PatchMapping(value = "/{id}")
	public ResponseEntity<LancamentoDTO> updatePatch(@Valid @RequestBody LancamentoDTO dto, @PathVariable Long id){
		dto = service.update(dto, id);
		return ResponseEntity.ok(dto);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

}
