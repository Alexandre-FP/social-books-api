package br.fepi.socialbooks.resources;

//import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.fepi.socialbooks.domain.Comentario;
import br.fepi.socialbooks.domain.Livro;
import br.fepi.socialbooks.repository.ComentarioRepository;
import br.fepi.socialbooks.repository.LivrosRepository;

@RestController
@RequestMapping(value = "/livros")
public class LivrosResources { 
	
	@Autowired
	private LivrosRepository livrosRepository;

	@Autowired
	private ComentarioRepository comentariosRepository; 

	@RequestMapping(method = RequestMethod.GET)
	public List<Livro> listar() {
		return livrosRepository.findAll();
	}

	@PostMapping()
	public Livro salvar(@RequestBody Livro livro){
		return livrosRepository.save(livro);
	}

	@CrossOrigin
	@GetMapping(value = "/{id}")
	public Livro findById(@PathVariable (value = "id")Long id){
		return livrosRepository.findById(id).orElse(null);
	}

	@DeleteMapping(value = "/{id}")
	public void deletar(@PathVariable(value = "id")Long id){
		livrosRepository.deleteById(id);
	}

	@PutMapping(value = "{id}")
	public void atualizar(@RequestBody Livro livro, @PathVariable("id") Long id){
		livro.setId(id);
		livrosRepository.save(livro);
	}
	
	@RequestMapping(value = "/{id}/comentario", method = RequestMethod.POST)
	public void adicionarComentario(@PathVariable("id") Long livroId, @RequestBody Comentario comentario){
		Livro livro = new Livro(null);
		livro.setId(livroId);

		comentario.setLivro(livro);
		comentariosRepository.save(comentario);  
	}
}
