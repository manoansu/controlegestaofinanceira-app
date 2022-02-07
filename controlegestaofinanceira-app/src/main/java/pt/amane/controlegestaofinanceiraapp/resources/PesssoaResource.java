package pt.amane.controlegestaofinanceiraapp.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import pt.amane.controlegestaofinanceiraapp.dtos.PessoaDTO;
import pt.amane.controlegestaofinanceiraapp.services.PessoaService;

@RestController
@RequestMapping(value = "/pessoas")
public class PesssoaResource {

	@Autowired
	private PessoaService service;

	@GetMapping(value = "/{id}")
	public ResponseEntity<PessoaDTO> findById(@PathVariable Long id) {
		PessoaDTO pessoaDTO = service.findById(id);
		return ResponseEntity.ok(pessoaDTO);
	}

	@GetMapping
	public ResponseEntity<List<PessoaDTO>> findAll() {
		List<PessoaDTO> listPessoaDTOs = service.findAll();
		return ResponseEntity.ok(listPessoaDTOs);
	}

	@PostMapping
	public ResponseEntity<PessoaDTO> create(@RequestBody PessoaDTO dto) {
		dto = service.create(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/{id}").buildAndExpand(dto.getId())
				.toUri();
		return ResponseEntity.created(uri).body(dto);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

}
