package br.fepi.socialbooks.resources;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController ;

import br.fepi.socialbooks.domain.Livro;
import br.fepi.socialbooks.repository.LivrosRepository;

@RestController
@RequestMapping(value = "/livros")
public class LivrosResources {
	
	@Autowired
	private LivrosRepository livrosRepository;
	
	@RequestMapping(method = RequestMethod.GET)
	public List<Livro>  listar() {
		Livro livro1 = new Livro("a arte da guerra");
		Livro livro2 = new Livro("O pequeno príncipe");
		Livro livro3 = new Livro("1984");
		
		Livro[] livros = {
			livro1, livro2,livro3
		};
		
		return Arrays.asList(livros);
	}

	@RequestMapping(method = RequestMethod.POST)
	public void salvar(@RequestBody Livro livro) {
		
		livrosRepository.save(livro);
	}
	
	//@RequestMapping(method = RequestMethod.GET)
	//public List<Livro> listar(@RequestBody Livro livro) {
	//	return livrosRepository.findAll();
	//}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)	
	public Livro buscaId(@PathVariable("id") long id) {
		return livrosRepository.findById(id).orElse(null);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)	
	public void deletar (@PathVariable("id") Long id) {
		livrosRepository.deleteById(id);
	}


	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public void atualizar (@RequestBody Livro livro,@PathVariable("id") Long id) {
		livro.setId(id);
		livrosRepository.save(livro);
	}
	
}
