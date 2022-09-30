package br.com.entra21.spring.execicioQuery.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import br.com.entra21.spring.execicioQuery.model.Exemplo;
import br.com.entra21.spring.execicioQuery.model.ItemNivel3;
import br.com.entra21.spring.execicioQuery.repository.IExemploRepository;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/exemplo")
public class ExemploController {
	@Autowired
	private IExemploRepository exemploRepository;

	@GetMapping("")
	@ResponseStatus(HttpStatus.OK)
	public List<Exemplo> listAll() {
		List<Exemplo> response = exemploRepository.findAll();
		response.forEach(programador -> {
			setMaturidadeNivel3(programador);
		});
		return response;

	}

	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody List<Exemplo> get(@PathVariable("id") int id) {
		List<Exemplo> response = exemploRepository.findById(id).stream().toList();
		response.forEach(programador -> {
			setMaturidadeNivel3(programador);
		});
		return response;

	}

	@PostMapping()
	@ResponseStatus(HttpStatus.CREATED)
	public @ResponseBody Exemplo create(@RequestBody Exemplo novoExemplo) {
		Exemplo response = exemploRepository.save(novoExemplo);
		return response;
	}

	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody Optional<Exemplo> update(@PathVariable("id") int id, @RequestBody Exemplo exemploEditado) {
		Exemplo atual = exemploRepository.findById(id).get();
		atual.setActive(exemploEditado.getActive());
		atual.setAge(exemploEditado.getAge());
		atual.setFirstname(exemploEditado.getFirstname());
		atual.setLastname(exemploEditado.getLastname());
		exemploRepository.save(atual);
		return exemploRepository.findById(id);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody boolean delete(@PathVariable("id") int id) {
		exemploRepository.deleteById(id);
		return !exemploRepository.existsById(id);
	}
		
		@GetMapping(value="/byage/{age}")
		 public List<Exemplo> getByAge(@PathVariable("age") Integer age ){
			return exemploRepository.findByAge(age);
		}
		
		@GetMapping(value="/nameandage/{name}/{age}")
		public List<Exemplo> getByAgeName(@PathVariable("name") String name,@PathVariable("age") Integer age){
			return exemploRepository.findByFirstnameAndAge(name, age);
		}
		
		@GetMapping(value="/lessthanequalage/{age}")
		public List<Exemplo>getByAgeLessThanEqual(@PathVariable("age") Integer age){
			return exemploRepository.findByAgeLessThanEqual(age);
		}
		
		@GetMapping(value="/lastnamestartingwith/{prefix}")
		public List<Exemplo>getStartingWith(@PathVariable("prefix") String sla){
			return exemploRepository.findByLastnameStartingWith(sla);
		}
		
		@GetMapping(value="/lastnamestartingwithandagelessthanequal/{prefix}/{age}")
		public List<Exemplo>getStartingWith(@PathVariable("prefix") String sla,@PathVariable("age") Integer age){
			return exemploRepository.findByLastnameStartingWithAndAgeLessThanEqual(sla,age);
		}


	private void setMaturidadeNivel3(Exemplo exemplo) {
		final String PATH = "localhost:8080/exemplo";
		exemplo.setLinks(new ArrayList<>());
		exemplo.getLinks().add(new ItemNivel3("GET", PATH));
		exemplo.getLinks().add(new ItemNivel3("GET", PATH + "/" + exemplo.getId()));
		exemplo.getLinks().add(new ItemNivel3("DELETE", PATH + "/" + exemplo.getId()));
		exemplo.getLinks().add(new ItemNivel3("POST", PATH));
		exemplo.getLinks().add(new ItemNivel3("PUT", PATH + "/" + exemplo.getId()));

	}

}
